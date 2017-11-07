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
               val maxStatus: BattlerStatus,
               val attackPoint: Long,
               val defensePoint: Long,
               val speedPoint: Long,
               val imageUrl: String,
               val firstSignUpDate: LocalDateTime,
               val generateDate: LocalDateTime,
               val nowStatus: BattlerStatus
             ) {
  def this(
            id: Long,
            twitterId: String,
            screenName: String,
            biography: String,
            level: Int,
            maxStatus: BattlerStatus,
            attackPoint: Long,
            defensePoint: Long,
            speedPoint: Long,
            imageUrl: String,
            firstSignUpDate: LocalDateTime,
            generateDate: LocalDateTime
          ) = this(
    id,
    twitterId,
    screenName,
    biography,
    level,
    maxStatus,
    attackPoint,
    defensePoint,
    speedPoint,
    imageUrl,
    firstSignUpDate,
    generateDate,
    maxStatus
  )
}
