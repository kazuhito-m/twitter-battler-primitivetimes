'use strict';

const assert = require('power-assert');
const Condition = require('../../main/js/condition');

/**
 * 検索条件VOクラスのテスト。
 *
 * 責務的には「VOなので少量の処理しかない」が、JSのプロパティ形式が不安なので、確認用。
 * 疑問を解決し経験を積むため、なるだけ冗長でもいいから丁寧にテスト書く。
 */

describe('Condition', () => {

    it('プロパティ確認、初期値はnullで統一', () => {
        const sut = new Condition();
        assert.equal(sut.productName, null);
        assert.equal(sut.miuraUse, null);
    });

    it('文字列プロパティ"productName"に値を設定、取得、変更出来る', () => {
        const sut = new Condition();

        sut.productName = '最初に設定した値が取得できる';
        assert.equal(sut.productName, '最初に設定した値が取得できる');

        sut.productName = '変更した値も取得できる';
        assert.equal(sut.productName, '変更した値も取得できる');
    })

    it('論理値プロパティ"miuraUse"に値を設定、取得、変更出来る', () => {
        const sut = new Condition();

        sut.miuraUse = true;
        assert.equal(sut.miuraUse, true);

        sut.miuraUse = false;
        assert.equal(sut.miuraUse, false);
    })

    it('論理値プロパティ"miuraUse"には、nullも設定可能', () => {
        const sut = new Condition();

        sut.miuraUse = true;
        assert.equal(sut.miuraUse, true);

        sut.miuraUse = null;
        assert.equal(sut.miuraUse, null);
    })

    it('論理値プロパティ"miuraUse"には、論理値以外設定不可能', () => {
        const sut = new Condition();
        assert.throws(() => {
            sut.miuraUse = "論理値以外の値";
        }, TypeError, 'プロパティ : miuraUse は boolean 型以外はsetできません。');
    })

});