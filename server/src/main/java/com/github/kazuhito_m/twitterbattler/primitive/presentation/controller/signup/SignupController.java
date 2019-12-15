package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller.signup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.beans.FeatureDescriptor;

@Controller
public class SignupController {
    final ConnectionFactoryLocator connectionFactoryLocator;
    final UsersConnectionRepository connectionRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);

    ProviderSignInUtils signInUtils = new ProviderSignInUtils();

    @GetMapping("/signup")
    String signup(WebRequest request) {
        //    log.info("signup() が呼ばれた時。")
        //    log.info("signInUtils : " + signInUtils)
        FeatureDescriptor connection = signInUtils.getConnectionFromSession(request);
        if (connection != null) {
            AuthUtil.authenticate(connection);
            signInUtils.doPostSignUp(connection.getDisplayName(), request);
        }
        return "redirect:/";
    }

    @PostMapping("/signin")
    String signin(WebRequest request) {
        return "redirect:/login.html";
    }

}
