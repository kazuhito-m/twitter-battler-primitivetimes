package com.github.kazuhito_m.twitterbattler.primitive.domain.model.party

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler

class Party(var battlers: List[Battler]) extends Serializable {
}
