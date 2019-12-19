package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.transfer.twitter;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.TwitterRepository;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerIdentifier;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.twitter.TbTwitterException;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.twitter.TwitterUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import twitter4j.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Repository
public class TwitterTransfer implements TwitterRepository {
    static final Logger LOGGER = LoggerFactory.getLogger(TwitterTransfer.class);

    final Twitter twitter = TwitterFactory.getSingleton();

    @Override
    public BattlerIdentifier convertScreenNameToId(String screenName) {
        try {
            ResponseList<User> users = twitter.users().lookupUsers(screenName);
            long twitterId = users.get(0).getId();
            return new BattlerIdentifier(twitterId);
        } catch (TwitterException e) {
            LOGGER.error("Twitterとのやり取りに失敗。", e);
            throw new TbTwitterException(e);
        }
    }

    @Override
    public List<TwitterUser> getRandomAccounts() {
        // 「だれでも使ってそうなワード(というか文字)」でツイートを検索、その発信者のIDを貯める。
        var accounts = searchAccountsFromTweetWord("は");
        return List.copyOf(accounts);
    }

    @Override
    public Set<TwitterUser> searchAccountsFromTweetWord(String word) {
        try {
            QueryResult result = twitter.search()
                    .search(new Query().query(word));
            return result.getTweets()
                    .stream()
                    .map(tweet -> tweet.getUser())
                    .filter(user -> !user.isProtected())
                    .map(TwitterUser::new)
                    .collect(toSet());
        } catch (TwitterException e) {
            LOGGER.error("Twitterとのやり取りに失敗。", e);
            throw new TbTwitterException(e);
        }
    }

    @Override
    public TwitterUser getProfile(BattlerIdentifier identifier) {
        try {
            ResponseList<User> users = twitter.users().lookupUsers(identifier.value());
            return new TwitterUser(users.get(0));
        } catch (TwitterException e) {
            LOGGER.error("Twitterとのやり取りに失敗。", e);
            throw new TbTwitterException(e);
        }
    }

    @Override
    public List<Long> getFollowers(BattlerIdentifier identifier) {
        try {
            IDs followersIDs = twitter.getFollowersIDs(identifier.value(), -1);
            return Arrays.stream(followersIDs.getIDs())
                    .mapToObj(i -> i)
                    .collect(toList());
        } catch (TwitterException e) {
            LOGGER.error("Twitterとのやり取りに失敗。", e);
            throw new TbTwitterException(e);
        }
    }

    @Override
    public List<Long> getFollows(BattlerIdentifier identifier) {
        try {
            IDs friendsIDs = twitter.getFriendsIDs(identifier.value(), -1);
            return Arrays.stream(friendsIDs.getIDs())
                    .mapToObj(i -> i)
                    .collect(toList());
        } catch (TwitterException e) {
            LOGGER.error("Twitterとのやり取りに失敗。", e);
            throw new TbTwitterException(e);
        }
    }
}
