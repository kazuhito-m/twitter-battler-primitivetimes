package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller.sample;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.TwitterRepository;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.Commands;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.PartyActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.security.Principal;
import java.util.function.Function;

@RestController
@RequestMapping("/api/twitter")
public class SampleTwitterController {
    final Twitter twitter = TwitterFactory.getSingleton();
    final RedisTemplate<String, Object> redisTemplate;
    final TwitterRepository twitterRepository;

    final static Logger LOGGER = LoggerFactory.getLogger(SampleTwitterController.class);

//    @GetMapping("tl")
//    String timeline(Principal user) {
//        return opeTwitter(user,
//                (name) -> twitter.getUserTimeline("@" + name)
//        );
//    }


    String opeTwitter(Principal user, Function<String, Object> f) {
        if (user == null) return null;
//        return f(user.getName());
        // TODO ↑の復活
        return null;
    }

//    @GetMapping("profile")
//    String profile(Principal user) {
//        return opeTwitter(user, (name) -> twitter.getUser(name));
//    }

    @GetMapping("commands")
    Commands commands() {
        return new Commands(PartyActivity.Fight);
    }

    @PostMapping("sendCommands")
    void sendCommands(@RequestBody Commands commands) {
        if (commands == null) {
            LOGGER.info("commandsはnull");
            return;
        }
        LOGGER.info("commands.partyActivity:" + commands.partyActivity().toString());
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
    String twitterTest() throws TwitterException {
        return twitter.getHomeTimeline().toString();
    }

    public SampleTwitterController(RedisTemplate<String, Object> redisTemplate, TwitterRepository twitterRepository) {
        this.redisTemplate = redisTemplate;
        this.twitterRepository = twitterRepository;
    }
}
