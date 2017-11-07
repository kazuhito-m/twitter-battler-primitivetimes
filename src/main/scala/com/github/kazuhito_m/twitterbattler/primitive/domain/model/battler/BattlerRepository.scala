package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler

trait BattlerRepository {

  def convertTwitterIdToId(twitterId: String): BattlerIdentifier

  /**
    * 指定されたId(TwitterID)から、ゲームキャラクタデータを作成、同時に保存する。
    */
  def create(identifier: BattlerIdentifier): Battler

  /**
    * Battlerオブジェクトを保存する。すでにある場合は上書きする。
    */
  def registar(battler: Battler): Unit

  /**
    * BattlerオブジェクトをIDで取得する。
    */
  def get(identifier: BattlerIdentifier): Battler

  /**
    * BattlerオブジェクトをIDで削除する。
    */
  def delete(identifier: BattlerIdentifier): Unit

  /**
    * Battlerオブジェクトが存在するかを真偽値で返す。
    */
  def isExists(identifier: BattlerIdentifier): Boolean

  /**
    * 指定したバトラーIDの友達バトラーをランダムに人数指定で取得する。
    */
  def randomFriendBattlers(identifier: BattlerIdentifier, size: Int): List[Battler]

  /**
    * ランダムに一人のバトラーを取得する。
    */
  def randomOneBattler(): Battler

}
