package com.github.kazuhito_m.twitterbattler.primitive.application.repository;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerIdentifier;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.twitter.TwitterUser;

import java.util.List;
import java.util.Set;

public interface TwitterRepository {
    BattlerIdentifier convertScreenNameToId(String screenName);

    List<TwitterUser> getRandomAccounts();

    Set<TwitterUser> searchAccountsFromTweetWord(String word);

    TwitterUser getProfile(BattlerIdentifier identifier);

    List<Long> getFollowers(BattlerIdentifier identifier);

    List<Long> getFollows(BattlerIdentifier identifier);
}
