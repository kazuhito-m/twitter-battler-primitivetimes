package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller.twitter;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.TwitterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import twitter4j.auth.AccessToken;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@CrossOrigin
@RequestMapping("/api/twitter")
public class TwitterController {
    final TwitterRepository twitterRepository;

    @PostMapping("/authUrl")
    String authUrl(@RequestBody String callbackUrl) {
        // TODO これ、ちょっとめんどくさいので、素直なJSON等にするのが良さげ。
        String decodedUrl = URLDecoder
                .decode(callbackUrl, StandardCharsets.UTF_8)
                .replaceAll("=$", "");
        return twitterRepository.authUrl(decodedUrl);
    }

    @PostMapping("/accessToken")
    AccessTokenResponse accessToken(@RequestBody OAuthCallback callback) {
        AccessToken accessToken = twitterRepository.accessToken(callback.oAuthToken, callback.oAuthVerifier);
        return new AccessTokenResponse(accessToken.getToken(), accessToken.getTokenSecret());
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterController.class);

    public TwitterController(TwitterRepository twitterRepository) {
        this.twitterRepository = twitterRepository;
    }
}
