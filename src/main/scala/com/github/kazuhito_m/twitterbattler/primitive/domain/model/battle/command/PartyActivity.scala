package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command

/**
  * 「パーティ全体の行動」のenumもどき。
  */
sealed abstract class PartyActivity

object PartyActivity {

  /** たたかう */
  case object Fight extends PartyActivity

  /** こうさん(敗北) */
  case object Surrender extends PartyActivity

  // 値の全列挙。
  val values = Array(Fight, Surrender)

}
