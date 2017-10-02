package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.BattleScene
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.BattleRepository
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class BattleDataSource(redisTemplate: RedisTemplate[String, Object]) extends BattleRepository {

  /** 戦闘画面シーンIDのキーヘッド */
  val BATTLE_SCENE_ID_KEY_PREFIX = "battleSceneId:"

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattleDataSource])

  def makeKeyForSceneId(playerId: String): String = BATTLE_SCENE_ID_KEY_PREFIX + playerId

  def saveBattleScene(playerId: String, battleScene: BattleScene): Unit = ofv.set(makeKeyForSceneId(playerId), battleScene)

  def deleteBattleScene(playerId: String): Unit = redisTemplate.delete(makeKeyForSceneId(playerId))

  def isExistsBattleScene(playerId: String): Boolean = getBattleScene(playerId) != null

  def getBattleScene(playerId: String): BattleScene = ofv.get(makeKeyForSceneId(playerId)).asInstanceOf[BattleScene]

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
