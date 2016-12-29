'use strict';

const ServerUtils = require('../server_utils');
const HtmlUtils = require('../util/html_utils');

/*
 * メインメニュー画面(いまのところindex.html)のクライアントロジック。
 */
class MenuPage {

    /** メインメニュー画面のOnLoad */
    startUp() {

        const utils = new ServerUtils();
        const html = new HtmlUtils();

        // プレイヤー情報を取得。
        const player = utils.getJson('api/game/getPlayer');
        console.log(player)

        // 初期表示系
        html.setTextById("playerId", player.id);
        html.setTextById("playerLv", player.level);

    }

}

module.exports = MenuPage;