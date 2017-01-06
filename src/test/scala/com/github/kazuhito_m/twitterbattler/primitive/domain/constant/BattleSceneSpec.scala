package com.github.kazuhito_m.twitterbattler.primitive.domain.constant

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BattleSceneSpec  extends Specification {

  "BattleSceneのenumもどきのテスト" should {

    "オブジェクトからid文字列が取得できる" in {
      BattleScene.PartyMake.id must equalTo("PM:01")
      BattleScene.PartyMakeMore.id must equalTo("PM:02")
    }

    "id文字列からオブジェクトを取得できる" in {
      BattleScene.byId("PM:01") must equalTo(BattleScene.PartyMake)
      BattleScene.byId("PM:02") must equalTo(BattleScene.PartyMakeMore)
    }

  }

}
