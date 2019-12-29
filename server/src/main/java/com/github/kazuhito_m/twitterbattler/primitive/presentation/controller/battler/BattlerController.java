package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller.battler;

import com.github.kazuhito_m.twitterbattler.primitive.application.service.battler.BattlerService;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/battler")
public class BattlerController {
    final BattlerService battlerService;

    @GetMapping("getPlayer")
    Battler getPlayer(Principal user) {
        return battlerService.getPlayer(user.getName());
    }

    BattlerController(BattlerService battlerService) {
        this.battlerService = battlerService;
    }
}
