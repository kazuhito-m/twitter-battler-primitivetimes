package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler

import java.time.LocalDateTime

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

import scala.beans.BeanProperty

/**
  * バトラー。
  *
  * ゲーム内での戦闘キャラクター。
  */
@RedisHash("battler")
class Battler extends Serializable {

  @BeanProperty
  @Id var id: String = null
  @BeanProperty var screenName: String = ""
  @BeanProperty var biography: String = ""
  @BeanProperty var level: Int = 0
  @BeanProperty var maxHitPoint: Long = 0L
  @BeanProperty var maxSpecialPoint: Long = 0L
  @BeanProperty var attackPoint: Long = 0L
  @BeanProperty var defensePoint: Long = 0L
  @BeanProperty var speedPoint: Long = 0L
  @BeanProperty var imageUrl: String = ""
  @BeanProperty var firstSignUpDate: LocalDateTime = null
  @BeanProperty var generateDate: LocalDateTime = null

}
