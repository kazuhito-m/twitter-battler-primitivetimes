package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command;

public class Commands {
    final PartyActivity partyActivity;

    public Commands(PartyActivity partyActivity) {
        this.partyActivity = partyActivity;
    }

    public PartyActivity partyActivity() {
        return partyActivity;
    }
}
