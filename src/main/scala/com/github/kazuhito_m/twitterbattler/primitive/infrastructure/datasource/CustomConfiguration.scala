package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.datasource

import com.fasterxml.jackson.annotation.JsonTypeInfo.As
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping
import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
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
    * オブジェクト保存汎用。オブジェクト保存時に「JSONで保存」するよう、そこだけカスタム。
    */
  @Bean
  def redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate[String, Object] = {
    val template = new RedisTemplate[String, Object]()
    template.setConnectionFactory(connectionFactory)
    template.setKeySerializer(new StringRedisSerializer())
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer(customObjectMapper)) // ここだけカスタム
    template.setHashKeySerializer(template.getKeySerializer())
    template.setHashValueSerializer(template.getValueSerializer())
    template
  }

  private def customObjectMapper: ObjectMapper = {
    val objectMapper = new ObjectMapper
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
    objectMapper.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY) // GenericJackson2JsonRedisSerializer のコンストラクタ未指定時のデフォルト
    // JSR310 Date and Time API 変換対応
    objectMapper.registerModule(new JavaTimeModule) // LocalDate系
    objectMapper.registerModule(new DefaultScalaModule) // Scalaの型系
    objectMapper
  }

}
