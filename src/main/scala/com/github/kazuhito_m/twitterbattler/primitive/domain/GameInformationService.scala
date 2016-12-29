package com.github.kazuhito_m.twitterbattler.primitive.domain

import com.github.kazuhito_m.twitterbattler.primitive.domain.entity.Battler
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Service

/**
  * ゲームにおいての「全体的な」「静的な」情報をて提供するサービス。
  *
  * TODO : 主語が大きすぎて「なんでも屋」になりそう…いつでも再構成する心づもり。
  */
@Service
class GameInformationService {

  protected val log: Logger = LoggerFactory.getLogger(classOf[GameInformationService])

  /**
    * プレイヤー情報を取得する。
    *
    * @param id ID(TwitterID)。
    * @return 情報をつめたヤツ。
    */
  def getPlayer(id: String): Battler = {
    log.debug("サービス内のgetPlayerに到達、idは" + id)
    // TODO 仮実装、その改修。
    return new Battler(
      "テストのIDは" + id
      , "テキトーなプロフィーうr"
      , 1
      , 12
      , 5
      , 5
      , 3
    )
  }

}
