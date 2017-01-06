package com.github.kazuhito_m.twitterbattler.primitive.domain.constant

/**
  * 「戦闘画面のシーン」のenumもどき。
  */
sealed abstract class BattleScene(val id:String)

object BattleScene {

  case object PartyMake extends BattleScene("PM:01")
  case object PartyMakeMore extends BattleScene("PM:02")

  // 値の全列挙。
  val values = Array(PartyMake , PartyMakeMore)

  /** ID文字列からobjectを取得。 */
  def byId(id:String): BattleScene = values.find(_.id == id).get

}

