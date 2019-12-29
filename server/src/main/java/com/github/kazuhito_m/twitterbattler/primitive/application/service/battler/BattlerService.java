package com.github.kazuhito_m.twitterbattler.primitive.application.service.battler;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.BattlerRepository;
import com.github.kazuhito_m.twitterbattler.primitive.application.repository.TwitterRepository;
import com.github.kazuhito_m.twitterbattler.primitive.application.service.battle.BattleService;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class BattlerService {
    /**
     * Battlerの再生成の間隔(つまりキャッシュの保存期間)
     */
    private static final int INTERVAL_OF_REGENERATE_BATTLER = 24 * 60 * 60 * 1000;

    final BattlerRepository battlerRepository;
    final TwitterRepository twitterRepository;

    public Battler getPlayer(String twitterId) {
        BattlerIdentifier id = battlerRepository.convertTwitterIdToId(twitterId);
        Battler player = battlerRepository.get(id);
        if (player == null || isOverIntervalRegenerate(player.generateDate())) {
            player = battlerRepository.create(id);
            LOGGER.debug("プレイヤーの情報を作成しました。id:" + player.identifier() + ",lv:" + player.level());
        }
        return player;
    }

    /**
     * Player情報の再作成間隔を過ぎているか否か。
     */
    private boolean isOverIntervalRegenerate(LocalDateTime lastGenerateDate) {
        long elapsed = ChronoUnit.MILLIS.between(lastGenerateDate, LocalDateTime.now());
        return elapsed > INTERVAL_OF_REGENERATE_BATTLER;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BattleService.class);

    BattlerService(BattlerRepository battlerRepository, TwitterRepository twitterRepository) {
        this.battlerRepository = battlerRepository;
        this.twitterRepository = twitterRepository;
    }
}
