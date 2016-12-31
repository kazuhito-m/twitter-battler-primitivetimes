'use strict';

const gulp = require('gulp');
const espower = require('gulp-espower');
const mocha = require('gulp-mocha');
const istanbul = require('gulp-istanbul');
const del = require('del');
const mkdirp = require('mkdirp');
const runSequence = require('run-sequence');
const prettify = require('gulp-jsbeautifier');
const eslint = require('gulp-eslint');
const plumber = require('gulp-plumber');
const plato = require('gulp-plato');
const through = require('through2');
const watch = require('gulp-watch');
const fs = require('fs');
const notify = require('gulp-notify');
const notifier = require('node-notifier');
const browserify = require('browserify');
const babel = require("gulp-babel");
const source = require('vinyl-source-stream');
const uglify = require("gulp-uglify");
const buffer = require("vinyl-buffer");
const babelify = require('babelify');


// 定数的pathマップ
const paths = {
    mains: './src/main/js/*.js',
    main_dir: './src/main/js/',
    tests: './src/test/js/*.js',
    srcs: './src/*/js/**/*',
    src_dir: './src',
    work_dir: './build/js/',
    work_mains: './build/js/main/js/**/*.js',
    work_tests: './build/js/test/js/**/*.js',
    ps_tests: './build/js/power-assert-test/js/**/*.js',
    ps_test_dir: './build/js/power-assert-test/js/',
    coverage_dir: './build/js/coverage',
    report_dir: './build/js/report',
    format_dir: './build/js/format',
    dest_dir: './src/main/resources/static/js'
};

// distディレクトリのクリーンアップ
gulp.task('clean', (cb) => {
    return del([paths.work_dir], cb);
});

// テスト周り

// ためしに「テスト開始前に本体側をソースフォーマット」してみる。具合が悪けりゃ変える。
gulp.task('test-src-copy', () => {
    mkdirp(paths.work_dir);
    return gulp.src([paths.srcs])
        .pipe(gulp.dest(paths.work_dir));
});

// 普通のassertで書かれたテストを、pwoer-assertで書いたテストへトランスパイル。
gulp.task('test-transpile-power-assert', () => {
    return gulp.src(paths.work_tests)
        .pipe(plumber({
            errorHandler: notify.onError('[test-transpile(<%=error.plugin %>)]\n<%= error.message %>')
        }))
        .pipe(espower())
        .pipe(plumber.stop())
        .pipe(gulp.dest(paths.ps_test_dir));
});

// カバレッジに必要な装備の初期設定。
gulp.task('test-mapping-coverage-src', () => {
    return gulp.src([paths.work_mains])
        .pipe(istanbul())
        .pipe(istanbul.hookRequire());
});

// テスト実行。その際、テストが成功していればカバレッジレポートを作成。
gulp.task('unit-test-nonstop', () => {
    return gulp.src([paths.ps_tests], {
            read: false
        })
        .pipe(plumber({
            errorHandler: notify.onError('[unit-test(<%=error.plugin %>)]<%= error.message %>')
        }))
        .pipe(mocha({
            reporter: 'list'
        }))
        .pipe(istanbul.writeReports({
            reportOpts: {
                dir: paths.coverage_dir
            }
        }))
        .pipe(istanbul.enforceThresholds({
            thresholds: {
                global: 10
            }
        }))
        .pipe(plumber.stop());
});

// Mochaテストだけするタスク。
gulp.task('unit-test', () => {
    return gulp.src([paths.ps_tests], {
            read: false
        })
        .pipe(mocha({
            reporter: 'list'
        }));
});

// カバレッジに取るタスク。
gulp.task('coverage', () => {
    return gulp.src([paths.ps_tests], {
            read: false
        })
        .pipe(istanbul.writeReports({
            reportOpts: {
                dir: paths.coverage_dir
            }
        }))
        .pipe(istanbul.enforceThresholds({
            thresholds: {
                global: 10
            }
        }));
});

gulp.task('test', (cb) => {
    return runSequence(
        'clean',
        'static-analysis-eslint', // コードフォーマットがズタズタにならないため、事前にESLintでチェック。
        'format',
        'test-src-copy',
        'test-transpile-power-assert',
        'test-mapping-coverage-src',
        'unit-test',
        'coverage',
        'static-analysis-plato',
        cb
    );
});

gulp.task('test-for-loop', (cb) => {
    return runSequence(
        'clean',
        'static-analysis-eslint', // コードフォーマットがズタズタにならないため、事前にESLintでチェック。
        'format',
        'test-src-copy',
        'test-transpile-power-assert',
        'test-mapping-coverage-src',
        'unit-test-nonstop',
        'static-analysis-plato',
        cb
    );
});


