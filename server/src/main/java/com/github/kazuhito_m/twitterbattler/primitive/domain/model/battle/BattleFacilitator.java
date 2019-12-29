package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.Commands;

public class BattleFacilitator {
    public Battle proceedNextTurn(Commands commands, Battle battle) {
        return battle; // TODO 今は仮実装
    }
}
