'use strict';

// クラスインポート。
const SceneController = require('./scene_controller');

/** HTMLとの境界、エントリポイント。 */
window.onload = function() {
    (new SceneController).startUp();
}