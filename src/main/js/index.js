'use strict';

const page_main = require('./page_main');

/**
 * metaタグを名前指定でcontent値を取得する。
 */
function getMetaContent(name) {
    var tags = document.getElementsByTagName('meta');
    for (let i = 0; i < tags.length; i++) {
        let meta = tags[i];
        if (meta.getAttribute("name") === name) {
            return meta.getAttribute("content");
        }
    }
    return "";
}

/**
 * メタタグから「現在の画面ID（画面の抽象名）を取得する。
 */
function getScreenId() {
    return getMetaContent('screanId');
}

/** HTMLとの境界、エントリポイント。 */
window.onload = function() {
    const screenId = getScreenId();
    switch (screenId) {
        case 'main':
            page_main();
            break;
        default:
            alert('Invalid screanId');
            break;
    }
}