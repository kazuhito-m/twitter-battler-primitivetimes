'use strict';

const assert = require('power-assert');
const MenuPage = require('../../main/js/page/menu');

/**
 * メインメニュー画面(いまのところindex.html)のクライアントロジックのテスト。
 */
describe('MenuPage', () => {

    it('とりあえずnew出来る(コンストラクタのテスト)', () => {
        const sut = new MenuPage();
    });

    it('MockingしたUtilityオブジェクトの呼び出しを確認', () => {
        class MockServerUtils {}
        class MockHtmlUtils {}
        const sut = new MenuPage();
    });

});