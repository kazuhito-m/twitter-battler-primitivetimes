package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.config.twitter;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = Twitter4jProperties.TWITTER4J_PREFIX)
public class Twitter4jProperties {
    public static final String TWITTER4J_PREFIX = "twitter4j";

    private Boolean debug = false;

    @NestedConfigurationProperty
    private OAuth oauth = new OAuth();

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public OAuth getOauth() {
        return oauth;
    }

    public void setOauth(OAuth oauth) {
        this.oauth = oauth;
    }
}