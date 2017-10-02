package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler

import java.time.LocalDateTime

import org.springframework.social.twitter.api.TwitterProfile

object BattlerFactory extends BattlerParameterGenerator {

  /** s
    * Twitterのプロファイルオブジェクトを元にBattlerオブジェクトを生成する。
    *
    * @param profile Twitterからのプロフィールオブジェクト。
    * @return ゲーム内のBattlerオブジェクト。
    */
  def create(profile: TwitterProfile): Battler = create(profile, LocalDateTime.now())

  /**
    * Twitterのプロファイルオブジェクトを元にBattlerオブジェクトを生成する。
    *
    * @param profile Twitterからのプロフィールオブジェクト。
    * @return ゲーム内のBattlerオブジェクト。
    */
  def create(profile: TwitterProfile, firstSignUpDate: LocalDateTime): Battler = generateBattler(profile, firstSignUpDate)

}