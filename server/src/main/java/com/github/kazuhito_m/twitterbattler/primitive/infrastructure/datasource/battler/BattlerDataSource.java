package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battler;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.BattleRepository;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.Battle;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.BattleScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BattlerDataSource implements BattleRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BattlerDataSource.class);

    /**
     * バトラーのキーヘッド
     */
    private final static String BATTLER_KEY_PREFIX = "battler:";

    @Override
    public void saveBattleScene(String playerTwitterId, BattleScene battleScene) {

    }

    @Override
    public void deleteBattleScene(String playerTwitterId) {

    }

    @Override
    public boolean isExistsBattleScene(String playerTwitterId) {
        return false;
    }

    @Override
    public BattleScene getBattleScene(String playerTwitterId) {
        return null;
    }

    @Override
    public Battle createBattle(String playerTwitterId) {
        return null;
    }

    @Override
    public void registerBattle(String playerTwitterId, Battle battle) {

    }

    @Override
    public Battle getBattle(String playerTwitterId) {
        return null;
    }

//    override def convertTwitterIdToId(twitterId: String): BattlerIdentifier = {
//        // TODO あの手➖ーションなど「意識しないでキャッシュ出来る」方法
//        val key = "convertTwitterIdToId:" + twitterId
//        val hitId: String = ofv.get(key).asInstanceOf[String]
//        if (hitId != null) return BattlerIdentifier(hitId.toLong)
//        val identifier: BattlerIdentifier = twitterDataSource.convertScreenNameToId(twitterId)
//        ofv.set(key, identifier.toString)
//        identifier
//    }
//
//    override def create(identifier: BattlerIdentifier): Battler = {
//        val battler = BattlerFactory.create(twitterDataSource.getProfile(identifier))
//        registar(battler)
//        battler
//    }
//
//    private def getOrCreate(identifier: BattlerIdentifier): Battler = {
//        val existsBattler = get(identifier)
//        if (existsBattler != null) return existsBattler
//        return create(identifier)
//    }
//
//    override def registar(battler: Battler): Unit = ofv.set(makeKey(battler.identifier), battler)
//
//    override def get(identifier: BattlerIdentifier): Battler = ofv.get(makeKey(identifier)).asInstanceOf[Battler]
//
//    override def delete(identifier: BattlerIdentifier): Unit = redisTemplate.delete(makeKey(identifier))
//
//    override def isExists(identifier: BattlerIdentifier): Boolean = get(identifier) != null
//
//    override def randomFriendBattlers(identifier: BattlerIdentifier, size: Int): List[Battler] = {
//        val choicesIds: List[Long] = randomFriendIds(identifier, size)
//        choicesIds.map { choicesId => getOrCreate(BattlerIdentifier(choicesId)) }
//    }
//
//    private def randomFriendIds(identifier: BattlerIdentifier, size: Int): List[Long] = {
//        val ids: List[Long] = twitterDataSource.getFollowers(identifier)
//        val choicesIds: List[Long] = new RandomChoiceIdList(ids).choice(size)
//        if (choicesIds.size >= size) return choicesIds;
//
//        val followIds: List[Long] = twitterDataSource.getFollows(identifier)
//        val choiceWithFollowIds = choicesIds ++ new RandomChoiceIdList(followIds).choice(size - choicesIds.size)
//        if (choiceWithFollowIds.size >= size) return choiceWithFollowIds;
//
//        val randomIds: List[Long] = this.getRandomIds()
//        choiceWithFollowIds ++ new RandomChoiceIdList(randomIds).choice(size - choiceWithFollowIds.size)
//    }
//
//    override def randomOneBattler(): Battler = {
//        getOrCreate(getRandomId)
//    }
//
//    private val RANDOM_IDS_KEY: String = "randomIds"
//
//    /**
//     * 因果の無いTwitterランダムID群を取得する。
//     * その際、アクセスにコストがかかるので、BattlerデータとIDデータをRedisに作成・保存する。
//     */
//    private def getRandomIds(): List[Long] = {
//        val accounts = twitterDataSource.getRandomAccounts()
//        // バトラーを作りながら保存。
//        accounts.foreach(account => registar(BattlerFactory.create(account)))
//        // IDだけを抽出。
//        val ids: List[Long] = accounts.map { account => account.getId }
//        // Twitter側からがゼロ(RateOvetとか)なら、Redis側から出して返す。
//        if (ids.isEmpty) {
//            val idSet: util.Set[Object] = redisTemplate.opsForSet().members(RANDOM_IDS_KEY)
//            return idSet.asScala.map { value => value.asInstanceOf[Long] }.toList
//        }
//        // Twitter側で取得できた場合、Redisに保存する。
//        redisTemplate.opsForSet.add(RANDOM_IDS_KEY, ids.toArray)
//        ids;
//    }
//
//    private def getRandomId(): BattlerIdentifier = {
//        val accounts: List[Long] = getRandomIds()
//        val randomIndex = floor(random * accounts.size).toInt
//        BattlerIdentifier(accounts(randomIndex))
//    }
//
//    /** RedisにBattlerオブジェクトを保存する文字列キーを作成する。 */
//    private def makeKey(identifier: BattlerIdentifier): String = BATTLER_KEY_PREFIX + identifier
//
//    /** エイリアス */
//    private def ofv = redisTemplate.opsForValue

}
