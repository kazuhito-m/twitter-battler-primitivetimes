package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler;

import java.time.LocalDateTime;

public class Battler {
    final BattlerIdentifier identifier;
    final String twitterId;
    final String screenName;
    final String biography;
    final int level;
    final BattlerStatus maxStatus;
    final long attackPoint;
    final long defensePoint;
    final long speedPoint;
    final String imageUrl;
    final LocalDateTime firstSignUpDate;
    final LocalDateTime generateDate;
    final BattlerStatus nowStatus;

    public Battler(BattlerIdentifier identifier, String twitterId, String screenName, String biography, int level, BattlerStatus maxStatus, long attackPoint, long defensePoint, long speedPoint, String imageUrl, LocalDateTime firstSignUpDate, LocalDateTime generateDate, BattlerStatus nowStatus) {
        this.identifier = identifier;
        this.twitterId = twitterId;
        this.screenName = screenName;
        this.biography = biography;
        this.level = level;
        this.maxStatus = maxStatus;
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
        this.speedPoint = speedPoint;
        this.imageUrl = imageUrl;
        this.firstSignUpDate = firstSignUpDate;
        this.generateDate = generateDate;
        this.nowStatus = nowStatus;
    }

    public BattlerIdentifier identifier() {
        return identifier;
    }

    public LocalDateTime generateDate() {
        return generateDate;
    }

    public int level() {
        return level;
    }
}
