package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command

class Commands(val partyActivity: PartyActivity) {

  // default constructor for Controller
  def this() = this(PartyActivity.Fight)
}
