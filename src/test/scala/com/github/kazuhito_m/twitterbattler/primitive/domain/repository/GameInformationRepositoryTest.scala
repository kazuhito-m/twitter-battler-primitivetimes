package com.github.kazuhito_m.twitterbattler.primitive.domain.repository

import java.util.Date

import com.github.kazuhito_m.twitterbattler.primitive.ConfigForTest
import com.github.kazuhito_m.twitterbattler.primitive.domain.entity.Battler
import org.hamcrest.CoreMatchers._
import org.junit.Assert.assertThat
import org.junit._
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[ConfigForTest]))
class GameInformationRepositoryTest {

  @Autowired
  var sut: GameInformationRepository = null

  @Before
  def setUp = {}

  @Test
  def redisに対しオブジェクトをそのまま保存復元出来る(): Unit = {
    // 準備
    val param = createSampleBattler("testId", "名前")
    // 実行
    sut.saveBattler(param)
    val actual = sut.getBattler(param.id)
    sut.deleteBattler(param.id)
    // 検証
    assertThat(actual, is(notNullValue))
    // 値
    assertThat(actual.id, is(param.id))
    assertThat(actual.screenName, is(param.screenName))
    // 削除
  }

  // ユティリティ

  def createSampleBattler(id: String, name: String) = Battler(id, name, "紹介", 1, 2, 3, 4, 5, 6, "http://example.com", new Date(), new Date())

}
