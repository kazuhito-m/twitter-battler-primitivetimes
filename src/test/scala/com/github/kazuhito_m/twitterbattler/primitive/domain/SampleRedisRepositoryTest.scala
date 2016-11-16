package com.github.kazuhito_m.twitterbattler.primitive.domain

import com.github.kazuhito_m.twitterbattler.primitive.fw.TestHelper
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner


@RunWith(classOf[JUnitRunner])
class SampleRedisRepositoryTest extends Specification {

  // Springアノテーション系が全滅してるので、自力でコンテキスト作ってその場で動かす。
  var sut: SampleRedisRepository = TestHelper.beanBy(classOf[SampleRedisRepository]);

  "サンプルのドメインクラスのお試しテスト" should {

    "RESTから代表的な値を取ってくることが出来る" in {
      sut.getSampleValue must equalTo("Redisのテストに使う予定の建設予定地。ここでは値取得だけ。")
    }

  }

}
