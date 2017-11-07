package com.github.kazuhito_m.twitterbattler.primitive.domain.model.party

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.{Battler, BattlerRepository}

class PartyFactory(battlerRepository: BattlerRepository) {

  private val PARTY_MEMBER_COUNT = 5;

  def createPlayerParty(player: Battler): Party = createParty(player)

  def createEnemyParty(): Party = createParty(battlerRepository.randomOneBattler())

  private def createParty(player: Battler): Party = new Party(
    player,
    battlerRepository.randomFriendBattlers(player.identifier, PARTY_MEMBER_COUNT - 1)
  )

}
