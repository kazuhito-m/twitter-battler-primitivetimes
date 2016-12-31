package com.github.kazuhito_m.twitterbattler.primitive.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.{GenericJackson2JsonRedisSerializer, StringRedisSerializer}

/**
  * 設定ファイルに記述しきれない設定・Bean定義などを定義する。
  */
@Configuration
class CustomConfiguration {

  /**
    * RedisTemplate定義。
    * オブジェクト保存汎用。オブジェクト保存時に「JSONで保存」するよう、そｋだけカスタム。
    */
  @Bean
  def redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate[String, Object] = {
    val template = new RedisTemplate[String, Object]()
    template.setConnectionFactory(connectionFactory)
    template.setKeySerializer(new StringRedisSerializer())
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer()) // ここだけカスタム
    template.setHashKeySerializer(template.getKeySerializer())
    template.setHashValueSerializer(template.getValueSerializer())
    template
  }

}
