package com.github.kazuhito_m.twitterbattler.primitive.application

import com.github.kazuhito_m.twitterbattler.primitive.domain.SampleRedisRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
@RequestMapping(Array("/sample"))
class SampleController {

  @Autowired
  val redisRepo: SampleRedisRepository = null

  @RequestMapping(value = Array("test"), method = Array(RequestMethod.GET))
  def data = "hoge"


  @RequestMapping(value = Array("redisgetsample"), method = Array(RequestMethod.GET))
  def redisGetSample(): String = redisRepo.getSampleValue

}
