'use strict';

const ServerUtils = require('../util/server_utils');
const HtmlUtils = require('../util/html_utils');

/*
 * 戦闘画面(battle.html)のクライアントロジック。
 */
class BattlePage {

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

module.exports = BattlePage;