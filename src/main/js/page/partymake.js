'use strict';

const ServerUtils = require('../util/server_utils');
const HtmlUtils = require('../util/html_utils');

/*
 * パーティ編成＆対戦相手決定画面(partymake.html)のクライアントロジック。
 */
class PartyMakePage {

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
     * 「バトルスタート！」クリックイベント。
     */
    startBattle(e, html, server) {
        // バトル開始をサーバに申請。
        server.getValue('api/game/startBattle');
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
        html.addClickEventById('startBattle', (e) => this.startBattle(e, html, server));

    }

}

module.exports = PartyMakePage;