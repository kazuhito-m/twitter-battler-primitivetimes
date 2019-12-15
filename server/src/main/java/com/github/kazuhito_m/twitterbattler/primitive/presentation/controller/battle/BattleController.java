package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller.battle;

import com.github.kazuhito_m.twitterbattler.primitive.application.service.battle.BattleService;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.Battle;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.Commands;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/battle")
public class BattleController {
    final BattleService battleService;

    @GetMapping("getBattleSceneId")
    String getBattleSceneId(Principal user) {
        battleService.getBattleSceneId(user.getName());
    }

    @PostMapping("makeEnemyAndParty")
    void makeEnemyAndParty(Principal user) {
        battleService.makeEnemyAndParty(user.getName());
    }

    @GetMapping("getBattle")
    Battle getBattle(Principal user) {
        return battleService.getBattle(user.getName());
    }

    @PostMapping("startBattle")
    void startBattle(Principal user) {
        battleService.startBattle(user.getName());
    }

    @PostMapping("operationForBattleTurn")
    void operationForBattleTurn(Principal user,
                                @RequestBody Commands commands) {
        battleService.operationForBattleTurn(user.getName(), commands);
    }

    @GetMapping("okBattleResult")
    void okBattleResult(Principal user) {
        battleService.okBattleResult(user.getName());
    }

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }
}
