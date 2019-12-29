package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler;

public class BattlerStatus {
    final long hitPoint;
    final long specialPoint;

    public BattlerStatus(long hitPoint, long specialPoint) {
        this.hitPoint = hitPoint;
        this.specialPoint = specialPoint;
    }
}
