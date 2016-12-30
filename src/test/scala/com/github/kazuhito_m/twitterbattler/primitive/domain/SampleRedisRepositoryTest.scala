package com.github.kazuhito_m.twitterbattler.primitive.domain

import com.github.kazuhito_m.twitterbattler.primitive.ConfigForTest
import com.github.kazuhito_m.twitterbattler.primitive.domain.repository.SampleRedisRepository
import org.hamcrest.CoreMatchers._
import org.junit.Assert.assertThat
import org.junit._
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[ConfigForTest]))
class SampleRedisRepositoryTest {

  @Autowired
  var sut: SampleRedisRepository = null

  @Before
  def setUp = {
    sut.clearSampleValue // 初期設定
  }

  @Test
  def 初期状態はnull(): Unit = {
    assertThat(sut.getSampleValue, is(nullValue))
  }

  @Test
  def 値を放り込んで取得できる(): Unit = {
    sut.clearSampleValue
    // 初期設定
    val expect = "Redisから代表的な値を取ってくることが出来る"

    sut.setSampleValue(expect)
    val actual = sut.getSampleValue

    assertThat(actual, is(expect))
  }

}
