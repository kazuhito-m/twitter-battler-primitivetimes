'use strict';

// クラスインポート。
const HtmlUtils = require('./util/html_utils');
const ServerUtils = require('./util/server_utils');

const MenuPage = require('./page/menu');

/*
 * 「サーバの状態」と「表示しているHTML」を制御するコントローラ。
 */
class SceneController {

    /**
     * サーバ上のシーンID(sceneId)と、現在表示しているHTMLのID(screenId)の組み合わせから、
     * 「正当なものか」を判断し、NGの場合「正しいHTML名(リダイレクト用)」を返す。
     * ただしければnullを返す。
     */
    getCorrectHtmlName(sceneId, screenId) {
        // TODO デバッグのため、Logを多量に出しているので、実装終わったら消す。
        console.log("sceneId : " + sceneId);
        console.log("screenId : " + screenId);
        const isKaramoji = (sceneId === "");
        console.log("から文字と判定しているか : " + isKaramoji);

        // シーンIDからプレフィックスを取得。
        const scenePrefix = sceneId.split(':')[0]
        console.log("scenePrefix : " + scenePrefix);

        // TODO 仮実装。正しい判定を実装。
        return null;
    }

    /**
     * 自身表示されているHTMLページごとに対応するJSを読み込み・実行する。
     */
    loadPage(screenId) {
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
        const sceneId = server.getValue('api/game/getBattleSceneId');

        // シーンIDのプレフィックスとHTMLが一致してるかを判定。してなくばリダイレクト。
        const redirectHtml = this.getCorrectHtmlName(sceneId, screenId);
        if (redirectHtml) html.redirect(redirectHtml);

        // 自身表示されているHTMLページごとに対応するJSを読み込み・実行する。
        this.loadPage(screenId)
    }

}
module.exports = SceneController; // テスト出来るように、一応Exportしておく。

/** HTMLとの境界、エントリポイント。 */
window.onload = function() {
    (new SceneController).startUp();
}