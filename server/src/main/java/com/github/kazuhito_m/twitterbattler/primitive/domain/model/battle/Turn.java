package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.activitiy.Activities;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.party.PartyStatus;

public class Turn {
    final PartyStatus beforeStatus;
    final PartyStatus afterStatus;
    final Activities activities;

    public Turn(PartyStatus beforeStatus, PartyStatus afterStatus, Activities activities) {
        this.beforeStatus = beforeStatus;
        this.afterStatus = afterStatus;
        this.activities = activities;
    }
}
