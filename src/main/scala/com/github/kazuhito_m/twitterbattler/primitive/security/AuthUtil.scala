package com.github.kazuhito_m.twitterbattler.primitive.security

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.social.connect.Connection

class AuthUtil

object AuthUtil {

  protected val log: Logger = LoggerFactory.getLogger(classOf[AuthUtil])

  def authenticate(connection: Connection[_]) {
    val userProfile = connection.fetchUserProfile
    val username = userProfile.getUsername
    val authentication = new UsernamePasswordAuthenticationToken(username, null, null)
    SecurityContextHolder.getContext.setAuthentication(authentication)
    log.info("User " + userProfile.getFirstName + " " + userProfile.getLastName + " connected.")
  }
}
