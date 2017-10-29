package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.result.{NoResult, Result}

class Battle(
              val turns: Turns,
              val result: Result
            ) {

  def this(turns: Turns) {
    this(turns, NoResult)
  }

  def withResult(result: Result): Battle = new Battle(this.turns, result)

  def hasEnded(): Boolean = result.enable;

}
