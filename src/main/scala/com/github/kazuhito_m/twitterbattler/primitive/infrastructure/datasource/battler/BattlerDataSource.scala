package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battler

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.{Battler, BattlerFactory, BattlerRepository}
import com.github.kazuhito_m.twitterbattler.primitive.infrastructure.twitter.TwitterDataSource
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class BattlerDataSource(
                         redisTemplate: RedisTemplate[String, Object],
                         twitterDataSource: TwitterDataSource
                       ) extends BattlerRepository {

  /** バトラーのキーヘッド */
  val BATTLER_KEY_PREFIX = "battler:"

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattlerDataSource])

  override def convertTwitterIdToId(twitterId: String): Long = {
    // TODO あの手➖ーションなど「意識しないでキャッシュ出来る」方法
    val key = "convertTwitterIdToId:" + twitterId
    val hitId: String = ofv.get(key).asInstanceOf[String]
    if (hitId != null) return hitId.toLong
    val id: Long = twitterDataSource.convertScreenNameToId(twitterId)
    ofv.set(key, id.toString)
    id
  }

  override def create(id: Long): Battler = {
    val battler = BattlerFactory.create(twitterDataSource.getProfile(id))
    registrar(battler)
    battler
  }

  override def registrar(battler: Battler): Unit = ofv.set(makeKey(battler.id), battler)

  override def get(id: Long): Battler = ofv.get(makeKey(id)).asInstanceOf[Battler]

  override def delete(id: Long): Unit = redisTemplate.delete(makeKey(id))

  override def isExists(id: Long): Boolean = get(id) != null

  /** RedisにBattlerオブジェクトを保存する文字列キーを作成する。 */
  private def makeKey(id: Long): String = BATTLER_KEY_PREFIX + id

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
