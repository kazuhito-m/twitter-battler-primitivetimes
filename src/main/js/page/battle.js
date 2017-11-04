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
        // バトル開始をサーバに申請。
        server.postValue('api/battle/operationForBattleTurn', operationJson);
        // 自画面を再描画。
        html.redirect('battle.html');
    }

    createOperationJson(partyOperation) {
        return `{
          "@class" : "com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.Commands",
          "partyActivity" : {
            "@class" : "com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.PartyActivity$${partyOperation}$"
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

        // 初期表示。

        // 味方パーティ&敵パーティの情報を取得。
        const battle = server.getJson('api/battle/getBattle');

        // 初期表示系
        this.viewBattle(battle, html);
    }

    viewBattle(battle, html) {
        const turn = battle.turns.lastTurn;
        // ターン最初の状態を表示。
        this.viewParties(turn.beforeStatus, html);
        // メッセージなど「ターンのやり取り」を表示
        this.animationTurn(turn);
        // ターン最後の状態を表示。
        this.viewParties(turn.afterStatus, html);
        // コマンドを表示。
        html.visibleChangeById('commands', true);
    }

    viewParties(partyStatus, html) {
        this.viewMineParty(partyStatus.mineParty, html);
        this.viewEnemyParty(partyStatus.enemyParty, html);
    }

    viewMineParty(party, html) {
        let index = 1;
        this.viewMineBattler(party.owner, index, html);
        for (var member of party.members[1]) {
            this.viewMineBattler(member, ++index, html);
        }
    }

    viewMineBattler(battler, index, html) {
        const suffix = '_' + index;
        html.setImageSrcById("battlerImage" + suffix, battler.imageUrl);
        html.setTextById("battlerId" + suffix, battler.twitterId);
        html.setTextById("battlerHitPoint" + suffix, battler.maxStatus.hitPoint);
        html.setTextById("battlerSpecialPoint" + suffix, battler.maxStatus.specialPoint);
        html.setTextById("battlerLv" + suffix, battler.level);
    }

    viewEnemyParty(party, html) {
        let index = 1;
        this.viewEnemyBattler(party.owner, index, html);
        for (var member of party.members[1]) {
            this.viewEnemyBattler(member, ++index, html);
        }
    }

    viewEnemyBattler(battler, index, html) {
        const suffix = '_' + index;
        html.setImageSrcById("enemyImage" + suffix, battler.imageUrl);
        html.setTextById("enemyId" + suffix, battler.twitterId);
    }

    animationTurn(turn) {
        // TODO 実装
        alert('ターンのアニメ表示');
	console.log('turn:' + turn);
    }

}

module.exports = BattlePage;
