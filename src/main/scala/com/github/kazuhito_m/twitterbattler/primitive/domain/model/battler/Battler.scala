package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler

import java.time.LocalDateTime

/**
  * バトラー。
  *
  * ゲーム内での戦闘キャラクター。
  */
class Battler extends Serializable {

  var id: Long = 0L
  var twitterId: String = null
  var screenName: String = ""
  var biography: String = ""
  var level: Int = 0
  var maxHitPoint: Long = 0L
  var maxSpecialPoint: Long = 0L
  var attackPoint: Long = 0L
  var defensePoint: Long = 0L
  var speedPoint: Long = 0L
  var imageUrl: String = ""
  var firstSignUpDate: LocalDateTime = null
  var generateDate: LocalDateTime = null

}
