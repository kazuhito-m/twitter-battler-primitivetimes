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

    const ids = ['playerId', 'playerName', 'playerLv', 'playerAttackPoint', 'playerDefensePoint', 'playerSpeedPoint', 'playerMaxHitPoint', 'playerMaxSpecialPoint'];

    for (let i = 0, len = ids.length; i < len; i++) {
        const span = doc.createElement('span');
        span.id = ids[i];
        span.innerHTML = "なんかかいておこう。" + i;
        body.appendChild(span);
    }

    const img = doc.createElement('img');
    img.setAttribute('id', 'playerImage');
    body.appendChild(img);

    const a1 = doc.createElement('a');
    a1.setAttribute('id', 'execPartyMake');
    body.appendChild(a1);

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
        FakeRequest.respondTo('api/battler/getPlayer', {
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