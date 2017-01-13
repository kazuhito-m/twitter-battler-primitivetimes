package com.github.kazuhito_m.twitterbattler.primitive.domain

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BattleSceneSpec extends Specification {

  "BattleSceneのenumもどきのテスト" should {

    "オブジェクトからid文字列が取得できる" in {
      BattleScene.PartyMake.id must equalTo("partymake:01")
      BattleScene.PartyMakeMore.id must equalTo("partymake:02")
    }

    "id文字列からオブジェクトを取得できる" in {
      BattleScene.byId("partymake:01") must equalTo(BattleScene.PartyMake)
      BattleScene.byId("partymake:02") must equalTo(BattleScene.PartyMakeMore)
    }

  }

}
