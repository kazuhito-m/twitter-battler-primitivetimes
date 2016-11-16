package com.github.kazuhito_m.twitterbattler.primitive.domain

import org.springframework.stereotype.Repository

@Repository
class SampleRedisRepository {

  def getSampleValue: String = "Redisのテストに使う予定の建設予定地。ここでは値取得だけ。";

}
