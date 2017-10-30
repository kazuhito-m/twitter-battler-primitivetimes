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

    operationForBattleTurn(partyOperation, e, html, server) {
        // JSON文字列を作成。
        const operationJson = this.createOperationJson(partyOperation);
        // TODO デバッグだから消す
        alert("往診JSOｎ:" + operationJson);
        // バトル開始をサーバに申請。
        server.postValue('api/battle/operationForBattleTurn', operationJson);
        // Let's Battle ！な画面に遷移。
        html.redirect('battle.html');
    }

    createOperationJson(partyOperation) {
        const operation = '$' + partyOperation + '$';
        return `{
          "@class" : "com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.Commands",
          "partyActivity" : {
            "@class" : "com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.PartyActivity${operation}"
          }
        }`;
    }

    /**
     * 「たたかう」クリックイベント。
     */
    operationFight(e, html, server) {
        this.operationForBattleTurn('Fight', e, html, server)
    }

    /**
     * 「こうさん」クリックイベント。
     */
    operationSurrender(e, html, server) {
        this.operationForBattleTurn('Surrender', e, html, server)
    }

    /**
     * OnLoad。
     */
    startUp() {
        const server = this._server;
        const html = this._html;

        // イベント定義。
        html.addClickEventById('operationFight', (e) => this.operationFight(e, html, server));
        html.addClickEventById('operationSurrender', (e) => this.operationSurrender(e, html, server));
    }

}

module.exports = BattlePage;