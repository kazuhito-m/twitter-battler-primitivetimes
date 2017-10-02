package com.github.kazuhito_m.twitterbattler.primitive.application

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.BattleScene
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.BattleRepository
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Service

/**
  * ゲームにおいての「全体的な」「静的な」情報をて提供するサービス。
  *
  * TODO : 主語が大きすぎて「なんでも屋」になりそう…いつでも再構成する心づもり。
  */
@Service
class GameInformationService(battleRepository: BattleRepository) {

  protected val log: Logger = LoggerFactory.getLogger(classOf[GameInformationService])

  /**
    * 現在の「指定ユーザの戦闘画面シーンID」を返す。
    */
  def getBattleSceneId(playerId: String): String = {
    val scene = battleRepository.getBattleScene(playerId)
    if (scene == null) null else scene.id
  }

  /**
    * 敵の決定とプレイヤーと敵のパーティーメンバーを生成する。
    */
  def makeEnemyAndParty(playerId: String): Unit = {
    // 敵の決定とプレイヤーと敵のパーティーメンバーを生成
    // TODO 実装
    // シーンを入れ替える。
    battleRepository.saveBattleScene(playerId, BattleScene.PartyMake)
  }

  /**
    * 設定された敵と両パーティーの情報を元に、バトルを開始する。
    */
  def startBattle(playerId: String): Unit = {
    // バトル情報登録、パーティの初期状態の記憶、など。
    // TODO 実装
    // シーンを入れ替える。
    battleRepository.saveBattleScene(playerId, BattleScene.BattleOperation)
  }

  /**
    * 入力(クライアントからのJSON)をもとに「戦闘１ターン分」すすめる。
    */
  def operationForBattleTurn(playerId: String): Unit = {
    // １ターン分の内部戦闘処理と戦闘を勧めた結果を計算。
    // TODO 実装
    // シーンを入れ替える。
    battleRepository.saveBattleScene(playerId, BattleScene.BattleResult)
  }

  /**
    * 戦闘結果を確認したことをサーバに表明する。
    */
  def okBattleResult(playerId: String): Unit = {
    // 戦闘データの後始末などを行う。
    // TODO 実装
    // シーンを破棄する。
    battleRepository.saveBattleScene(playerId, null)
  }


}