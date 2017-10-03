package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller

import java.security.Principal

import com.github.kazuhito_m.twitterbattler.primitive.application.{BattlerService, BattleService}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
@RequestMapping(Array("/api/battle"))
class BattleController(
                        gameInfoService: BattleService,
                        battlerService: BattlerService
                      ) {

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattleController])

  @RequestMapping(value = Array("getBattleSceneId"), method = Array(GET, POST))
  def getBattleSceneId(user: Principal): String = gameInfoService.getBattleSceneId(user.getName)

  @RequestMapping(value = Array("makeEnemyAndParty"), method = Array(POST))
  def makeEnemyAndParty(user: Principal) = gameInfoService.makeEnemyAndParty(user.getName)

  @RequestMapping(value = Array("startBattle"), method = Array(GET, POST))
  def startBattle(user: Principal) = gameInfoService.startBattle(user.getName)

  @RequestMapping(value = Array("operationForBattleTurn"), method = Array(GET, POST))
  def operationForBattleTurn(user: Principal) = gameInfoService.operationForBattleTurn(user.getName)

  @RequestMapping(value = Array("okBattleResult"), method = Array(GET, POST))
  def okBattleResult(user: Principal) = gameInfoService.okBattleResult(user.getName)

}
