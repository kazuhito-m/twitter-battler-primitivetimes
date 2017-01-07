package com.github.kazuhito_m.twitterbattler.primitive.domain.battle

import java.util.Date

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
    battler.id = twitter.getScreenName
    battler.screenName = twitter.getName
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
    *
    * 暫定 : (ツイート数 /100) ルート2
    */
  def calculateBattlerLevel(twitter: TwitterProfile): Int =
    (Math.log(twitter.getStatusesCount / 100) / Math.log(2.0) + 1.0).asInstanceOf[Int]

  /**
    * Twitter情報からバトラーの「最大HP」を計算する。
    *
    * 暫定 : 1 + (ツイート数 / 10) + (日割りツイート数　*  (日割りフォロー数 * 100))
    */
  def calculateMaxHitPoint(twitter: TwitterProfile): Long =
    1 + (twitter.getStatusesCount / 10) + (calculateSpeedPoint(twitter) * (twitter.getFriendsCount * 100 / getElapsedDay(twitter.getCreatedDate)))

  /**
    * Twitter情報からバトラーの「最大スペシャルポイント」を計算する。
    *
    * 暫定 : リスト登録数 ＊２ + いいね数
    */
  def calculateMaxSpecialPoint(twitter: TwitterProfile): Long =
    (twitter.getListedCount * 2) + twitter.getFavoritesCount

  /**
    * Twitter情報からバトラーの「攻撃力」を計算する。
    */
  def calculateAttackPoint(twitter: TwitterProfile): Long = twitter.getFollowersCount

  /**
    * Twitter情報からバトラーの「防御力」を計算する。
    */
  def calculateDefensePoint(twitter: TwitterProfile): Long = twitter.getFriendsCount

  /**
    * Twitter情報からバトラーの「素早さ」を計算する。
    *
    * 暫定 : ツイート数 ÷ 日付 + 1
    */
  def calculateSpeedPoint(twitter: TwitterProfile) =
    twitter.getStatusesCount / getElapsedDay(twitter.getCreatedDate) + 1

  /**
    * ２つの日付の間隔(日数)を計算する。
    */
  def getBetweenDays(from: Date, to: Date): Long = (to.getTime - from.getTime) / ONE_DAY_MS

  /**
    * 指定した日付から現在の「経過日数」を計算する。
    * 一日目は「一日」と捉える。
    */
  def getElapsedDay(from:Date): Long=  getBetweenDays(from , new Date()) + 1

}
