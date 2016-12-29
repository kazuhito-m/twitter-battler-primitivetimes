package com.github.kazuhito_m.twitterbattler.primitive.domain.entity

import scala.beans.BeanProperty

/**
  * バトラー。
  *
  * ゲーム内での戦闘キャラクター。
  */
case class Battler(
                    @BeanProperty id: String
                    , @BeanProperty biography: String
                    , @BeanProperty level: Int
                    , @BeanProperty maxHitPoint: Long
                    , @BeanProperty maxSpecialPoint: Long
                    , @BeanProperty attackPoint: Long
                    , @BeanProperty defensePoint: Long
                  )
