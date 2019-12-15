package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource.battle;

import com.github.kazuhito_m.twitterbattler.primitive.application.repository.BattlerRepository;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler;
import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.BattlerIdentifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BattleDatasource implements BattlerRepository {
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
    public BattlerIdentifier convertTwitterIdToId(String twitterId) {
        return null;
    }

    @Override
    public Battler create(BattlerIdentifier identifier) {
        return null;
    }

    @Override
    public void register(Battler battler) {

    }

    @Override
    public Battler get(BattlerIdentifier identifier) {
        return null;
    }

    @Override
    public void delete(BattlerIdentifier identifier) {

    }

    @Override
    public boolean isExists(BattlerIdentifier identifier) {
        return false;
    }

    @Override
    public List<Battler> randomFriendBattlers(BattlerIdentifier identifier, int size) {
        return null;
    }

    @Override
    public Battler randomOneBattler() {
        return null;
    }


//    protected val log: Logger = LoggerFactory.getLogger(classOf[BattleDataSource])
//
//    /** RedisにBattleSceneオブジェクトを保存する文字列キーを作成する。 */
//    private def makeKeyForSceneId(identifier: BattlerIdentifier): String = BATTLE_SCENE_ID_KEY_PREFIX + identifier
//
//    override def saveBattleScene(playerTwitterId: String, battleScene: BattleScene): Unit = {
//        val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
//        ofv.set(makeKeyForSceneId(id), battleScene)
//    }
//
//    override def deleteBattleScene(playerTwitterId: String): Unit = {
//        val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
//        redisTemplate.delete(makeKeyForSceneId(id))
//    }
//
//    override def isExistsBattleScene(playerTwitterId: String): Boolean = getBattleScene(playerTwitterId) != null
//
//    override def getBattleScene(playerTwitterId: String): BattleScene = {
//        val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
//        ofv.get(makeKeyForSceneId(id)).asInstanceOf[BattleScene]
//    }
//
//    override def createBattle(playerTwitterId: String): Battle = {
//        val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
//        val player = battlerRepository.get(id)
//        val partyFactory: PartyFactory = new PartyFactory(battlerRepository)
//
//        val partyStatus: PartyStatus = new PartyStatus(
//                partyFactory.createPlayerParty(player),
//                partyFactory.createEnemyParty()
//        )
//
//        val turns = new Turns(new Turn(partyStatus))
//        val newBattle = new Battle(turns)
//
//        registerBattle(id, newBattle)
//        newBattle
//    }
//
//    override def registerBattle(playerTwitterId: String, battle: Battle): Unit = {
//        val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
//        registerBattle(id, battle)
//    }
//
//    private def makeKeyForBaattleId(identifier: BattlerIdentifier): String = BATTLE_ID_KEY_PREFIX + identifier
//
//    private def registerBattle(identifier: BattlerIdentifier, battle: Battle): Unit = ofv.set(makeKeyForBaattleId(identifier), battle)
//
//    override def getBattle(playerTwitterId: String): Battle = {
//        val id = battlerRepository.convertTwitterIdToId(playerTwitterId)
//        ofv.get(makeKeyForBaattleId(id)).asInstanceOf[Battle]
//    }

    /**
     * エイリアス
     */
    private Object ofv() {
        return redisTemplate.opsForValue();
    }
}
