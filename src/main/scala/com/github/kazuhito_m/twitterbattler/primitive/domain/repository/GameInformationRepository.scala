package com.github.kazuhito_m.twitterbattler.primitive.domain.repository

import com.github.kazuhito_m.twitterbattler.primitive.domain.entity.Battler
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class GameInformationRepository {

  protected val log: Logger = LoggerFactory.getLogger(classOf[GameInformationRepository])

  // Redisで使用するキー文字列。

  /** バトラーのキーヘッド */
  val BATTLER_KEY_PREFIX = "battler:"

  @Autowired
  private val redisTemplate: RedisTemplate[String, Object] = null

  /**
    * BattlerオブジェクトをIDで取得する。
    */
  def getBattler(id: String): Battler = {
    val o = ofv.get(makeKey(id))
    if (o != null) log.debug("class:" + o.getClass.getName + ",value:" + o)
    val b:Battler = o.asInstanceOf[Battler]
    if (b != null) log.debug("class2:" + b.getClass.getName + ",value:" + b)
    return b
  }

  /**
    * Battlerオブジェクトを保存する。すでにある場合は上書きする。
    */
  def saveBattler(battler: Battler): Unit = ofv.set(makeKey(battler.id), battler)

  /**
    * BattlerオブジェクトをIDで削除する。
    */
  def deleteBattler(id: String): Unit = redisTemplate.delete(makeKey(id))

  /**
    * Battlerオブジェクトが存在するかを真偽値で返す。
    */
  def isExistsBattler(id: String) :Boolean = {getBattler(id) != null}

  /** RedisにBattlerオブジェクトを保存する文字列キーを作成する。 */
  def makeKey(id: String): String = BATTLER_KEY_PREFIX + id

  // エイリアス。

  private def ofv = redisTemplate.opsForValue

}
