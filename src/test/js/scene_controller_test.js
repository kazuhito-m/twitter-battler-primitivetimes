'use strict';

const assert = require('power-assert');
const SceneController = require('../../main/js/scene_controller');

describe('index.js(SceneController)のテスト', () => {

    it('サーバ側にIDなし、HTML側がmain(メニューページ)の場合、正しいとみなし書き換えない', () => {
        const sut = new SceneController();
        // 実行 & 検証
        assert.equal(sut.getCorrectHtmlName(null, 'menu'), null);
        assert.equal(sut.getCorrectHtmlName('', 'menu'), null);
    });

    it('サーバ側にIDが「パーティ作成画面(1/2)」、HTML側がmain(メニューページ)の場合、おかしいので正しいページ名を返す', () => {
        const sut = new SceneController();
        // 実行 & 検証
        assert.equal(sut.getCorrectHtmlName('partymake:01', 'menu'), 'partymake.html');
        assert.equal(sut.getCorrectHtmlName('partymake:02', 'menu'), 'partymake.html');
    });

    it('サーバ側にIDが「戦闘画面(1/2)」、HTML側がmain(メニューページ)の場合、おかしいので正しいページ名を返す', () => {
        const sut = new SceneController();
        // 実行 & 検証
        assert.equal(sut.getCorrectHtmlName('battle:01', 'menu'), 'battle.html');
        assert.equal(sut.getCorrectHtmlName('battle:02', 'menu'), 'battle.html');
    });

    it('サーバ側にIDが「戦闘結果画面」、HTML側がmain(メニューページ)の場合、おかしいので正しいページ名を返す', () => {
        const sut = new SceneController();
        // 実行 & 検証
        assert.equal(sut.getCorrectHtmlName('battleresult:01', 'menu'), 'battleresult.html');
    });

    it('サーバ側にIDが「パーティ作成画面(1/2)」、HTML側が「同じく」の場合、おかしいので正しいページ名を返す', () => {
        const sut = new SceneController();
        // 実行 & 検証
        assert.equal(sut.getCorrectHtmlName('partymake:01', 'partymake'), null);
        assert.equal(sut.getCorrectHtmlName('partymake:02', 'partymake'), null);
    });

    it('サーバから帰ってきたScineIDがダブルクオート付きでも適切に処理出来る', () => {
        const sut = new SceneController();
        // 実行 & 検証
        assert.equal(sut.getCorrectHtmlName('"partymake:01"', 'menu'), 'partymake.html');
        assert.equal(sut.getCorrectHtmlName('""partymake:01""', 'menu'), 'partymake.html');
    });

});