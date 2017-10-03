'use strict';

const ServerUtils = require('../util/server_utils');
const HtmlUtils = require('../util/html_utils');

/*
 * 戦闘画面(battle.html)のクライアントロジック。
 */
class BattlePage {

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
     * 「たたかう」クリックイベント。
     */
    operationForBattleTurn(e, html, server) {
        // バトル開始をサーバに申請。
        server.getValue('api/battle/operationForBattleTurn');
        // Let's Battle ！な画面に遷移。
        html.redirect('battle.html');
    }

    /**
     * OnLoad。
     */
    startUp() {
        const server = this._server;
        const html = this._html;

        // イベント定義。
        html.addClickEventById('operationForBattleTurn', (e) => this.operationForBattleTurn(e, html, server));

    }

}

module.exports = BattlePage;