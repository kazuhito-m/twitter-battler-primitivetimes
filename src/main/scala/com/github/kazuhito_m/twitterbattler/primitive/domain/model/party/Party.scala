package com.github.kazuhito_m.twitterbattler.primitive.domain.model.party

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler

import scala.beans.BeanProperty

class Party(@BeanProperty battlers: List[Battler]) {
}
