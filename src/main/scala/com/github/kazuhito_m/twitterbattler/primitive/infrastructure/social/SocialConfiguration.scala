package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.social

import com.github.kazuhito_m.twitterbattler.primitive.infrastructure.security.AuthUtil
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.social.connect.Connection
import org.springframework.social.connect.web.SignInAdapter
import org.springframework.web.context.request.NativeWebRequest

@Configuration
class SocialConfiguration {

  // https://github.com/spring-projects/spring-social/blob/master/spring-social-config/src/main/java/org/springframework/social/config/annotation/SocialConfiguration.java#L87
  @Bean
  def socialConfigurerAdapter: SocialConfigurer = new SocialConfigurer

  @Bean
  def authSignInAdapter: SignInAdapter = new SignInAdapter() {

    def signIn(userId: String, connection: Connection[_], request: NativeWebRequest): String = {
      AuthUtil.authenticate(connection)
      null
    }

  }
}
