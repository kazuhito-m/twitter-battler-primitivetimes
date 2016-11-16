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

  /**
    * 指定したクラスのDI済Beanを取得する。
    *
    * Scala+Specs2+Gradleなテストでは「アノテーションによるSpringDIが出来ない」ので、自力でやる。
    *
    * @param clazz 対象となるクラス。
    * @tparam T 対象と成るクラス型。
    * @return 取得できたBean。
    */
  def beanBy[T](clazz: Class[T]): T = applicationContext.getBean(clazz)

}
