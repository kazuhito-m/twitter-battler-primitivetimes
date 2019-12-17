package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler;

import java.time.LocalDateTime;

public class BattlerFactory extends BattlerParameterGenerator {
    /**
     * Twitterのプロファイルオブジェクトを元にBattlerオブジェクトを生成する。
     *
     * @param profile Twitterからのプロフィールオブジェクト。
     * @return ゲーム内のBattlerオブジェクト。
     */
    public Battler create(TwitterProfile profile) {
        return create(profile, LocalDateTime.now());
    }

    /**
     * Twitterのプロファイルオブジェクトを元にBattlerオブジェクトを生成する。
     *
     * @param profile Twitterからのプロフィールオブジェクト。
     * @return ゲーム内のBattlerオブジェクト。
     */
    public Battler create(TwitterProfile profile, LocalDateTime firstSignUpDate) {
        return generateBattler(profile, firstSignUpDate);
    }
}
