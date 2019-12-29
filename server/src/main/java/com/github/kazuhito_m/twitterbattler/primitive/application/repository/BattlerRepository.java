package com.github.kazuhito_m.twitterbattler.primitive.application.repository;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerIdentifier;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.RandomBattlerPickup;

public interface BattlerRepository extends RandomBattlerPickup {
    BattlerIdentifier convertTwitterIdToId(String twitterId);

    /**
     * 指定されたId(TwitterID)から、ゲームキャラクタデータを作成、同時に保存する。
     */
    Battler create(BattlerIdentifier identifier);

    /**
     * Battlerオブジェクトを保存する。すでにある場合は上書きする。
     */
    void register(Battler battler);

    /**
     * BattlerオブジェクトをIDで取得する。
     */
    Battler get(BattlerIdentifier identifier);

    /**
     * BattlerオブジェクトをIDで削除する。
     */
    void delete(BattlerIdentifier identifier);

    /**
     * Battlerオブジェクトが存在するかを真偽値で返す。
     */
    boolean isExists(BattlerIdentifier identifier);
}
