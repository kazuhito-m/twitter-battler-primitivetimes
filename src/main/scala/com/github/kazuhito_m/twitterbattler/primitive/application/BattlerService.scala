package com.github.kazuhito_m.twitterbattler.primitive.application

import java.util.Date

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.{Battler, BattlerRepository}
import com.github.kazuhito_m.twitterbattler.primitive.infrastructure.twitter.TwitterDataSource
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Service

/**
  * 戦闘キャラクタに関するサービス。
  */
@Service
class BattlerService(
                      battlerRepository: BattlerRepository,
                      twitterRepository: TwitterDataSource
                    ) {

  /** Battlerの再生成の間隔(つまりキャッシュの保存期間) */
  val INTARVAL_OF_REGENERATE_BATTLER = 24 * 60 * 60 * 1000;

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattlerService])

  /**
    * プレイヤー情報を取得する。
    *
    * @param id ID(TwitterID)。
    * @return 情報をつめたヤツ。
    */
  def getPlayer(id: String): Battler = {
    // まず、既存のユーザを取得。
    var player = battlerRepository.get(id)
    // あればそれを、なければ生成し保存する。
    if (player == null || isOverIntervalRegenerate(player.generateDate)) {
      player = battlerRepository.create(id)
      log.debug("プレイヤーの情報を作成しました。id:" + player.id + ",lv:" + player.level)
    }
    player
  }

  /**
    * Player情報の再作成間隔を過ぎているか否か。
    */
  def isOverIntervalRegenerate(lastGenerateDate: Date): Boolean =
    (new Date().getTime - lastGenerateDate.getTime) > INTARVAL_OF_REGENERATE_BATTLER

}
