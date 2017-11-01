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
        server.getValue('api/battle/startBattle');
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

        // 初期表示。

        // 味方パーティ&敵パーティの情報を取得。
        const battle = server.getJson('api/battle/getBattle');

        // 初期表示系
        this.viewParties(battle, html);
    }

    viewParties(battle, html) {
        const status = battle.turns.lastTurn.afterStatus;
        this.viewParty(status.mineParty, 1, html);
        this.viewParty(status.enemyParty, 2, html);
    }

    viewParty(party, groupKey, html) {
        let index = 1;
        this.viewBattler(party.owner, groupKey, index, html);
        for (var member of party.members[1]) {
            this.viewBattler(member, groupKey, ++index, html);
        }
    }

    viewBattler(battler, groupKey, index, html) {
        const suffix = '_' + groupKey + '_' + index;
        html.setImageSrcById("battlerImage" + suffix, battler.imageUrl);

        html.setTextById("battlerId" + suffix, battler.twitterId);
        html.setTextById("battlerName" + suffix, battler.screenName);

        html.setTextById("battlerLv" + suffix, battler.level);
        html.setTextById("battlerAttackPoint" + suffix, battler.attackPoint);
        html.setTextById("battlerDefensePoint" + suffix, battler.defensePoint);
        html.setTextById("battlerSpeedPoint" + suffix, battler.speedPoint);
        html.setTextById("battlerMaxHitPoint" + suffix, battler.maxStatus.hitPoint);
        html.setTextById("battlerMaxSpecialPoint" + suffix, battler.maxStatus.specialPoint);
    }

}

module.exports = PartyMakePage;