package com.github.kazuhito_m.twitterbattler.primitive.presentation

import java.nio.charset.StandardCharsets

import com.fasterxml.jackson.annotation.JsonTypeInfo.As
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping
import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.{HttpMessageConverter, StringHttpMessageConverter}
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
// @EnableWebMvc
class CustomWebMvcConfigAdapter extends WebMvcConfigurerAdapter {
  override def configureMessageConverters(converters: java.util.List[HttpMessageConverter[_]]): Unit = {
    converters.add(new MappingJackson2HttpMessageConverter(customObjectMapper))
    converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8))
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
