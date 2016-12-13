package com.github.kazuhito_m.twitterbattler.primitive.security

import java.security.Principal
import javax.servlet.http.HttpSession

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
@RequestMapping(Array("/api/session"))
class AuthenticationResource {

  @Autowired
  val authenticationManager: AuthenticationManager = null

  @RequestMapping(method = Array(RequestMethod.GET))
  def session(user: Principal) = User(if (user == null) null else user.getName)

  @RequestMapping(method = Array(RequestMethod.DELETE))
  def logout(session: HttpSession) = session.invalidate()
}
