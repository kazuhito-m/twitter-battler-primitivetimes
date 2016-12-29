'use strict';

/**
 * HTMLに関する操作(プラスこのアプリ固有の操作を少し)を集めたUtilityクラス。
 */
class HtmlUtils {

    constructor(doc = null) {
        if (doc === null) {
            this._document = window.document;
        } else {
            this._document = doc;
        }
    }

    /**
     * metaタグを名前指定でcontent値を取得する。
     */
    getMetaContent(name) {
        const document = this._document;
        const tags = document.getElementsByTagName('meta');
        for (let i = 0; i < tags.length; i++) {
            let meta = tags[i];
            if (meta.getAttribute("name") === name) {
                return meta.getAttribute("content");
            }
        }
        return '';
    }

    /**
     * メタタグから「現在の画面ID（画面の抽象名）を取得する。
     */
    getScreenId() {
        return this.getMetaContent('screenId');
    }

    /**
     * ID指定でDOMオブジェクトに値をセットする。
     */
    setTextById(id, text) {
        const document = this._document;
        const target = document.getElementById(id);
        target.innerHTML = text;
    }

}

module.exports = HtmlUtils;