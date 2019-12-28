package com.github.kazuhito_m.twitterbattler.primitive.presentation.controller.twitter;

public class OAuthCallback {
    String oAuthToken;
    String oAuthVerifier;

    public String getoAuthToken() {
        return oAuthToken;
    }

    public void setoAuthToken(String oAuthToken) {
        this.oAuthToken = oAuthToken;
    }

    public String getoAuthVerifier() {
        return oAuthVerifier;
    }

    public void setoAuthVerifier(String oAuthVerifier) {
        this.oAuthVerifier = oAuthVerifier;
    }
}
