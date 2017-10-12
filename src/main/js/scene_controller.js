'use strict';

// クラスインポート。
const HtmlUtils = require('./util/html_utils');
const ServerUtils = require('./util/server_utils');

const MenuPage = require('./page/menu');
const PartyMakePage = require('./page/partymake');
const BattlePage = require('./page/battle');
const BattleResultPage = require('./page/battleresult');

/*
 * 「サーバの状態」と「表示しているHTML」を制御するコントローラ。
 */
class SceneController {

    // HTMLのID(screenId)と、それに対応するページのJSのペア。
    get SCREEN_ID_TO_PAGE_SCRIPT() {
        return {
            'menu': new MenuPage(),
            'partymake': new PartyMakePage(),
            'battle': new BattlePage(),
            'battleresult': new BattleResultPage()
        };
    }

    // シーンIDが無い(戦闘中とかじゃない)時にも、行き来できるHTMLのID群。
    get VALID_SCREEN_IDS() {
        return ['menu'];
    }

    /**
     * サーバ上のシーンID(sceneId)と、現在表示しているHTMLのID(screenId)の組み合わせから、
     * 「正当なものか」を判断し、NGの場合「正しいHTML名(リダイレクト用)」を返す。
     * ただしければnullを返す。
     */
    getCorrectHtmlName(sceneId, screenId) {
        // シーンIDからプレフィックスを取得。
        if (!sceneId) sceneId = '';
        const fixedSceneId = sceneId.replace(/"/g, ''); // ダブルクオートは削除
        const scenePrefix = fixedSceneId.split(':')[0];

        // プレフィックスが一致する(サーバとHTMLの画面状態が完全一致)なら、問題なしでnull返し。
        if (screenId === scenePrefix) return null;
        // プレフィックスとHTML側IDが一致しないが、プレフィックス自体があるなら、是正のためプレフィックスと同じHTMLに遷移。
        if (scenePrefix) return scenePrefix + ".html";
        // プレフィックスは無い(サーバ側はバトル系画面じゃない)が、ゲームしている時に自由に行き来出来る画面ならOK。
        if (this.VALID_SCREEN_IDS.indexOf(screenId) >= 0) return null;
        // サーバがバトル状態じゃないし、行ったらイカン画面なら、強制的にindex.html。
        return 'index.html';
    }

    /**
     * 自身表示されているHTMLページごとに対応するJSを読み込み・実行する。
     */
    loadPage(screenId) {
        const page = this.SCREEN_ID_TO_PAGE_SCRIPT[screenId];
        if (page) {
            page.startUp();
        } else {
            alert('Invalid screenId');
        }
    }

    /**
     * ページが表示された直後の「HTML側からのエントリポイント」。
     * ※ここのみ、HTMLやDOMへの実際のアクセスが行われる。(とはいえ、HtmlUtils経由だが）
     */
    startUp() {
        const html = new HtmlUtils();
        const server = new ServerUtils;

        // HTML側のID を取得。
        const screenId = html.getScreenId();
        // サーバ側の「シーンID」と、表示HTMLが合ってるかをチェックし、不整合あればリダイレクトで移動する。
        const sceneId = server.getValue('api/battle/getBattleSceneId');

        console.log("sceneId:" + sceneId + ",screenId:" + screenId);

        // シーンIDのプレフィックスとHTMLが一致してるかを判定。
        const redirectHtml = this.getCorrectHtmlName(sceneId, screenId);
        if (redirectHtml) {
            // 一致してなくば、正しいページへリダイレクト。
            console.log("RedirectTo: " + redirectHtml);
            html.redirect(redirectHtml);
        } else {
            // 自身表示されているHTMLページごとに対応するJSを読み込み・実行する。
            this.loadPage(screenId);
        }
    }

}
module.exports = SceneController; // テスト出来るように、一応Exportしておく。