package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.BattleScene

trait BattleRepository {

  /** RedisにBattleSceneオブジェクトを保存する文字列キーを作成する。 */
  def makeKeyForSceneId(playerId: String): String

  /**
    * BattleSceneオブジェクトを保存する。すでにある場合は上書きする。
    */
  def saveBattleScene(playerId: String, battleScene: BattleScene): Unit

  /**
    * BattleSceneオブジェクトをIDで削除する。
    */
  def deleteBattleScene(playerId: String): Unit

  /**
    * BattleSceneオブジェクトが存在するかを真偽値で返す。
    */
  def isExistsBattleScene(playerId: String): Boolean

  /**
    * 現在の「指定ユーザの戦闘画面シーンID」を返す。
    */
  def getBattleScene(playerId: String): BattleScene

}
