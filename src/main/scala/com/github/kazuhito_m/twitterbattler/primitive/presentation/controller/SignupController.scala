package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller

import com.github.kazuhito_m.twitterbattler.primitive.infrastructure.security.AuthUtil
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.social.connect.web.ProviderSignInUtils
import org.springframework.social.connect.{ConnectionFactoryLocator, UsersConnectionRepository}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.request.WebRequest

@Controller
class SignupController(
                        connectionFactoryLocator: ConnectionFactoryLocator
                        , connectionRepository: UsersConnectionRepository
                      ) {

  private val log: Logger = LoggerFactory.getLogger(classOf[SignupController])

  private var signInUtils: ProviderSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository)

  @RequestMapping(value = Array("/signup"))
  def signup(request: WebRequest): String = {
    //    log.info("signup() が呼ばれた時。")
    //    log.info("signInUtils : " + signInUtils)
    val connection = signInUtils.getConnectionFromSession(request)
    if (connection != null) {
      AuthUtil.authenticate(connection)
      signInUtils.doPostSignUp(connection.getDisplayName, request)
    }
    "redirect:/"
  }

  @RequestMapping(value = Array("/signin"))
  def signin(request: WebRequest) = "redirect:/login.html"

}
