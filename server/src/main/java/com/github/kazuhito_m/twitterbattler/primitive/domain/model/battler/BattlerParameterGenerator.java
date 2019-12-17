package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler;

import java.time.LocalDateTime;
import java.util.Date;

public class BattlerParameterGenerator {
    private static final long ONE_DAY_MS = 1000 * 60 * 60 * 24;

    /**
     * Twitterアカウントの情報からこのゲームのバトラーのパラメータを生成する。
     *
     * @param twitter         Twitterのプロファイル。
     * @param firstSignUpDate このゲーム開始(最初にログインした)時刻。
     */
    public Battler generateBattler(TwitterProfile twitter, LocalDateTime firstSignUpDate) {
        return new Battler(
                new BattlerIdentifier(twitter.getId()),
                twitter.getScreenName(),
                twitter.getName(),
                twitter.getDescription(),
                calculateBattlerLevel(twitter),
                new BattlerStatus(
                        calculateMaxHitPoint(twitter),
                        calculateMaxSpecialPoint(twitter)
                ),
                calculateAttackPoint(twitter),
                calculateDefensePoint(twitter),
                calculateSpeedPoint(twitter),
                twitter.getProfileImageUrl(),
                firstSignUpDate,
                LocalDateTime.now()
        );
    }

    /**
     * Twitter情報からバトラーのレベルを計算する。
     * <p>
     * 暫定 : (ツイート数 /100) ルート2
     */
    private int calculateBattlerLevel(TwitterProfile twitter) {
        return (Math.log(twitter.getStatusesCount() / 100 + 1) / Math.log(2.0) + 1.0);
    }

    /**
     * Twitter情報からバトラーの「最大HP」を計算する。
     * <p>
     * 暫定 : 1 + (ツイート数 / 10) + (日割りツイート数　*  (日割りフォロー数 * 100))
     */
    private long calculateMaxHitPoint(TwitterProfile twitter) {
        return 1 + (twitter.getStatusesCount() / 10) + (
                calculateSpeedPoint(twitter) * (twitter.getFriendsCount() * 100 /
                        getElapsedDay(twitter.getCreatedDate())));
    }

    /**
     * Twitter情報からバトラーの「最大スペシャルポイント」を計算する。
     * <p>
     * 暫定 : リスト登録数 ＊２ + いいね数
     */
    private long calculateMaxSpecialPoint(TwitterProfile twitter) {
        return (twitter.getListedCount() * 2) + twitter.getFavoritesCount();
    }

    /**
     * Twitter情報からバトラーの「攻撃力」を計算する。
     */
    private long calculateAttackPoint(TwitterProfile twitter) {
        return twitter.getFollowersCount();
    }

    /**
     * Twitter情報からバトラーの「防御力」を計算する。
     */
    private long calculateDefensePoint(TwitterProfile twitter) {
        return twitter.getFriendsCount();
    }

    /**
     * Twitter情報からバトラーの「素早さ」を計算する。
     * <p>
     * 暫定 : ツイート数 ÷ 日付 + 1
     */
    private long calculateSpeedPoint(TwitterProfile twitter) {
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
