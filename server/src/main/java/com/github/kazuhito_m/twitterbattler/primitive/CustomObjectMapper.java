package com.github.kazuhito_m.twitterbattler.primitive;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * このアプリケーションでJSONを扱う場合の統一変換則。
 * WebAPIの送受信、Redisの保存と両方に使っているが、異なる場合はわける事。
 */
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        enable(SerializationFeature.INDENT_OUTPUT);
        enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY); // GenericJackson2JsonRedisSerializer のコンストラクタ未指定時のデフォルト
        // JSR310 Date and Time API 変換対応 (LocalDate系)
        registerModule(new JavaTimeModule());
    }
}

