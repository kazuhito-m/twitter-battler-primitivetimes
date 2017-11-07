package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battle

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.BattleScene
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.{Battle, BattleRepository, Turn, Turns}
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.{BattlerIdentifier, BattlerRepository}
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.party.{PartyFactory, PartyStatus}
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
  private def makeKeyForSceneId(identifier: BattlerIdentifier): String = BATTLE_SCENE_ID_KEY_PREFIX + identifier

  override def saveBattleScene(playerTwitterId: String, battleScene: BattleScene): Unit = {
    val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
    ofv.set(makeKeyForSceneId(id), battleScene)
  }

  override def deleteBattleScene(playerTwitterId: String): Unit = {
    val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
    redisTemplate.delete(makeKeyForSceneId(id))
  }

  override def isExistsBattleScene(playerTwitterId: String): Boolean = getBattleScene(playerTwitterId) != null

  override def getBattleScene(playerTwitterId: String): BattleScene = {
    val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
    ofv.get(makeKeyForSceneId(id)).asInstanceOf[BattleScene]
  }

  override def createBattle(playerTwitterId: String): Battle = {
    val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
    val player = battlerRepository.get(id)
    val partyFactory: PartyFactory = new PartyFactory(battlerRepository)

    val partyStatus: PartyStatus = new PartyStatus(
      partyFactory.createPlayerParty(player),
      partyFactory.createEnemyParty()
    )

    val turns = new Turns(new Turn(partyStatus))
    val newBattle = new Battle(turns)

    registerBattle(id, newBattle)
    newBattle
  }

  override def registerBattle(playerTwitterId: String, battle: Battle): Unit = {
    val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
    registerBattle(id, battle)
  }

  private def makeKeyForBaattleId(identifier: BattlerIdentifier): String = BATTLE_ID_KEY_PREFIX + identifier

  private def registerBattle(identifier: BattlerIdentifier, battle: Battle): Unit = ofv.set(makeKeyForBaattleId(identifier), battle)

  override def getBattle(playerTwitterId: String): Battle = {
    val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
    ofv.get(makeKeyForBaattleId(id)).asInstanceOf[Battle]
  }

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
