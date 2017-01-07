package com.github.kazuhito_m.twitterbattler.primitive.domain.game

import com.github.kazuhito_m.twitterbattler.primitive.ConfigForTest
import com.github.kazuhito_m.twitterbattler.primitive.domain.BattleScene
import com.github.kazuhito_m.twitterbattler.primitive.domain.battle.BattleRepository
import org.hamcrest.CoreMatchers._
import org.junit.Assert.assertThat
import org.junit._
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[ConfigForTest]))
class BattleRepositoryTest {

  @Autowired
  var sut: BattleRepository = null

  @Test
  def redisに対しenumっぽいオブジェクトをそのまま保存復元出来る(): Unit = {
    // 準備
    val playerId = "Player0001"
    // 実行
    sut.saveBattleScene(playerId, BattleScene.PartyMake)
    val actual: BattleScene = sut.getBattleScene(playerId)
    sut.deleteBattleScene(playerId)
    // 検証
    assertThat(actual, is(notNullValue()))
    assertThat(actual.id, is("PM:01"))
  }

  @Test
  def redisに対しデータが無い場合にはnullを返す(): Unit = {
    // 準備
    val id = "testId"
    // 実行
    sut.deleteBattleScene(id)
    val actual = sut.getBattleScene(id)
    // 検証
    assertThat(actual, is(nullValue))
  }

}
