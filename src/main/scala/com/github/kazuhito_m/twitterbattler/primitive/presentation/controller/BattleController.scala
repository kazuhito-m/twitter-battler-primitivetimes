package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller

import java.security.Principal

import com.github.kazuhito_m.twitterbattler.primitive.application.{BattleService, BattlerService}
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.Battle
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
@RequestMapping(Array("/api/battle"))
class BattleController(
                        battleService: BattleService,
                        battlerService: BattlerService
                      ) {

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattleController])

  @RequestMapping(value = Array("getBattleSceneId"), method = Array(GET, POST))
  def getBattleSceneId(user: Principal): String = battleService.getBattleSceneId(user.getName)

  @RequestMapping(value = Array("makeEnemyAndParty"), method = Array(POST))
  def makeEnemyAndParty(user: Principal) = battleService.makeEnemyAndParty(user.getName)

  @RequestMapping(value = Array("getBattle") , method = Array(GET))
  def getBattle(user: Principal):Battle = battleService.getBattle(user.getName)

  @RequestMapping(value = Array("startBattle"), method = Array(GET, POST))
  def startBattle(user: Principal) = battleService.startBattle(user.getName)

  @RequestMapping(value = Array("operationForBattleTurn"), method = Array(GET, POST))
  def operationForBattleTurn(user: Principal) = battleService.operationForBattleTurn(user.getName)

  @RequestMapping(value = Array("okBattleResult"), method = Array(GET, POST))
  def okBattleResult(user: Principal) = battleService.okBattleResult(user.getName)

}
