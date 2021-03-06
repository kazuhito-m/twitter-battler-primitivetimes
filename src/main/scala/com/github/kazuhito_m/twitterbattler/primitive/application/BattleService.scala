package com.github.kazuhito_m.twitterbattler.primitive.application

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.BattleScene
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.{Commands, PartyActivity}
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.result.{Result, VictoryOrDefeat}
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.{Battle, BattleFacilitator, BattleRepository}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Service

/**
  * ゲームにおいての「全体的な」「静的な」情報をて提供するサービス。
  *
  * TODO : 主語が大きすぎて「なんでも屋」になりそう…いつでも再構成する心づもり。
  */
@Service
class BattleService(battleRepository: BattleRepository) {

  protected val log: Logger = LoggerFactory.getLogger(classOf[BattleService])

  /**
    * 現在の「指定ユーザの戦闘画面シーンID」を返す。
    */
  def getBattleSceneId(playerTwitterId: String): String = {
    val scene = battleRepository.getBattleScene(playerTwitterId)
    if (scene == null) null else scene.id
  }

  /**
    * 敵の決定とプレイヤーと敵のパーティーメンバーを生成する。
    */
  def makeEnemyAndParty(playerTwitterId: String): Unit = {
    // 敵の決定とプレイヤーと敵のパーティーメンバーを生成
    battleRepository.createBattle(playerTwitterId);
    // シーンを入れ替える。
    battleRepository.saveBattleScene(playerTwitterId, BattleScene.PartyMake)
  }

  /**
    * 設定された敵と両パーティーの情報を元に、バトルを開始する。
    */
  def startBattle(playerTwitterId: String): Unit = {
    // シーンを入れ替える。
    battleRepository.saveBattleScene(playerTwitterId, BattleScene.BattleOperation)
  }

  /**
    * 入力(クライアントからのJSON)をもとに「戦闘１ターン分」すすめる。
    */
  def operationForBattleTurn(playerTwitterId: String, commands: Commands): Unit = {
    // １ターン分の内部戦闘処理と戦闘を勧めた結果を計算。
    val battle = battleRepository.getBattle(playerTwitterId)
    // コマンドで「こうさん」してたら、即時終了。
    if (commands.partyActivity == PartyActivity.Surrender) {
      val lossBattle = battle.withResult(new Result(true, VictoryOrDefeat.Loss))
      battleRepository.registerBattle(playerTwitterId, lossBattle)
      battleRepository.saveBattleScene(playerTwitterId, BattleScene.BattleResult)
    }
    // コマンドで「たたかう」なら、ターンを一つすすめる。
    val battleFowardOneTurn = (new BattleFacilitator()).proceedNextTurn(commands, battle)
    // 結果、勝敗がついてたら、シーンを入れかえる。
    if (battleFowardOneTurn.hasEnded) battleRepository.saveBattleScene(playerTwitterId, BattleScene.BattleResult)
  }

  /**
    * 戦闘結果を確認したことをサーバに表明する。
    */
  def okBattleResult(playerTwitterId: String): Unit = {
    // 戦闘データの後始末などを行う。
    // TODO 実装
    // シーンを破棄する。
    battleRepository.saveBattleScene(playerTwitterId, null)
  }

  def getBattle(playerTwitterId: String): Battle = battleRepository.getBattle(playerTwitterId)

}
