package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller.sample;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.Commands;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.PartyActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.function.Function;

@RestController
@RequestMapping("/api/twitter")
public class SampleTwitterController {
    final Twitter twitter;
    final RedisTemplate<String, Object> redisTemplate;
    final TwitterDataSource twitterRepository:

    final static Logger logger = LoggerFactory.getLogger(SampleTwitterController.class);


    @GetMapping("tl")
    String timeline(Principal user) {
        return opeTwitter(user,
                (name) -> twitter.timelineOperations().getUserTimeline("@" + name)
        );
    }


    String opeTwitter(Principal user, Function<String, Object> f) {
        if (user == null) return null;
//        return f(user.getName());
        // TODO ↑の復活
        return null;
    }

    @GetMapping("profile")
    String profile(Principal user) {
        return opeTwitter(user, (name) -> twitter.userOperations().getUserProfile(name)),
    }

    @GetMapping("commands")
    Commands commands() {
        return new Commands(PartyActivity.Fight);
    }

    @PostMapping("sendCommands")
    void sendCommands(@RequestBody Commands commands) {
        if (commands == null) {
            logger.info("commandsはnull");
            return;
        }
        logger.info("commands.partyActivity:" + commands.partyActivity().toString());
        //    logger.info("commands.partyActivity:" + commands)
    }

    //  @RequestMapping(value = Array("jsonTest"), method = Array(GET))
    //  def jsonTest(): Unit = {
    //    val one: Battler = new Battler
    //    one.id = 1
    //    one.twitterId = "testone"
    //    val two: Battler = new Battler
    //    two.id = 2
    //    two.twitterId = "testtwo"
    //
    //    //    val battlers: Batlers = new Batlers(one, two)
    //    val battlers: Batlers = new Batlers(List(one, two))
    //    redisTemplate.opsForValue.set("test:abcd", battlers)
    //
    //  }

    @GetMapping("twitterTest")
    String twitterTest() {
        return twitter.timelineOperations().getHomeTimeline();
    }
}
