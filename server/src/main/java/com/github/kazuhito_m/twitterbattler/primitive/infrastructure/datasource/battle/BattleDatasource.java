package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battle;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.BattleRepository;
import com.github.kazuhito_m.twitterbattler.primitive.application.repository.BattlerRepository;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.Battle;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.BattleScene;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.Turn;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.Turns;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerIdentifier;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.party.PartyFactory;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.party.PartyStatus;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BattleDatasource implements BattleRepository {
    /**
     * 戦闘画面シーンIDのキーヘッド
     */
    private static final String BATTLE_SCENE_ID_KEY_PREFIX = "battleSceneId:";

    /**
     * 戦闘データのIDのキーヘッド
     */
    private static final String BATTLE_ID_KEY_PREFIX = "battleId:";

    final BattlerRepository battlerRepository;
    final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveBattleScene(String playerTwitterId, BattleScene battleScene) {
        BattlerIdentifier id = battlerRepository.convertTwitterIdToId(playerTwitterId);
        ofv().set(makeKeyForSceneId(id), battleScene);
    }

    @Override
    public void deleteBattleScene(String playerTwitterId) {
        BattlerIdentifier id = battlerRepository.convertTwitterIdToId(playerTwitterId);
        redisTemplate.delete(makeKeyForSceneId(id));
    }

    @Override
    public boolean isExistsBattleScene(String playerTwitterId) {
        return getBattleScene(playerTwitterId) != null;
    }

    @Override
    public BattleScene getBattleScene(String playerTwitterId) {
        var id = battlerRepository.convertTwitterIdToId(playerTwitterId);
        return (BattleScene) ofv().get(makeKeyForSceneId(id));
    }

    @Override
    public Battle createBattle(String playerTwitterId) {
        var id = battlerRepository.convertTwitterIdToId(playerTwitterId);
        var player = battlerRepository.get(id);
        var partyFactory = new PartyFactory(battlerRepository);

        var partyStatus = new PartyStatus(
                partyFactory.createPlayerParty(player),
                partyFactory.createEnemyParty()
        );

        var turns = new Turns(new Turn(partyStatus));
        var newBattle = new Battle(turns);

        registerBattle(id, newBattle);

        return newBattle;
    }

    @Override
    public void registerBattle(String playerTwitterId, Battle battle) {
        var id = battlerRepository.convertTwitterIdToId(playerTwitterId);
        registerBattle(id, battle);
    }

    @Override
    public Battle getBattle(String playerTwitterId) {
        var id = battlerRepository.convertTwitterIdToId(playerTwitterId);
        return (Battle) ofv().get(makeKeyForBattleId(id));
    }

    /**
     * RedisにBattleSceneオブジェクトを保存する文字列キーを作成する。
     */
    private String makeKeyForSceneId(BattlerIdentifier identifier) {
        return BATTLE_SCENE_ID_KEY_PREFIX + identifier;
    }

    private void registerBattle(BattlerIdentifier identifier, Battle battle) {
        ofv().set(makeKeyForBattleId(identifier), battle);
    }

    private String makeKeyForBattleId(BattlerIdentifier identifier) {
        return BATTLE_ID_KEY_PREFIX + identifier;
    }

    /**
     * エイリアス
     */
    private ValueOperations ofv() {
        return redisTemplate.opsForValue();
    }

    BattleDatasource(BattlerRepository battlerRepository, RedisTemplate<String, Object> redisTemplate) {
        this.battlerRepository = battlerRepository;
        this.redisTemplate = redisTemplate;
    }
}
