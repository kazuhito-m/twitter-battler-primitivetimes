package com.github.kazuhito_m.twitterbattler.primitive.application

import java.security.Principal

import com.github.kazuhito_m.twitterbattler.primitive.domain.GameInformationService
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
@RequestMapping(Array("/api/game"))
class GameInformationController {

  protected val log: Logger = LoggerFactory.getLogger(classOf[GameInformationController])

  @Autowired
  private val gameInfoService: GameInformationService = null

  @RequestMapping(value = Array("getPlayer"), method = Array(GET, POST))
  def getPlayer(user: Principal) = gameInfoService.getPlayer(user.getName)

  @RequestMapping(value = Array("getBattleSceneId"), method = Array(GET, POST))
  def getBattleSceneId(user: Principal): String = gameInfoService.getBattleSceneId(user.getName)

  @RequestMapping(value = Array("makeEnemyAndParty"), method = Array(GET, POST))
  def makeEnemyAndParty(user: Principal) = gameInfoService.makeEnemyAndParty(user.getName)

}
