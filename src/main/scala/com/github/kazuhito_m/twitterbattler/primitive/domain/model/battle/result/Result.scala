package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.result

class Result(
              val enable: Boolean,
              val victoryOrDefeat: VictoryOrDefeat
            ) {
}

object NoResult extends Result(false, VictoryOrDefeat.None)
