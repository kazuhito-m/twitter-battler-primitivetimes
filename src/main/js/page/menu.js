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
     * メインメニュー画面のOnLoad
     */
    startUp() {

        const server = this._server;
        const html = this._html;

        // プレイヤー情報を取得。
        const player = server.getJson('api/game/getPlayer');

        // 初期表示系
        html.setImageSrcById("playerImage", player.imageUrl);

        html.setTextById("playerId", player.id);
        html.setTextById("playerName", player.screenName);

        html.setTextById("playerLv", player.level);
        html.setTextById("playerAttackPoint", player.attackPoint);
        html.setTextById("playerDefensePoint", player.defensePoint);
        html.setTextById("playerSpeedPoint", player.speedPoint);
        html.setTextById("playerMaxHitPoint", player.maxHitPoint);
        html.setTextById("playerMaxSpecialPoint", player.maxSpecialPoint);

    }

}

module.exports = MenuPage;