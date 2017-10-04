package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.twitter

import org.springframework.social.twitter.api.{Twitter, TwitterProfile}
import org.springframework.stereotype.Repository

import scala.collection.JavaConverters._
import scala.math.{floor, random}

@Repository
class TwitterDataSource(twitter: Twitter) {

  def convertScreenNameToId(screenName: String): Long = twitter.userOperations().getUserProfile(screenName).getId

  def getRandomId(): Long = {
    val ids: List[Long] = getRandomIds()
    val randomIndex = floor(random * ids.size).toInt
    ids(randomIndex)
  }

  def getRandomIds(): List[Long] = List[Long]() // TODO 本実装(現在仮実装)。

  def getProfile(id: Long): TwitterProfile = twitter.userOperations().getUserProfile(id)

  def getFollowers(id: Long): List[Long] = javaToScalaLongList(twitter.friendOperations().getFollowerIds(id))

  def getFollows(id: Long): List[Long] = javaToScalaLongList(twitter.friendOperations().getFriendIds(id))

  private def javaToScalaLongList(source: java.util.List[java.lang.Long]): List[Long] = source.asScala
    .toList
    .map { id => id.longValue() }

}
