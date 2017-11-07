package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.twitter

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerIdentifier
import org.springframework.social.twitter.api.{Twitter, TwitterProfile}
import org.springframework.stereotype.Repository

import scala.collection.JavaConverters._

@Repository
class TwitterDataSource(twitter: Twitter) {

  def convertScreenNameToId(screenName: String): BattlerIdentifier = {
    val twitterId = twitter.userOperations().getUserProfile(screenName).getId
    BattlerIdentifier(twitterId)
  }

  def getRandomAccounts(): List[TwitterProfile] = {
    // 「だれでも使ってそうなワード(というか文字)」でツイートを検索、その発信者のIDを貯める。
    var accounts: Set[TwitterProfile] = searchAccountsFromTweetWord("は")
    //    var idsB: Set[Long] = searchIdFromTweetWord("a")
    //    var idsC: Set[Long] = searchIdFromTweetWord("今")
    //    (idsA ++ idsB ++ idsC).toList
    accounts.toList
  }

  private def searchAccountsFromTweetWord(word: String): Set[TwitterProfile] = {
    val searchOpe = twitter.searchOperations()
    return searchOpe.search(word)
      .getTweets
      .asScala
      .map { tweet => tweet.getUser }
      .filter(!_.isProtected)
      .toSet
  }

  def getProfile(identifier: BattlerIdentifier): TwitterProfile = twitter.userOperations().getUserProfile(identifier.value)

  def getFollowers(identifier: BattlerIdentifier): List[Long] = javaToScalaLongList(twitter.friendOperations().getFollowerIds(identifier.value))

  def getFollows(identifier: BattlerIdentifier): List[Long] = javaToScalaLongList(twitter.friendOperations().getFriendIds(identifier.value))

  private def javaToScalaLongList(source: java.util.List[java.lang.Long]): List[Long] = source.asScala
    .toList
    .map { id => id.longValue() }

}
