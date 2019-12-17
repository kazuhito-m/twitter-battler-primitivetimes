package com.github.kazuhito_m.twitterbattler.primitive.application.repository;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerIdentifier;

import java.util.List;

public interface BattlerRepository {
    BattlerIdentifier convertTwitterIdToId(String twitterId);

    /**
     * 指定されたId(TwitterID)から、ゲームキャラクタデータを作成、同時に保存する。
     */
    Battler create(BattlerIdentifier identifier);

    /**
     * Battlerオブジェクトを保存する。すでにある場合は上書きする。
     */
    void registar(Battler battler);

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

    /**
     * 指定したバトラーIDの友達バトラーをランダムに人数指定で取得する。
     */
    List<Battler> randomFriendBattlers(BattlerIdentifier identifier, int size);

    /**
     * ランダムに一人のバトラーを取得する。
     */
    Battler randomOneBattler();
}
