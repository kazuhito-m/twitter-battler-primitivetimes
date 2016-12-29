'use strict';
/*
 * メインメニュー画面(いまのところindex.html)のクライアントロジック。
 */

const ServerUtils = require('./server_utils');

function setTextById(id, text) {
    const target = document.getElementById(id);
    target.innerHTML = text;
}

/** メインメニュー画面のOnLoad */
module.exports = function() {

    const utils = new ServerUtils();

    // プレイヤー情報を取得。
    const player = utils.getJson('api/game/getPlayer');
    console.log(player)

    // 初期表示系
    setTextById("playerId", player.id);
    setTextById("playerLv", player.level);

}