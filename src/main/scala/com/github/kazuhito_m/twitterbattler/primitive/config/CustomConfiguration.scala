package com.github.kazuhito_m.twitterbattler.primitive.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.{JdkSerializationRedisSerializer, StringRedisSerializer}

/**
  * 設定ファイルに記述しきれない設定・Bean定義などを定義する。
  */
@Configuration
class CustomConfiguration {

  /**
    * RedisTemplate定義。
    * オブジェクト保存汎用。
    */
  @Bean
  def redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate[String, Object] = {
    val template = new RedisTemplate[String, Object]()
    template.setConnectionFactory(connectionFactory)
    template.setKeySerializer(new StringRedisSerializer())
    // TODO GenericJackson2JsonRedisSerializerで「JSONで保存・復元」を試みたが、CaseClassにはデフォルトコンストラクタが無いから無理だった。
    // TODO 出来るなら「ScalaのCaseClassをJsonで復元出来る」RedisSerializerを自作する。
    template.setValueSerializer(new JdkSerializationRedisSerializer())
    template.setHashKeySerializer(template.getKeySerializer())
    template.setHashValueSerializer(template.getValueSerializer())
    template
  }

}
