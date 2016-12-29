'use strict';

/*
 * メインメニュー画面(いまのところindex.html)のクライアントロジック。
 */

function setTextById(id, text) {
    const target = document.getElementById(id);
    target.innerHTML = text;
}

/** メインメニュー画面のOnLoad */
module.exports = function() {
    // 初期表示系
    setTextById("playerId", "よくわからないがまずはJS側から表示できるまで。");
    setTextById("playerLv", "9999");

}