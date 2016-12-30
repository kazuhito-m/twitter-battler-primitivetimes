package com.github.kazuhito_m.twitterbattler.primitive.domain.repository

import com.github.kazuhito_m.twitterbattler.primitive.domain.entity.Battler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository

@Repository
class GameInformationRepository {

  // Redisで使用するキー文字列。

  /** バトラーのキーヘッド */
  val BATTLER_KEY_PREFIX = "battler:"

  @Autowired
  private val redisTemplate: StringRedisTemplate = null

  def getBattler(id:String): Battler = {
    // TODO 実装
    null
  }

}
