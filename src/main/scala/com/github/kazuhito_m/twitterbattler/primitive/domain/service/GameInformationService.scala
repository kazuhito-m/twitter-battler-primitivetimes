package com.github.kazuhito_m.twitterbattler.primitive.domain.service

import com.github.kazuhito_m.twitterbattler.primitive.domain.entity.Battler
import com.github.kazuhito_m.twitterbattler.primitive.domain.factory.BattlerFactory
import com.github.kazuhito_m.twitterbattler.primitive.domain.repository.TwitterRepository
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * ゲームにおいての「全体的な」「静的な」情報をて提供するサービス。
  *
  * TODO : 主語が大きすぎて「なんでも屋」になりそう…いつでも再構成する心づもり。
  */
@Service
class GameInformationService {

  protected val log: Logger = LoggerFactory.getLogger(classOf[GameInformationService])

  @Autowired
  val twitterRepository: TwitterRepository = null

  /**
    * プレイヤー情報を取得する。
    *
    * @param id ID(TwitterID)。
    * @return 情報をつめたヤツ。
    */
  def getPlayer(id: String): Battler = {
    // TODO Redisキャッシュ処理。
    val player = BattlerFactory.create(twitterRepository.getProfile(id))
    player
  }

}
