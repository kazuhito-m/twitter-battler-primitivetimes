package com.github.kazuhito_m.twitterbattler.primitive.domain.model.party;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler;

import java.util.List;

public class Party {
    final Battler owner;
    final List<Battler> members;

    public Party(Battler owner, List<Battler> members) {
        this.owner = owner;
        this.members = members;
    }
}
