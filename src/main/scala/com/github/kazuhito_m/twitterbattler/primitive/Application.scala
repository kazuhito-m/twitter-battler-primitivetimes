package com.github.kazuhito_m.twitterbattler.primitive

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application {
}

object Application extends App {
  SpringApplication.run(classOf[Application], args: _*)
}
