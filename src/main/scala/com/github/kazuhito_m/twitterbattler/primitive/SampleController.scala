package com.github.kazuhito_m.twitterbattler.primitive

import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
@RequestMapping(Array("/api/sample"))
class SampleController {
  @RequestMapping(method = Array(RequestMethod.GET))
  def data = "hoge"
}
