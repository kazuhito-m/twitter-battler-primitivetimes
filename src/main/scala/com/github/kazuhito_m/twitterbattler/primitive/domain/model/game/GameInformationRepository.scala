package com.github.kazuhito_m.twitterbattler.primitive.domain.model.game

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.Battler
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class GameInformationRepository {

  /** バトラーのキーヘッド */
  val BATTLER_KEY_PREFIX = "battler:"

  protected val log: Logger = LoggerFactory.getLogger(classOf[GameInformationRepository])

  @Autowired
  private val redisTemplate: RedisTemplate[String, Object] = null

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

  /**
    * Battlerオブジェクトが存在するかを真偽値で返す。
    */
  def isExistsBattler(id: String): Boolean = getBattler(id) != null

  /**
    * BattlerオブジェクトをIDで取得する。
    */
  def getBattler(id: String): Battler = ofv.get(makeKey(id)).asInstanceOf[Battler]

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
