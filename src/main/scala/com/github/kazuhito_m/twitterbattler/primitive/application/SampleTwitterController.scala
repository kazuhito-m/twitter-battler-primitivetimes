package com.github.kazuhito_m.twitterbattler.primitive.application

import java.security.Principal

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.social.twitter.api.Twitter
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
@RequestMapping(Array("/api/twitter"))
class SampleTwitterController {

  @Autowired
  val twitter: Twitter = null

  @RequestMapping(value = Array("tl"), method = Array(GET, POST))
  def timeline(user: Principal) = opeTwitter(user,
    (name) => twitter.timelineOperations().getUserTimeline("@" + name)
  )

  @RequestMapping(value = Array("profile"), method = Array(GET, POST))
  def profile(user: Principal) = opeTwitter(user,
    (name) => twitter.userOperations().getUserProfile(name)
  )

  def opeTwitter(user: Principal, f: (String) => Object) = if (user == null) null else f(user.getName)

}
