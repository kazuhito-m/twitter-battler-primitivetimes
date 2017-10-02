package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler

import java.util.Date

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.springframework.social.twitter.api.TwitterProfile

@RunWith(classOf[JUnitRunner])
class BattlerParameterGeneratorSpec extends Specification {

  "バトラーのパラメータ生成のテスト" should {

    "すばやさは「一日の平均ツイート数」に比例する" in {
      val sut = new {} with BattlerParameterGenerator {}

      // 放り込むパラメータ
      val profile = new TwitterProfile(1, null, null, null, null, null, null, null) {
        override def getCreatedDate() = new Date(1256399391000L)
        override def getStatusesCount: Int = 82071
      }
      // 実行＆検証
      sut.calculateSpeedPoint(profile) must equalTo(32)

    }

  }

}
