package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler;

import java.util.List;

public interface RandomBattlerPickup {
    /**
     * ランダムに一人のバトラーを取得する。
     */
    Battler randomOneBattler();

    /**
     * 指定したバトラーIDの友達バトラーをランダムに人数指定で取得する。
     */
    List<Battler> randomFriendBattlers(BattlerIdentifier identifier, int size);
}
