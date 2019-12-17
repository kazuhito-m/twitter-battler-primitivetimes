package com.github.kazuhito_m.twitterbattler.primitive.application.repository;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.Battle;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.BattleScene;

public interface BattleRepository {
    /**
     * BattleSceneオブジェクトを保存する。すでにある場合は上書きする。
     */
    void saveBattleScene(String playerTwitterId, BattleScene battleScene);

    /**
     * BattleSceneオブジェクトをIDで削除する。
     */
    void deleteBattleScene(String playerTwitterId);

    /**
     * BattleSceneオブジェクトが存在するかを真偽値で返す。
     */
    boolean isExistsBattleScene(String playerTwitterId);

    /**
     * 現在の「指定ユーザの戦闘画面シーンID」を返す。
     */
    BattleScene getBattleScene(String playerTwitterId);

    Battle createBattle(String playerTwitterId);

    void registerBattle(String playerTwitterId, Battle battle);

    Battle getBattle(String playerTwitterId);
}
