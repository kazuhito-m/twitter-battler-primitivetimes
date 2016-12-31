package com.github.kazuhito_m.twitterbattler.primitive.domain.generator

import java.util.Date

import com.github.kazuhito_m.twitterbattler.primitive.domain.entity.Battler
import org.springframework.social.twitter.api.TwitterProfile

/**
  * ゲーム内の「バトラーのパラメタ」を計算・生成するトレイト。
  */
trait BattlerParameterGenerator {

  val ONE_DAY_MS: Long = 1000 * 60 * 60 * 24

  /**
    * Twitterアカウントの情報からこのゲームのバトラーのパラメータを生成する。
    *
    * @param twitter         Twitterのプロファイル。
    * @param firstSignUpDate このゲーム開始(最初にログインした)時刻。
    */
  def generateBattler(twitter: TwitterProfile, firstSignUpDate: Date): Battler = {
    val battler = new Battler()
    battler.id = twitter.getName
    battler.screenName = twitter.getScreenName
    battler.biography = twitter.getDescription
    battler.level = calculateBattlerLevel(twitter)
    battler.maxHitPoint = calculateMaxHitPoint(twitter)
    battler.maxSpecialPoint = calculateMaxSpecialPoint(twitter)
    battler.attackPoint = calculateAttackPoint(twitter)
    battler.defensePoint = calculateDefensePoint(twitter)
    battler.speedPoint = calculateSpeedPoint(twitter)
    battler.imageUrl = twitter.getProfileImageUrl
    battler.firstSignUpDate = firstSignUpDate
    battler.generateDate = new Date()
    battler
  }

  /**
    * Twitter情報からバトラーのレベルを計算する。
    */
  def calculateBattlerLevel(profile: TwitterProfile): Int =
    (Math.log(profile.getStatusesCount / 100) / Math.log(2.0) + 1.0).asInstanceOf[Int]

  /**
    * Twitter情報からバトラーの「最大HP」を計算する。
    */
  def calculateMaxHitPoint(profile: TwitterProfile): Long = 0 // TODO 実装

  /**
    * Twitter情報からバトラーの「最大スペシャルポイント」を計算する。
    */
  def calculateMaxSpecialPoint(profile: TwitterProfile): Long = 0 // TODO 実装

  /**
    * Twitter情報からバトラーの「攻撃力」を計算する。
    */
  def calculateAttackPoint(profile: TwitterProfile): Long = profile.getFollowersCount

  /**
    * Twitter情報からバトラーの「防御力」を計算する。
    */
  def calculateDefensePoint(profile: TwitterProfile): Long = profile.getFriendsCount

  /**
    * Twitter情報からバトラーの「素早さ」を計算する。
    */
  def calculateSpeedPoint(profile: TwitterProfile) =
    profile.getStatusesCount / (getBetweenDays(profile.getCreatedDate, new Date()) + 1) + 1 // ツイート数 ÷ 日付 + 1

  /**
    * ２つの日付の間隔(日数)を計算する。
    */
  def getBetweenDays(from: Date, to: Date): Long = (to.getTime - from.getTime) / ONE_DAY_MS

}
