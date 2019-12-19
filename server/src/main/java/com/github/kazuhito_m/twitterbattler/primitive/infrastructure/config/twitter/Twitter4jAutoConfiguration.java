package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.config.twitter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@ConditionalOnClass({TwitterFactory.class, Twitter.class})
@EnableConfigurationProperties(Twitter4jProperties.class)
public class Twitter4jAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(Twitter4jAutoConfiguration.class);

    final Twitter4jProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public TwitterFactory twitterFactory() {

        if (this.properties.getOauth().getConsumerKey() == null
                || this.properties.getOauth().getConsumerSecret() == null
                || this.properties.getOauth().getAccessToken() == null
                || this.properties.getOauth().getAccessTokenSecret() == null) {
            String msg = "Twitter4j properties not configured properly." +
                    " Please check twitter4j.* properties settings in configuration file.";
            LOGGER.error(msg);
            throw new RuntimeException(msg);
        }

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(properties.getDebug())
                .setOAuthConsumerKey(properties.getOauth().getConsumerKey())
                .setOAuthConsumerSecret(properties.getOauth().getConsumerSecret())
                .setOAuthAccessToken(properties.getOauth().getAccessToken())
                .setOAuthAccessTokenSecret(properties.getOauth().getAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf;
    }

    @Bean
    @ConditionalOnMissingBean
    public Twitter twitter(TwitterFactory twitterFactory) {
        return twitterFactory.getInstance();
    }

    Twitter4jAutoConfiguration(Twitter4jProperties properties) {
        this.properties = properties;
    }
}
