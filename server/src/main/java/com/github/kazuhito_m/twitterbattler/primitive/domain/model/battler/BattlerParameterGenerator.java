package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler;

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.twitter.TwitterUser;

import java.time.LocalDateTime;
import java.util.Date;

public class BattlerParameterGenerator {
    private static final long ONE_DAY_MS = 1000 * 60 * 60 * 24;

    /**
     * Twitterアカウントの情報からこのゲームのバトラーのパラメータを生成する。
     *
     * @param user            Twitterのプロファイル。
     * @param firstSignUpDate このゲーム開始(最初にログインした)時刻。
     */
    public Battler generateBattler(TwitterUser user, LocalDateTime firstSignUpDate) {
        BattlerStatus status = new BattlerStatus(
                calculateMaxHitPoint(user),
                calculateMaxSpecialPoint(user)
        );
        return new Battler(
                new BattlerIdentifier(user.getId()),
                user.getScreenName(),
                user.getName(),
                user.getDescription(),
                calculateBattlerLevel(user),
                status,
                calculateAttackPoint(user),
                calculateDefensePoint(user),
                calculateSpeedPoint(user),
                user.getProfileImageUrl(),
                firstSignUpDate,
                LocalDateTime.now(),
                status
        );
    }

    /**
     * Twitter情報からバトラーのレベルを計算する。
     * <p>
     * 暫定 : (ツイート数 /100) ルート2
     */
    private int calculateBattlerLevel(TwitterUser user) {
        return (int) (Math.log(user.getStatusesCount() / 100D + 1D) / Math.log(2.0) + 1.0);
    }

    /**
     * Twitter情報からバトラーの「最大HP」を計算する。
     * <p>
     * 暫定 : 1 + (ツイート数 / 10) + (日割りツイート数　*  (日割りフォロー数 * 100))
     */
    private long calculateMaxHitPoint(TwitterUser twitter) {
        return 1 + (twitter.getStatusesCount() / 10) + (
                calculateSpeedPoint(twitter) * (twitter.getFriendsCount() * 100 /
                        getElapsedDay(twitter.getCreatedDate())));
    }

    /**
     * Twitter情報からバトラーの「最大スペシャルポイント」を計算する。
     * <p>
     * 暫定 : リスト登録数 ＊２ + いいね数
     */
    private long calculateMaxSpecialPoint(TwitterUser user) {
        return (user.getListedCount() * 2) + user.getFavoritesCount();
    }

    /**
     * Twitter情報からバトラーの「攻撃力」を計算する。
     */
    private long calculateAttackPoint(TwitterUser twitter) {
        return twitter.getFollowersCount();
    }

    /**
     * Twitter情報からバトラーの「防御力」を計算する。
     */
    private long calculateDefensePoint(TwitterUser twitter) {
        return twitter.getFriendsCount();
    }

    /**
     * Twitter情報からバトラーの「素早さ」を計算する。
     * <p>
     * 暫定 : ツイート数 ÷ 日付 + 1
     */
    private long calculateSpeedPoint(TwitterUser twitter) {
        return twitter.getStatusesCount() / getElapsedDay(twitter.getCreatedDate()) + 1;
    }

    /**
     * ２つの日付の間隔(日数)を計算する。
     */
    private long getBetweenDays(Date from, Date to) {
        return (to.getTime() - from.getTime()) / ONE_DAY_MS;
    }

    /**
     * 指定した日付から現在の「経過日数」を計算する。
     * 一日目は「一日」と捉える。
     */
    private long getElapsedDay(Date from) {
        return getBetweenDays(from, new Date()) + 1;
    }
}
