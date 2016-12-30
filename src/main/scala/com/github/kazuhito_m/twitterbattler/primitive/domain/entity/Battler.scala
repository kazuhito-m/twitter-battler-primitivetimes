package com.github.kazuhito_m.twitterbattler.primitive.domain.entity


import java.util.Date

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

import scala.beans.BeanProperty

/**
  * バトラー。
  *
  * ゲーム内での戦闘キャラクター。
  */
@RedisHash("battler")
case class Battler(
                    @BeanProperty @Id id: String
                    , @BeanProperty screenName: String
                    , @BeanProperty biography: String
                    , @BeanProperty level: Int
                    , @BeanProperty maxHitPoint: Long
                    , @BeanProperty maxSpecialPoint: Long
                    , @BeanProperty attackPoint: Long
                    , @BeanProperty defensePoint: Long
                    , @BeanProperty speedPoint: Long
                    , @BeanProperty imageUrl: String
                    , @BeanProperty firstSignUpDate: Date
                    , @BeanProperty generateDate: Date
                  )
