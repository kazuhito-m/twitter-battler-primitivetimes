package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battle

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.BattleScene
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.BattleRepository
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerRepository
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class BattleDataSource(
                        battlerRepository: BattlerRepository,
                        redisTemplate: RedisTemplate[String, Object]
                      ) extends BattleRepository {

  /** 戦闘画面シーンIDのキーヘッド */
  val BATTLE_SCENE_ID_KEY_PREFIX = "battleSceneId:"

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattleDataSource])

  /** RedisにBattleSceneオブジェクトを保存する文字列キーを作成する。 */
  private def makeKeyForSceneId(id: Long): String = BATTLE_SCENE_ID_KEY_PREFIX + id.toString

  def saveBattleScene(playerTwitterId: String, battleScene: BattleScene): Unit = {
    val id: Long = battlerRepository.convertTwitterIdToId(playerTwitterId)
    ofv.set(makeKeyForSceneId(id), battleScene)
  }

  def deleteBattleScene(playerTwitterId: String): Unit = {
    val id: Long = battlerRepository.convertTwitterIdToId(playerTwitterId)
    redisTemplate.delete(makeKeyForSceneId(id))
  }

  def isExistsBattleScene(playerTwitterId: String): Boolean = getBattleScene(playerTwitterId) != null

  def getBattleScene(playerTwitterId: String): BattleScene = {
    val id: Long = battlerRepository.convertTwitterIdToId(playerTwitterId)
    ofv.get(makeKeyForSceneId(id)).asInstanceOf[BattleScene]
  }

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
