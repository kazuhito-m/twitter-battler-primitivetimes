package com.github.kazuhito_m.twitterbattler.primitive.domain.repository

import com.github.kazuhito_m.twitterbattler.primitive.domain.entity.Battler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class GameInformationRepository {

  // Redisで使用するキー文字列。

  /** バトラーのキーヘッド */
  val BATTLER_KEY_PREFIX = "battler:"

  @Autowired
  private val redisTemplate: RedisTemplate[String, Object] = null

  /**
    * BattlerオブジェクトをIDで取得する。
    */
  def getBattler(id: String): Battler = ofv.get(makeKey(id)).asInstanceOf[Battler]

  /**
    * Battlerオブジェクトを保存する。すでにある場合は上書きする。
    */
  def saveBattler(battler: Battler): Unit = ofv.set(makeKey(battler.id), battler)

  /**
    * BattlerオブジェクトをIDで削除する。
    */
  def deleteBattler(id: String): Unit = redisTemplate.delete(makeKey(id))

  /** RedisにBattlerオブジェクトを保存する文字列キーを作成する。 */
  def makeKey(id: String): String = BATTLER_KEY_PREFIX + id

  // エイリアス。

  private def ofv = redisTemplate.opsForValue

}
