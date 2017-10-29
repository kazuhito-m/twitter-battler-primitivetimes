package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.activitiy.Activities
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.party.PartyStatus

class Turn(
            val beforeStatus: PartyStatus,
            val afterStatus: PartyStatus,
            val activities: Activities
          ) {

  def this(status: PartyStatus) = {
    this(status, status, new Activities)
  }

}
