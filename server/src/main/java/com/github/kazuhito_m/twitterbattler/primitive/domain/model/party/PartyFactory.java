package com.github.kazuhito_m.twitterbattler.primitive.domain.model.party;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.RandomBattlerPickup;

public class PartyFactory {
    private static final int PARTY_MEMBER_COUNT = 5;

    final RandomBattlerPickup pickup;

    public PartyFactory(RandomBattlerPickup pickup) {
        this.pickup = pickup;
    }

    public Party createPlayerParty(Battler player) {
        return createParty(player);
    }

    public Party createEnemyParty() {
        return createParty(pickup.randomOneBattler());
    }

    private Party createParty(Battler player) {
        return new Party(
                player,
                pickup.randomFriendBattlers(
                        player.identifier(),
                        PARTY_MEMBER_COUNT - 1
                )
        );
    }
}
