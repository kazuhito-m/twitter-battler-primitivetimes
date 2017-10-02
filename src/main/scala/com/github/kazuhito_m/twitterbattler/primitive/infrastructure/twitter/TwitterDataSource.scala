package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.twitter

import org.springframework.social.twitter.api.{Twitter, TwitterProfile}
import org.springframework.stereotype.Repository

@Repository
class TwitterDataSource(twitter: Twitter) {

  /**
    * Twitterから「指定されたIDのプロフィール情報」を取得し、オブジェクトで返す。
    *
    * @param id TwitterID。
    * @return プロフィールオブジェクト。
    */
  def getProfile(id: String): TwitterProfile = twitter.userOperations().getUserProfile(id)

}
