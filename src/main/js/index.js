'use strict';

// クラスインポート。
const HtmlUtils = require('./util/html_utils');

const MenuPage = require('./page/menu');

/** HTMLとの境界、エントリポイント。 */
window.onload = function() {
    const html = new HtmlUtils();
    const screenId = html.getScreenId();
    let page;
    switch (screenId) {
        case 'main':
            page = new MenuPage();
            break;
        default:
            alert('Invalid screenId');
            break;
    }
    page.startUp();
}