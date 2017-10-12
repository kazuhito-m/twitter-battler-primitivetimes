package com.github.kazuhito_m.twitterbattler.primitive.domain.model.party

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler

class Party(var owner: Battler, var members: List[Battler]) extends Serializable {
  def battlers(): List[Battler] = owner :: members
}
