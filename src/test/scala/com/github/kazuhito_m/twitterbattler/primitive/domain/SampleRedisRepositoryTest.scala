package com.github.kazuhito_m.twitterbattler.primitive.domain

import com.github.kazuhito_m.twitterbattler.primitive.fw.TestHelper._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification.BeforeEach


@RunWith(classOf[JUnitRunner])
class SampleRedisRepositoryTest extends Specification with BeforeEach {

  // befere -> test という風に実行順を保証する記述。
  sequential

  val sut = beanBy(classOf[SampleRedisRepository])

  def before = {
    sut.clearSampleValue // 初期設定
  }

  "サンプルのドメインクラスのお試しテスト" should {

    "初期状態はnull" in {
      sut.getSampleValue must beNull
    }

    "値を放り込んで、取得できる" in {
      val expect = "Redisから代表的な値を取ってくることが出来る"

      sut.setSampleValue(expect)
      val actual = sut.getSampleValue

      actual must equalTo(expect)
    }

  }

}
