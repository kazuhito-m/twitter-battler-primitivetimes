'use strict';

// クラスインポート。
const HtmlUtils = require('./util/html_utils');
const ServerUtils = require('./util/server_utils');

const MenuPage = require('./page/menu');

/*
 * 「サーバの状態」と「表示しているHTML」を制御するコントローラ。
 */
class SceneController {

    // シーンIDのプレフィックス(２文字)と、screenId(HTMLファイルの拡張子以外)の組みあわせ辞書。
    get ID_DICTIONARY() {
        return {
            'PM': 'partymake',
            'BT': 'battle',
            'BR': 'battleresult'
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
        const scenePrefix = sceneId.split(':')[0];

        // プレフィックスが一致するのに、一致しないHTMLを表示していれば、正しいHTML名を返す。
        for (const spf in this.ID_DICTIONARY) {
            const scId = this.ID_DICTIONARY[spf];
            if (scenePrefix === spf && screenId !== scId) {
                return scId + '.html'
            }
        }

        // 上記ループのプレフィックスから漏れた場合、HTMLのIDが「バトル以外で許されてるページ」でないなら、強制的にメニューに返す。
        for (const okId of this.VALID_SCREEN_IDS) {
            if (okId === screenId) return null;
        }
        return 'index.html';
    }

    /**
     * 自身表示されているHTMLページごとに対応するJSを読み込み・実行する。
     */
    loadPage(screenId) {
        let page;
        switch (screenId) {
            case 'menu':
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