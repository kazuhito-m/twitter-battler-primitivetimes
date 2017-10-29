package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battler

import java.util

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.{Battler, BattlerFactory, BattlerRepository, RandomChoiceIdList}
import com.github.kazuhito_m.twitterbattler.primitive.infrastructure.twitter.TwitterDataSource
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

import scala.collection.JavaConverters._
import scala.math.{floor, random}

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
    registar(battler)
    battler
  }

  private def getOrCreate(id: Long): Battler = {
    val existsBattler = get(id)
    if (existsBattler != null) return existsBattler
    return create(id)
  }

  override def registar(battler: Battler): Unit = ofv.set(makeKey(battler.id), battler)

  override def get(id: Long): Battler = ofv.get(makeKey(id)).asInstanceOf[Battler]

  override def delete(id: Long): Unit = redisTemplate.delete(makeKey(id))

  override def isExists(id: Long): Boolean = get(id) != null

  override def randomFriendBattlers(id: Long, size: Int): List[Battler] = {
    val choicesIds: List[Long] = randomFriendIds(id, size)
    choicesIds.map { id => getOrCreate(id) }
  }

  private def randomFriendIds(id: Long, size: Int): List[Long] = {
    val ids: List[Long] = twitterDataSource.getFollowers(id)
    val choicesIds: List[Long] = new RandomChoiceIdList(ids).choice(size)
    if (choicesIds.size >= size) return choicesIds;

    val followIds: List[Long] = twitterDataSource.getFollows(id)
    val choiceWithFollowIds = choicesIds ++ new RandomChoiceIdList(followIds).choice(size - choicesIds.size)
    if (choiceWithFollowIds.size >= size) return choiceWithFollowIds;

    val randomIds: List[Long] = this.getRandomIds()
    choiceWithFollowIds ++ new RandomChoiceIdList(randomIds).choice(size - choiceWithFollowIds.size)
  }

  override def randomOneBattler(): Battler = {
    getOrCreate(getRandomId)
  }

  private val RANDOM_IDS_KEY: String = "randomIds"

  /**
    * 因果の無いTwitterランダムID群を取得する。
    * その際、アクセスにコストがかかるので、BattlerデータとIDデータをRedisに作成・保存する。
    */
  private def getRandomIds(): List[Long] = {
    val accounts = twitterDataSource.getRandomAccounts()
    // バトラーを作りながら保存。
    accounts.foreach(account => registar(BattlerFactory.create(account)))
    // IDだけを抽出。
    val ids: List[Long] = accounts.map { account => account.getId }
    // Twitter側からがゼロ(RateOvetとか)なら、Redis側から出して返す。
    if (ids.isEmpty) {
      val idSet: util.Set[Object] = redisTemplate.opsForSet().members(RANDOM_IDS_KEY)
      return idSet.asScala.map { value => value.asInstanceOf[Long] }.toList
    }
    // Twitter側で取得できた場合、Redisに保存する。
    redisTemplate.opsForSet.add(RANDOM_IDS_KEY, ids.toArray)
    ids;
  }

  private def getRandomId(): Long = {
    val accounts: List[Long] = getRandomIds()
    val randomIndex = floor(random * accounts.size).toInt
    accounts(randomIndex)
  }

  /** RedisにBattlerオブジェクトを保存する文字列キーを作成する。 */
  private def makeKey(id: Long): String = BATTLER_KEY_PREFIX + id

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
