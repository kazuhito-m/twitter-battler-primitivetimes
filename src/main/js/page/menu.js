'use strict';

const ServerUtils = require('../util/server_utils');
const HtmlUtils = require('../util/html_utils');

/*
 * メインメニュー画面(いまのところindex.html)のクライアントロジック。
 */
class MenuPage {

    constructor(serverUtils = null, htmlUtils = null) {
        if (serverUtils == null) {
            this._server = new ServerUtils();
        } else {
            this._server = serverUtils;
        }
        if (htmlUtils == null) {
            this._html = new HtmlUtils();
        } else {
            this._html = htmlUtils;
        }
    }

    /**
     * メインメニュー画面のOnLoad
     */
    startUp() {

        const server = this._server;
        const html = this._html;

        // プレイヤー情報を取得。
        const player = server.getJson('api/game/getPlayer');
        console.log(player)

        // 初期表示系
        html.setTextById("playerId", player.id);
        html.setTextById("playerLv", player.level);

    }

}

module.exports = MenuPage;