package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller.twitter;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.TwitterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/twitter")

public class TwitterController {
    final TwitterRepository twitterRepository;

    @PostMapping("/authUrl")
    String authUrl(@Param("callbackUrl") String callbackUrl) {
        return twitterRepository.authUrl(callbackUrl);
    }

//
//
//    ProviderSignInUtils signInUtils = new ProviderSignInUtils();
//
//    @GetMapping("/signup")
//    String signup(WebRequest request) {
//        //    log.info("signup() が呼ばれた時。")
//        //    log.info("signInUtils : " + signInUtils)
//        FeatureDescriptor connection = signInUtils.getConnectionFromSession(request);
//        if (connection != null) {
//            AuthUtil.authenticate(connection);
//            signInUtils.doPostSignUp(connection.getDisplayName(), request);
//        }
//        return "redirect:/";
//    }
//
//    @PostMapping("/signin")
//    String signin(WebRequest request) {
//        return "redirect:/login.html";
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterController.class);

    public TwitterController(TwitterRepository twitterRepository) {
        this.twitterRepository = twitterRepository;
    }
}
