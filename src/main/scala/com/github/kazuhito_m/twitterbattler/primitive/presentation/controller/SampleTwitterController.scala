package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller

import java.security.Principal

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.sample.Batlers
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.social.twitter.api.Twitter
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
@RequestMapping(Array("/api/twitter"))
class SampleTwitterController(twitter: Twitter, redisTemplate: RedisTemplate[String, Object]) {

  @RequestMapping(value = Array("tl"), method = Array(GET, POST))
  def timeline(user: Principal) = opeTwitter(user,
    (name) => twitter.timelineOperations().getUserTimeline("@" + name)
  )

  def opeTwitter(user: Principal, f: (String) => Object) = if (user == null) null else f(user.getName)

  @RequestMapping(value = Array("profile"), method = Array(GET, POST))
  def profile(user: Principal) = opeTwitter(user,
    (name) => twitter.userOperations().getUserProfile(name)
  )

  @RequestMapping(value = Array("jsonTest"), method = Array(GET))
  def jsonTest(): Unit = {
    val one: Battler = new Battler
    one.id = 1
    one.twitterId = "testone"
    val two: Battler = new Battler
    two.id = 2
    two.twitterId = "testtwo"

    //    val battlers: Batlers = new Batlers(one, two)
    val battlers: Batlers = new Batlers(List(one, two))
    redisTemplate.opsForValue.set("test:abcd", battlers)

  }

  @RequestMapping(value = Array("twitterTest"), method = Array(GET))
  def twitterTest() = twitter.timelineOperations().getHomeTimeline

}
