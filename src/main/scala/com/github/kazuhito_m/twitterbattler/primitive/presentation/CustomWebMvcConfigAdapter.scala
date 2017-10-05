package com.github.kazuhito_m.twitterbattler.primitive.presentation

import java.nio.charset.StandardCharsets

import com.github.kazuhito_m.twitterbattler.primitive.CustomObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.{HttpMessageConverter, StringHttpMessageConverter}
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
// @EnableWebMvc
class CustomWebMvcConfigAdapter extends WebMvcConfigurerAdapter {
  override def configureMessageConverters(converters: java.util.List[HttpMessageConverter[_]]): Unit = {
    converters.add(new MappingJackson2HttpMessageConverter(CustomObjectMapper))
    converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8))
  }
}
