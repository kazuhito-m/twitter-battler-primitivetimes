package com.github.kazuhito_m.twitterbattler.primitive.fw

import com.github.kazuhito_m.twitterbattler.primitive.Application
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
  * Test用の色々ヘルパー。
  *
  * 主だった目的は「Specs2のテストにDI出来ない」ため、それを補うための装備を持つ。
  */
object TestHelper {

  val applicationContext = new AnnotationConfigApplicationContext(classOf[Application])

  def beanBy[T](target: Class[T]): T = applicationContext.getBean(target)

}
