package com.github.kazuhito_m.twitterbattler.primitive.ui

import com.github.kazuhito_m.twitterbattler.primitive.sample.SampleController
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SampleControllerSpec extends Specification {

  "Scalaでテスト書けるかのテスト" should {

    "RESTの返答が固定値に成る、たったそれだけな世界一簡単なテスト" in {
      val sut = new SampleController
      sut.data must equalTo("hoge")
    }

  }

}
