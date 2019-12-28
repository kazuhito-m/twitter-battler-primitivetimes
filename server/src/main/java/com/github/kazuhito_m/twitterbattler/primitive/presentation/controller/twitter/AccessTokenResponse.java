package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller.twitter;

public class AccessTokenResponse {
    final String token;
    final String tokenSecret;

    public String getToken() {
        return token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public AccessTokenResponse(String token, String tokenSecret) {
        this.token = token;
        this.tokenSecret = tokenSecret;
    }
}
