'use strict';

/**
 * 主にサーバとの通信を制御するUtils暮らす。
 */
class ServerUtils {

    constructor() {
        this._xhr = new XMLHttpRequest();
    }

    getJson(url) {
        const xhr = this._xhr;
        xhr.open('POST', url, false);
        xhr.send();

        const jsonText = xhr.responseText;
        const jsonObj = JSON.parse(jsonText);

        return jsonObj;
    }

}

module.exports = ServerUtils;