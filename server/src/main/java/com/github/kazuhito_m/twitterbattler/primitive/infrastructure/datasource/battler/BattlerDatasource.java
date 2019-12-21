package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battler;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.BattlerRepository;
import com.github.kazuhito_m.twitterbattler.primitive.application.repository.TwitterRepository;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerFactory;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerIdentifier;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.RandomChoiceIdList;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Repository
public class BattlerDatasource implements BattlerRepository {
    /**
     * バトラーのキーヘッド
     */
    private static final String BATTLER_KEY_PREFIX = "battler:";

    final RedisTemplate<String, Object> redisTemplate;
    final TwitterRepository twitterRepository;

    @Override
    public BattlerIdentifier convertTwitterIdToId(String twitterId) {
        // TODO あの手➖ーションなど「意識しないでキャッシュ出来る」方法
        var key = "convertTwitterIdToId:" + twitterId;
        String hitId = (String) ofv().get(key);
        if (hitId != null) return new BattlerIdentifier(Long.valueOf(hitId));
        BattlerIdentifier identifier = twitterRepository.convertScreenNameToId(twitterId);
        ofv().set(key, identifier.toString());
        return identifier;
    }

    @Override
    public Battler create(BattlerIdentifier identifier) {
        var battler = battlerFactory().create(twitterRepository.getProfile(identifier));
        register(battler);
        return battler;
    }

    @Override
    public void register(Battler battler) {
        ofv().set(makeKey(battler.identifier()), battler);

    }

    @Override
    public Battler get(BattlerIdentifier identifier) {
        return (Battler) ofv().get(makeKey(identifier));
    }

    @Override
    public void delete(BattlerIdentifier identifier) {
        redisTemplate.delete(makeKey(identifier));
    }

    @Override
    public boolean isExists(BattlerIdentifier identifier) {
        return get(identifier) != null;
    }

    @Override
    public Battler randomOneBattler() {
        return getOrCreate(getRandomId());
    }

    @Override
    public List<Battler> randomFriendBattlers(BattlerIdentifier identifier, int size) {
        List<Long> choicesIds = randomFriendIds(identifier, size);
        return choicesIds.stream()
                .map(choicesId -> getOrCreate(new BattlerIdentifier(choicesId)))
                .collect(toList());
    }

    private Battler getOrCreate(BattlerIdentifier identifier) {
        var existsBattler = get(identifier);
        if (existsBattler != null) return existsBattler;
        return create(identifier);
    }

    List<Long> randomIds() {
        return getRandomIds();
    }

    private List<Long> randomFriendIds(BattlerIdentifier identifier, int size) {
        List<Long> ids = twitterRepository.getFollowers(identifier);
        List<Long> choicesIds = new RandomChoiceIdList(ids).choice(size);
        if (choicesIds.size() >= size) return choicesIds;

        List<Long> followIds = twitterRepository.getFollows(identifier);
        choicesIds.addAll(
                new RandomChoiceIdList(followIds).
                        choice(size - choicesIds.size())
        );
        List<Long> choiceWithFollowIds = choicesIds;
        if (choiceWithFollowIds.size() >= size) return choiceWithFollowIds;

        List<Long> randomIds = getRandomIds();
        choiceWithFollowIds.addAll(new RandomChoiceIdList(randomIds)
                .choice(size - choiceWithFollowIds.size()));
        return choiceWithFollowIds;
    }

    private static final String RANDOM_IDS_KEY = "randomIds";


    /**
     * 因果の無いTwitterランダムID群を取得する。
     * その際、アクセスにコストがかかるので、BattlerデータとIDデータをRedisに作成・保存する。
     */
    private List<Long> getRandomIds() {
        var accounts = twitterRepository.getRandomAccounts();
        // バトラーを作りながら保存。
        accounts.forEach(account -> register(battlerFactory().create(account)));
        // IDだけを抽出。
        List<Long> ids = accounts.stream()
                .map(account -> account.getId())
                .collect(toList());
        // Twitter側からがゼロ(RateOvetとか)なら、Redis側から出して返す。
        if (ids.isEmpty()) {
            Set<Object> idSet = redisTemplate.opsForSet()
                    .members(RANDOM_IDS_KEY);
            return idSet.stream()
                    .map(value -> (Long) value)
                    .collect(toList());
        }
        // Twitter側で取得できた場合、Redisに保存する。
        redisTemplate.opsForSet().add(RANDOM_IDS_KEY, ids.toArray());
        return ids;
    }

    private BattlerIdentifier getRandomId() {
        List<Long> accounts = getRandomIds();
        // TODO RandomChoiceIdList に同じロジックがある。統合を狙いたい。
        int randomIndex = (int) Math.floor(Math.random() * accounts.size());
        return new BattlerIdentifier(accounts.get(randomIndex));
    }


    /**
     * RedisにBattlerオブジェクトを保存する文字列キーを作成する。
     */
    private String makeKey(BattlerIdentifier identifier) {
        return BATTLER_KEY_PREFIX + identifier;
    }

    private BattlerFactory battlerFactory() {
        return new BattlerFactory();
    }

    /**
     * エイリアス
     */
    private ValueOperations ofv() {
        return redisTemplate.opsForValue();
    }

    BattlerDatasource(RedisTemplate<String, Object> redisTemplate, TwitterRepository twitterRepository) {
        this.redisTemplate = redisTemplate;
        this.twitterRepository = twitterRepository;
    }
}
