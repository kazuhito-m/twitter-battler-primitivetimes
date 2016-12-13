package com.github.kazuhito_m.twitterbattler.primitive.social

import org.springframework.social.config.annotation.SocialConfigurerAdapter
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository
import org.springframework.stereotype.Component

@Component
class SocialConfigurer extends SocialConfigurerAdapter {
  override def getUsersConnectionRepository(connectionFactoryLocator: ConnectionFactoryLocator) = new InMemoryUsersConnectionRepository(connectionFactoryLocator)
}
