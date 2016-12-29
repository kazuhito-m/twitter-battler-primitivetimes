package com.github.kazuhito_m.twitterbattler.primitive.application

import java.security.Principal

import com.github.kazuhito_m.twitterbattler.primitive.domain.GameInformationService
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody, RestController}

import scala.collection.JavaConverters._

@RestController
@RequestMapping(Array("/api/game"))
class GameInformationController {

  protected val log: Logger = LoggerFactory.getLogger(classOf[GameInformationController])

  @Autowired
  private val gameInfoService: GameInformationService = null

  @RequestMapping(value = Array("getPlayer"), method = Array(GET, POST))
  def getPlayer(user: Principal) = {
    gameInfoService.getPlayer(user.getName)
  }

}