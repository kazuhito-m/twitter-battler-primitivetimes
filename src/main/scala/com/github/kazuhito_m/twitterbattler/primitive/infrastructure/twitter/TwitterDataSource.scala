package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.twitter

import java.lang

import org.springframework.social.twitter.api.{CursoredList, Twitter, TwitterProfile}
import org.springframework.stereotype.Repository

@Repository
class TwitterDataSource(twitter: Twitter) {

  def convertScreenNameToId(screenName: String): Long = twitter.userOperations().getUserProfile(screenName).getId

  /**
    * Twitterから「指定されたIDのプロフィール情報」を取得し、オブジェクトで返す。
    *
    * @param id TwitterID。
    * @return プロフィールオブジェクト。
    */
  def getProfile(id: Long): TwitterProfile = twitter.userOperations().getUserProfile(id)

  def getFollowers(id: Long): CursoredList[lang.Long] = twitter.friendOperations().getFollowerIds(id)

  def getFollows(id: Long): CursoredList[lang.Long] = twitter.friendOperations().getFriendIds(id)

}
