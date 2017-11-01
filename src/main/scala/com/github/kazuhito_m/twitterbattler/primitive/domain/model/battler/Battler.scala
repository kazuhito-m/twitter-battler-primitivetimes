package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler

import java.time.LocalDateTime

/**
  * バトラー。
  *
  * ゲーム内での戦闘キャラクター。
  */
class Battler(
               val id: Long,
               val twitterId: String,
               val screenName: String,
               val biography: String,
               val level: Int,
               val maxHitPoint: Long,
               val maxSpecialPoint: Long,
               val attackPoint: Long,
               val defensePoint: Long,
               val speedPoint: Long,
               val imageUrl: String,
               val firstSignUpDate: LocalDateTime,
               val generateDate: LocalDateTime
             ) {
  def this() = this(
    0L,
    null,
    "",
    "",
    0,
    0L,
    0L,
    0L,
    0L,
    0L,
    "",
    null,
    null
  )
}
