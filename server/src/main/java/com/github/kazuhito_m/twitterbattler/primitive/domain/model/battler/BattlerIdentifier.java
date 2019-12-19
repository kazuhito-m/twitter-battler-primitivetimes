package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler;

import java.util.Objects;

public class BattlerIdentifier {
    public static final BattlerIdentifier EMPTY = new BattlerIdentifier(-1);

    final long value;

    public BattlerIdentifier(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattlerIdentifier that = (BattlerIdentifier) o;
        return value == that.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
