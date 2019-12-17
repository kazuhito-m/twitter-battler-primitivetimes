package com.github.kazuhito_m.twitterbattler.primitive.domain.model.party;

public class PartyStatus {
    final Party mineParty;
    final Party enemyParty;

    public PartyStatus(Party mineParty, Party enemyParty) {
        this.mineParty = mineParty;
        this.enemyParty = enemyParty;
    }
}
