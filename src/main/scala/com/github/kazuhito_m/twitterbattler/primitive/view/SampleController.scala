package com.github.kazuhito_m.twitterbattler.primitive.view

import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
@RequestMapping(Array("/sample"))
class SampleController {

  @RequestMapping(value = Array("test"), method = Array(RequestMethod.GET))
  def data = "hoge"


  @RequestMapping(value = Array("redisgetsample"), method = Array(RequestMethod.GET))
  def redisGetSample(): String = "Redisのテストに使う予定の建設予定地。"

}
