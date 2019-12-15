package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle;

import java.util.stream.Stream;

/**
 * 戦闘画面のシーン。
 */
public enum BattleScene {
    /**
     * パーティ作成画面(自動生成直後、アニメーションあり画面)
     */
    PartyMake("partymake:01"),

    /**
     * パーティ作成・変更画面(アイテムを使ったりとかいろいろ)
     */
    PartyMakeMore("partymake:02"),

    /**
     * バトル画面(入力を許容する状態)
     */
    BattleOperation("battle:01"),

    /**
     * バトル画面(アニメーションなどするターン中)
     */
    BattleInTurn("battle:02"),

    /**
     * バトル結果(結果や経験値やボーナスなどを表示する画面)
     */
    BattleResult("battleresult:01");

    private final String id;

    BattleScene(String id) {
        this.id = id;
    }

    public BattleScene of(String id) {
        return Stream.of(values())
                .filter(e -> e.id.equals(id))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public String id() {
        return id;
    }
}
