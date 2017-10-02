package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller

import java.security.Principal

import com.github.kazuhito_m.twitterbattler.primitive.application.BattlerService
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
@RequestMapping(Array("/api/battler"))
class BattlerController(battlerService: BattlerService) {

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattlerController])

  @RequestMapping(value = Array("getPlayer"), method = Array(GET))
  def getPlayer(user: Principal) = battlerService.getPlayer(user.getName)

}
