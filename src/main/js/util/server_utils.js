'use strict';

/**
 * 主にサーバとの通信を制御するUtils暮らす。
 */
class ServerUtils {

    constructor() {
        this._xhr = new XMLHttpRequest();
    }

    /**
     * URL指定でサーバから結果をJSONオブジェクトにして返す。
     */
    getJson(url) {
        const xhr = this._xhr;
        xhr.open('POST', url, false);
        xhr.send();

        const jsonText = xhr.responseText;
        console.log(jsonText);

        const jsonObj = JSON.parse(jsonText);

        return jsonObj;
    }

}

module.exports = ServerUtils;