package com.github.kazuhito_m.twitterbattler.primitive.domain.repository

import com.github.kazuhito_m.twitterbattler.primitive.domain.constant.BattleScene
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class BattleRepository {

  /** 戦闘画面シーンIDのキーヘッド */
  val BATTLE_SCENE_ID_KEY_PREFIX = "battleSceneId:"

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattleRepository])

  @Autowired
  private val redisTemplate: RedisTemplate[String, Object] = null

  /** RedisにBattleSceneオブジェクトを保存する文字列キーを作成する。 */
  def makeKeyForSceneId(playerId: String): String = BATTLE_SCENE_ID_KEY_PREFIX + playerId

  /**
    * BattleSceneオブジェクトを保存する。すでにある場合は上書きする。
    */
  def saveBattleScene(playerId: String, battleScene: BattleScene): Unit = ofv.set(makeKeyForSceneId(playerId), battleScene)

  /**
    * BattleSceneオブジェクトをIDで削除する。
    */
  def deleteBattleScene(playerId: String): Unit = redisTemplate.delete(makeKeyForSceneId(playerId))

  /**
    * BattleSceneオブジェクトが存在するかを真偽値で返す。
    */
  def isExistsBattleScene(playerId: String): Boolean = getBattleScene(playerId) != null

  /**
    * 現在の「指定ユーザの戦闘画面シーンID」を返す。
    */
  def getBattleScene(playerId: String): BattleScene = ofv.get(makeKeyForSceneId(playerId)).asInstanceOf[BattleScene]

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
