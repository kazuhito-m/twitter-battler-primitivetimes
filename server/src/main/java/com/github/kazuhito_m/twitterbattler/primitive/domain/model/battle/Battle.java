package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.result.Result;

public class Battle {
    final Turns turns;
    final Result result;

    public Battle(Turns turns, Result result) {
        this.turns = turns;
        this.result = result;
    }


    public Battle withResult(Result result) {
        return new Battle(this.turns, result);
    }

    public boolean hasEnded() {
        result.enable();
    }

}
