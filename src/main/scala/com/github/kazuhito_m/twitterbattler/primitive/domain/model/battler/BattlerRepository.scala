package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler

trait BattlerRepository {

  def convertTwitterIdToId(twitterId: String): Long

  /**
    * 指定されたId(TwitterID)から、ゲームキャラクタデータを作成、同時に保存する。
    */
  def create(id: Long): Battler

  /**
    * Battlerオブジェクトを保存する。すでにある場合は上書きする。
    */
  def registrar(battler: Battler): Unit

  /**
    * BattlerオブジェクトをIDで取得する。
    */
  def get(id: Long): Battler

  /**
    * BattlerオブジェクトをIDで削除する。
    */
  def delete(id: Long): Unit

  /**
    * Battlerオブジェクトが存在するかを真偽値で返す。
    */
  def isExists(id: Long): Boolean

}
