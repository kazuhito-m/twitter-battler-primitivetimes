package com.github.kazuhito_m.twitterbattler.primitive.view

import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
class SampleController {

  @RequestMapping(value = Array("/api/sample"), method = Array(RequestMethod.GET))
  def data = "hoge"


  @RequestMapping(value = Array("/api/redisgetsample"), method = Array(RequestMethod.GET))
  def redisGetSample(): String = "Redisのテストに使う予定の建設予定地。"

}
