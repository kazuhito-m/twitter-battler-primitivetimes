package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle

import java.time.LocalDateTime

import com.github.kazuhito_m.twitterbattler.primitive.ConfigForTest
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.{Battler, BattlerRepository}
import org.hamcrest.CoreMatchers._
import org.junit.Assert.assertThat
import org.junit._
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[ConfigForTest]))
class BattlerRepositoryTest {

  @Autowired
  var sut: BattlerRepository = null

  @Before
  def setUp = {}

  @Test
  def redisに対しオブジェクトをそのまま保存復元出来る(): Unit = {
    // 準備
    val param = createSampleBattler(123456, "名前")
    // 実行
    sut.registar(param)
    val actual = sut.get(param.id)
    sut.delete(param.id)
    // 検証
    assertThat(actual, is(notNullValue))
    // 値
    assertThat(actual.id, is(param.id))
    assertThat(actual.screenName, is(param.screenName))
  }

  @Test
  def redisに対しデータが無い場合にはnullを返す(): Unit = {
    // 準備
    val id = 4321
    // 実行
    sut.delete(id)
    val actual = sut.get(id)
    // 検証
    assertThat(actual, is(nullValue))
  }

  // ユティリティ

  @Test
  def redis中にBattlerオブジェクトが存在するかを調べることが出来る(): Unit = {
    // 準備
    val param = createSampleBattler(123456, "名前")
    sut.registar(param)
    // 実行
    val actual = sut.isExists(param.id)
    // 検証
    assertThat(actual, is(true))
    // 削除してみる
    sut.delete(param.id)
    // 実行
    val actual2 = sut.isExists(param.id)
    // 検証
    assertThat(actual2, is(false))
  }

  def createSampleBattler(id: Int, name: String): Battler = {
    val battler = new Battler()
    battler.id = id
    battler.screenName = name
    battler.biography = "紹介文"
    battler.level = 1
    battler.maxHitPoint = 2
    battler.maxSpecialPoint = 3
    battler.attackPoint = 4
    battler.defensePoint = 5
    battler.speedPoint = 6
    battler.imageUrl = "http://example.com"
    battler.firstSignUpDate = LocalDateTime.now()
    battler.generateDate = LocalDateTime.now()
    battler
  }

}
