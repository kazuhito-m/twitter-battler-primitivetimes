package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.party.Party

import scala.beans.BeanProperty

class Battle(
              @BeanProperty mineParty: Party,
              @BeanProperty enemyParty: Party
            ) {

}
