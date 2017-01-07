'use strict';

const ServerUtils = require('../util/server_utils');
const HtmlUtils = require('../util/html_utils');

/*
 * 戦闘結果画面(battleresult.html)のクライアントロジック。
 */
class BattleResultPage {

    constructor(htmlUtils = null) {
        // テスト時にMockして動かせるように。
        if (htmlUtils == null) {
            this._html = new HtmlUtils();
        } else {
            this._html = htmlUtils;
        }
        this._server = new ServerUtils();
    }

    /**
     * 結果に対しての「”OK”ボタン」クリックイベント。
     */
    okBattleResult(e, html, server) {
        // バトル開始をサーバに申請。
        server.getValue('api/game/okBattleResult');
        // Let's Battle ！な画面に遷移。
        html.redirect('index.html');
    }

    /**
     * OnLoad。
     */
    startUp() {
        const server = this._server;
        const html = this._html;

        // イベント定義。
        html.addClickEventById('okBattleResult', (e) => this.okBattleResult(e, html, server));

    }

}

module.exports = BattleResultPage;