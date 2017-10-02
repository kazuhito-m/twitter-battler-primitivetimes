package com.github.kazuhito_m.twitterbattler.primitive.domain.model.game

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class GameInformationRepository {

  protected val log: Logger = LoggerFactory.getLogger(classOf[GameInformationRepository])

  @Autowired
  private val redisTemplate: RedisTemplate[String, Object] = null

  /** エイリアス */
  private def ofv = redisTemplate.opsForValue

}
