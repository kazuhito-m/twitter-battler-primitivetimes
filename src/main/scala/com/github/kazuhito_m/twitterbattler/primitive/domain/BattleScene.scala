package com.github.kazuhito_m.twitterbattler.primitive.domain

/**
  * 「戦闘画面のシーン」のenumもどき。
  */
sealed abstract class BattleScene(val id: String)

object BattleScene {

  /** パーティ作成画面(自動生成直後、アニメーションあり画面) */
  case object PartyMake extends BattleScene("PM:01")

  /** パーティ作成・変更画面(アイテムを使ったりとかいろいろ) */
  case object PartyMakeMore extends BattleScene("PM:02")

  /** バトル画面(入力を許容する状態) */
  case object BattleOperation extends BattleScene("BT:01")

  /** バトル画面(アニメーションなどするターン中) */
  case object BattleInTurn extends BattleScene("BT:02")

  /** バトル結果(結果や経験値やボーナスなどを表示する画面) */
  case object BattleResult extends BattleScene("BR:01")

  // 値の全列挙。
  val values = Array(PartyMake, PartyMakeMore, BattleOperation, BattleInTurn, BattleResult)

  /** ID文字列からobjectを取得。 */
  def byId(id: String): BattleScene = values.find(_.id == id).get

}

