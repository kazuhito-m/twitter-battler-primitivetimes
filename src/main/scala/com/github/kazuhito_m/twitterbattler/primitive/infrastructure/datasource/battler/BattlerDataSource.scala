package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battler

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.{Battler, BattlerFactory, BattlerRepository}
import com.github.kazuhito_m.twitterbattler.primitive.infrastructure.twitter.TwitterDataSource
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class BattlerDataSource(
                         redisTemplate: RedisTemplate[String, Object],
                         twitterDataSource: TwitterDataSource
                       ) extends BattlerRepository {

  /** バトラーのキーヘッド */
  val BATTLER_KEY_PREFIX = "battler:"

  override def create(id: String): Battler = {
    val battler = BattlerFactory.create(twitterDataSource.getProfile(id))
    registrar(battler)
    battler
  }

  override def registrar(battler: Battler): Unit = ofv.set(makeKey(battler.id), battler)

  override def get(id: String): Battler = ofv.get(makeKey(id)).asInstanceOf[Battler]

  override def delete(id: String): Unit = redisTemplate.delete(makeKey(id))

  override def isExists(id: String): Boolean = get(id) != null

  /** RedisにBattlerオブジェクトを保存する文字列キーを作成する。 */
  private def makeKey(id: String): String = BATTLER_KEY_PREFIX + id

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
