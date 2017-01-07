'use strict';

const ServerUtils = require('../util/server_utils');
const HtmlUtils = require('../util/html_utils');

/*
 * 戦闘結果画面(battleresult.html)のクライアントロジック。
 */
class BattleResultPage {

    constructor(htmlUtils = null) {
        // テスト時にMockして動かせるように。
        if (htmlUtils == null) {
            this._html = new HtmlUtils();
        } else {
            this._html = htmlUtils;
        }
        this._server = new ServerUtils();
    }

    /**
     * OnLoad。
     */
    startUp() {

        // TODO DebugWrite
        const html = this._html;
        alert("screenId:" + html.getScreenId());

    }

}

module.exports = BattleResultPage;