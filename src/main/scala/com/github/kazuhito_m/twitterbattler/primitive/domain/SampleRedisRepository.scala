package com.github.kazuhito_m.twitterbattler.primitive.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository

@Repository
class SampleRedisRepository {

  /** Redisで取得するキー */
  val SAMPLE_KEY = "sample-value:string"

  @Autowired
  private val redisTemplate: StringRedisTemplate = null

  /**
    * サンプル用テキトウキーで値を取得。
    *
    * @return 取得できた値。
    */
  def getSampleValue: String = ofv.get(SAMPLE_KEY)

  /**
    * サンプル用テキトウキーで値を保存。
    *
    * @param value 保存する文字列。
    */
  def setSampleValue(value: String) = ofv.set(SAMPLE_KEY, value)

  private def ofv = redisTemplate.opsForValue()

  /**
    * サンプル用テキトウキー自体を削除。
    */
  def clearSampleValue = redisTemplate.delete(SAMPLE_KEY)

}
