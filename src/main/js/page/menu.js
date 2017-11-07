'use strict';

const ServerUtils = require('../util/server_utils');
const HtmlUtils = require('../util/html_utils');

/*
 * メインメニュー画面(いまのところindex.html)のクライアントロジック。
 */
class MenuPage {

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
     * 「対戦を開始」クリックイベント。
     */
    execPartyMake(e, html, server) {
        // パーティー作成＆対戦相手決定を開始。
        server.postValue('api/battle/makeEnemyAndParty');
        // パーティー作成＆対戦相手決定画面に遷移。
        html.redirect('partymake.html');
    }

    /**
     * メインメニュー画面のOnLoad
     */
    startUp() {

        const server = this._server;
        const html = this._html;

        // イベント定義。
        html.addClickEventById('execPartyMake', (e) => this.execPartyMake(e, html, server));

        // 初期表示。

        // プレイヤー情報を取得。
        const player = server.getJson('api/battler/getPlayer');
        // 初期表示系
        this.viewPlayer(player, html);

    }

    viewPlayer(player, html) {
        html.setImageSrcById("playerImage", player.imageUrl);

        html.setTextById("playerId", player.twitterId);
        html.setTextById("playerName", player.screenName);

        html.setTextById("playerLv", player.level);
        html.setTextById("playerAttackPoint", player.attackPoint);
        html.setTextById("playerDefensePoint", player.defensePoint);
        html.setTextById("playerSpeedPoint", player.speedPoint);
        html.setTextById("playerMaxHitPoint", player.maxStatus.hitPoint);
        html.setTextById("playerMaxSpecialPoint", player.maxStatus.specialPoint);
    }

}

module.exports = MenuPage;