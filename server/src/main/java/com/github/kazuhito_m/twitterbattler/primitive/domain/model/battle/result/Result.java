package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.result;

public class Result {
    public static final Result NO_RESULT = new Result(false, VictoryOrDefeat.None);

    final boolean enable;
    final VictoryOrDefeat victoryOrDefeat;

    public Result(boolean enable, VictoryOrDefeat victoryOrDefeat) {
        this.enable = enable;
        this.victoryOrDefeat = victoryOrDefeat;
    }

    public boolean enable() {
        return enable;
    }
}
