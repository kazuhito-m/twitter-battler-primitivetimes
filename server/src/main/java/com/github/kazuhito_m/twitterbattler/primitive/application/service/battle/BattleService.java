package com.github.kazuhito_m.twitterbattler.primitive.application.service.battle;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.BattleRepository;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.Battle;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.BattleFacilitator;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.BattleScene;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.Commands;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.PartyActivity;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.result.Result;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.result.VictoryOrDefeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// TODO : 主語が大きすぎて「なんでも屋」になりそう…いつでも再構成する心づもり。
@Service
public class BattleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BattleService.class);

    final BattleRepository battleRepository;

    /**
     * 現在の「指定ユーザの戦闘画面シーンID」を返す。
     */
    public String getBattleSceneId(String playerTwitterId) {
        BattleScene scene = battleRepository.getBattleScene(playerTwitterId);
        return scene == null ? null : scene.id();
    }

    /**
     * 敵の決定とプレイヤーと敵のパーティーメンバーを生成する。
     */
    public void makeEnemyAndParty(String playerTwitterId) {
        // 敵の決定とプレイヤーと敵のパーティーメンバーを生成
        battleRepository.createBattle(playerTwitterId);
        // シーンを入れ替える。
        battleRepository.saveBattleScene(playerTwitterId, BattleScene.PartyMake);
    }

    /**
     * 設定された敵と両パーティーの情報を元に、バトルを開始する。
     */
    public void startBattle(String playerTwitterId) {
        // シーンを入れ替える。
        battleRepository.saveBattleScene(playerTwitterId, BattleScene.BattleOperation);
    }

    /**
     * 入力(クライアントからのJSON)をもとに「戦闘１ターン分」すすめる。
     */
    public void operationForBattleTurn(String playerTwitterId, Commands commands) {
        // １ターン分の内部戦闘処理と戦闘を勧めた結果を計算。
        Battle battle = battleRepository.getBattle(playerTwitterId);
        // コマンドで「こうさん」してたら、即時終了。
        if (commands.partyActivity() == PartyActivity.Surrender) {
            Battle lossBattle = battle.withResult(new Result(true, VictoryOrDefeat.Loss));
            battleRepository.registerBattle(playerTwitterId, lossBattle);
            battleRepository.saveBattleScene(playerTwitterId, BattleScene.BattleResult);
        }
        // コマンドで「たたかう」なら、ターンを一つすすめる。
        Battle battleFowardOneTurn = (new BattleFacilitator()).proceedNextTurn(commands, battle);
        // 結果、勝敗がついてたら、シーンを入れかえる。
        if (battleFowardOneTurn.hasEnded()) battleRepository.saveBattleScene(playerTwitterId, BattleScene.BattleResult);
    }

    /**
     * 戦闘結果を確認したことをサーバに表明する。
     */
    public void okBattleResult(String playerTwitterId) {
        // 戦闘データの後始末などを行う。
        // TODO 実装
        // シーンを破棄する。
        battleRepository.saveBattleScene(playerTwitterId, null);
    }

    public Battle getBattle(String playerTwitterId) {
        return battleRepository.getBattle(playerTwitterId);
    }

    BattleService(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }
}
