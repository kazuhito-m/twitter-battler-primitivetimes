package com.github.kazuhito_m.twitterbattler.primitive.domain.model.twitter;

import twitter4j.User;

import java.util.Date;
import java.util.Objects;

public class TwitterUser {
    final User value;

    public TwitterUser(User value) {
        this.value = value;
    }

    public long getId() {
        return value.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitterUser that = (TwitterUser) o;
        return value.getId() == that.value.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getScreenName() {
        return value.getScreenName();
    }

    public String getName() {
        return value.getName();
    }

    public String getDescription() {
        return value.getDescription();
    }

    public String getProfileImageUrl() {
        return value.getProfileImageURL();
    }

    public int getStatusesCount() {
        return value.getStatusesCount();
    }

    public int getFriendsCount() {
        return value.getFriendsCount();
    }

    public Date getCreatedDate() {
        return value.getCreatedAt();
    }

    public int getListedCount() {
        return value.getListedCount();
    }

    public int getFavoritesCount() {
        return value.getFavouritesCount();
    }

    public int getFollowersCount() {
        return value.getFollowersCount();
    }
}
