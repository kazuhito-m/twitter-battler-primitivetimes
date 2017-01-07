'use strict';

/**
 * 主にサーバとの通信を制御するUtils暮らす。
 */
class ServerUtils {

    constructor() {
        this._xhr = new XMLHttpRequest();
    }

    /**
     * URL指定でサーバから結果を値(オブジェクト)としてそのまま返す。
     */
    getValue(url) {
        const xhr = this._xhr;
        xhr.open('POST', url, false);
        xhr.send();

        const jsonText = xhr.responseText;
        return jsonText;
    }

    /**
     * URL指定でサーバから結果をJSONオブジェクトにして返す。
     */
    getJson(url) {
        const jsonText = this.getValue(url);
        const jsonObj = JSON.parse(jsonText);
        return jsonObj;
    }

}

module.exports = ServerUtils;