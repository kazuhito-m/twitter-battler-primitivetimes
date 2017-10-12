package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.BattleScene

trait BattleRepository {

  /**
    * BattleSceneオブジェクトを保存する。すでにある場合は上書きする。
    */
  def saveBattleScene(playerTwitterId: String, battleScene: BattleScene): Unit

  /**
    * BattleSceneオブジェクトをIDで削除する。
    */
  def deleteBattleScene(playerTwitterId: String): Unit

  /**
    * BattleSceneオブジェクトが存在するかを真偽値で返す。
    */
  def isExistsBattleScene(playerTwitterId: String): Boolean

  /**
    * 現在の「指定ユーザの戦闘画面シーンID」を返す。
    */
  def getBattleScene(playerTwitterId: String): BattleScene

  def createBattle(playerTwitterId: String): Battle

  def getBattle(playerTwitterId: String): Battle

}