// ソースフォーマット周り

gulp.task('settings-format', () => {
    return gulp.src(['./*.js'])
        .pipe(prettify())
        .pipe(gulp.dest('./'));
});

gulp.task('format', (cb) => {
    return runSequence(
        'src-format',
        'settings-format',
        cb
    );
});

// 静的解析

// ESLintは「コンパイルがわり」に使う(構文おかしかったらコケてくれるように)
gulp.task('static-analysis-eslint', () => {
    return gulp.src([paths.mains])
        .pipe(eslint({
            useEslintrc: true
        })) // .eslintrc を参照
        .pipe(eslint.format())
        .pipe(eslint.failOnError())
});

// Platoは「テスト終わった後の解析」に使用する。
gulp.task('static-analysis-plato', () => {
    return gulp.src(paths.mains)
        .pipe(plato(paths.report_dir, {
            complexity: {
                trycatch: true
            }
        }));
});

// コードフォーマット ＆ 差分だけソース側に書き戻し機能。

gulp.task('formating-and-dump', () => {
    return gulp.src(
            [paths.srcs], {
                base: paths.src_dir
            })
        .pipe(prettify({
            mode: 'VERIFY_AND_WRITE'
        }))
        .pipe(gulp.dest(paths.format_dir));
});

function fileCopy(src, dist) {
    const r = fs.createReadStream(src);
    const w = fs.createWriteStream(dist);
    r.pipe(w);
}

function diff(src, dest) {
    return fs.readFileSync(src, 'utf-8') !== fs.readFileSync(dest, 'utf-8');
}

// 指定されたディレクトリから再帰的にファイルを抽出し、コールバックに投げ込む。
function directoryWork(path, fileCallback) {
    fs.readdir(path, (err, files) => {
        files.forEach((file) => {
            const fullPath = path + '/' + file;
            if (fs.statSync(fullPath).isDirectory()) {
                directoryWork(fullPath, fileCallback); // ディレクトリなら再帰
            } else {
                fileCallback(fullPath); // ファイルならコールバックで通知
            }
        });
    });
}

gulp.task('diff-and-copyfile', () => {
    directoryWork(paths.format_dir, (filePath) => {
        const mainPath = filePath.replace(paths.format_dir, paths.src_dir);
        if (diff(filePath, mainPath)) {
            console.log('Code formatting : ' + mainPath);
            fileCopy(filePath　, mainPath);
        }
    });
});

gulp.task('src-format', (cb) => {
    return runSequence(
        'formating-and-dump',
        'diff-and-copyfile',
        cb
    );
});

// 「開発時にローカルでテスト回す(CIする)ような常駐タスク

gulp.task('all-test-with-notify', () => {
    return gulp.src(['./'])
        .pipe(plumber({
            errorHandler: notify.onError('[(<%=error.plugin %>)]<%= error.message %>')
        }))
        .pipe(through.obj((file, encoding, callback) => {
            runSequence(
                ['test-for-loop'], (error) => {
                    if (error === undefined) {
                        return;
                    }
                    notifier.notify({
                        'title': 'Error running Gulp',
                        'message': '[' + error.plugin + ']\n' + error.message,
                        'sound': true
                    });
                }
            );
            callback(null, file);
        }))
        .pipe(plumber.stop());

});

gulp.task('develop', () => {
    return watch(paths.srcs, {
        ignoreInitial: true,
        readDelay: 500 // 再帰するので抑制を狙ったが…いまいち効力が解らない
    }, (event) => {
        gulp.start('all-test-with-notify');
    });
});

// 本チャン出力まわり

gulp.task('clean_build_result', (cb) => {
    return del([paths.dest_dir + '/index.js'], cb);
});

gulp.task('transpile', () => {
    return browserify({
            entries: paths.main_dir + '/index.js'
        })
        .transform(babelify, {
            presets: ['es2015']
        })
        .bundle()
        .pipe(source('index.js'))
        .pipe(buffer())
        // TODO 可読性重視で一時的に殺す。本番になれば蘇らす。
        //        .pipe(uglify()) // uglifyfyでbrowserify内で出来るのだが、圧縮結果が変わるのでここでやる
        .pipe(gulp.dest(paths.dest_dir));
});

gulp.task('build', (cb) => {
    return runSequence(
        'clean_build_result',
        'test',
        'transpile',
        cb
    );
});