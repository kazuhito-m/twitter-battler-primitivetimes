'use strict';

const assert = require('power-assert');

const FakeRequest = require('fake-request');
const MockBrowser = require('mock-browser').mocks.MockBrowser;

const MenuPage = require('../../../main/js/page/menu');
const HtmlUtils = require('../../../main/js/util/html_utils');

/**
 * HTMLのdomモデルをMockingしたものを返す。
 */
function createMockDocument() {
    const mockBrowser = new MockBrowser();
    const doc = mockBrowser.getDocument();
    const body = doc.getElementsByTagName("body").item(0);

    const span1 = doc.createElement('span');
    span1.id = 'playerId';
    span1.innerHTML = "なんかかいておこう。";
    body.appendChild(span1);

    const span2 = doc.createElement('span');
    span2.setAttribute('id', 'playerLv');
    span2.innerHTML = "なんｋなかいておこう２";
    body.appendChild(span2);

    return doc;
}

/**
 * メインメニュー画面(いまのところindex.html)のクライアントロジックのテスト。
 */
describe('MenuPage', () => {

    const AbstractBrowser = require('mock-browser').delegates.AbstractBrowser;

    before((done) => {
        FakeRequest.mock();
        done();
    });
    beforeEach((done) => {
        FakeRequest.reset();
        done();
    });
    after((done) => {
        FakeRequest.restore();
        done();
    });

    // テスト開始。

    it('とりあえずnew出来る(コンストラクタのテスト)', () => {
        const sut = new MenuPage(new HtmlUtils(new MockBrowser()));
    });

    it('MockingしたUtilityオブジェクトの呼び出しを確認', () => {

        // サーバ送信のMocking。
        FakeRequest.respondTo('api/game/getPlayer', {
            readyState: 4,
            status: 200,
            responseText: '{"id":"test_id","level":999}',
            responseHeaders: 'content-type: text/json'
        });

        // ブラウザ中のHTMLをMocking。
        const doc = createMockDocument();

        // 対象作成。
        const sut = new MenuPage(new HtmlUtils(doc));

        // 実行
        sut.startUp();

        // 検証
        assert.equal(doc.getElementById('playerId').innerHTML, 'test_id');
        assert.equal(doc.getElementById('playerLv').innerHTML, '999');

    });

});