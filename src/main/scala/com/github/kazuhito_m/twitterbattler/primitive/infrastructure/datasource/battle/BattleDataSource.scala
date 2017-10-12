package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battle

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.BattleScene
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.{Battle, BattleRepository}
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerRepository
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.party.PartyFactory
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class BattleDataSource(
                        battlerRepository: BattlerRepository,
                        redisTemplate: RedisTemplate[String, Object]
                      ) extends BattleRepository {

  /** 戦闘画面シーンIDのキーヘッド */
  private val BATTLE_SCENE_ID_KEY_PREFIX = "battleSceneId:"

  /** 戦闘データのIDのキーヘッド */
  private val BATTLE_ID_KEY_PREFIX = "battleId:"

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattleDataSource])

  /** RedisにBattleSceneオブジェクトを保存する文字列キーを作成する。 */
  private def makeKeyForSceneId(id: Long): String = BATTLE_SCENE_ID_KEY_PREFIX + id.toString

  override def saveBattleScene(playerTwitterId: String, battleScene: BattleScene): Unit = {
    val id: Long = battlerRepository.convertTwitterIdToId(playerTwitterId)
    ofv.set(makeKeyForSceneId(id), battleScene)
  }

  override def deleteBattleScene(playerTwitterId: String): Unit = {
    val id: Long = battlerRepository.convertTwitterIdToId(playerTwitterId)
    redisTemplate.delete(makeKeyForSceneId(id))
  }

  override def isExistsBattleScene(playerTwitterId: String): Boolean = getBattleScene(playerTwitterId) != null

  override def getBattleScene(playerTwitterId: String): BattleScene = {
    val id: Long = battlerRepository.convertTwitterIdToId(playerTwitterId)
    ofv.get(makeKeyForSceneId(id)).asInstanceOf[BattleScene]
  }

  override def createBattle(playerTwitterId: String): Battle = {
    val id: Long = battlerRepository.convertTwitterIdToId(playerTwitterId)
    val player = battlerRepository.get(id)
    val pertyFactory: PartyFactory = new PartyFactory(battlerRepository)

    val newBattle = new Battle(
      pertyFactory.createPlayerParty(player),
      pertyFactory.createEnemyParty()
    )
    registerBattle(id, newBattle)
    newBattle
  }

  private def makeKeyForBaattleId(id: Long): String = BATTLE_ID_KEY_PREFIX + id.toString

  private def registerBattle(id: Long, battle: Battle): Unit = ofv.set(makeKeyForBaattleId(id), battle)

  override def getBattle(playerTwitterId: String): Battle = {
    val id: Long = battlerRepository.convertTwitterIdToId(playerTwitterId)
    ofv.get(makeKeyForBaattleId(id)).asInstanceOf[Battle]
  }

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
