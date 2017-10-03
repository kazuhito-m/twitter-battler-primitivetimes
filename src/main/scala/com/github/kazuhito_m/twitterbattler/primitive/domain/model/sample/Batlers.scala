package com.github.kazuhito_m.twitterbattler.primitive.domain.model.sample

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler.Battler

/**
  * Json シリアライズ/デシリアイズのテスト実装
  */
//class Batlers(
//               private val oneBattler: Battler,
//               private val twoBattler: Battler
//             ) extends Serializable {
class Batlers(var list: List[Battler]) extends Serializable {
  //  @BeanProperty
  //  protected var one: Battler = oneBattler
  //  @BeanProperty
  //  protected var two: Battler = twoBattler

  //  @BeanProperty
  //  var list: List[Battler] = scala.List[Battler](oneBattler, twoBattler)

  // for Json serialize

  //  private def setList(newList: java.util.List[Battler]): Unit = list = newList.asScala.toList
  //  private def getList(): java.util.List[Battler] = list.asJava

}

// build.gradleの
//     compile 'com.fasterxml.jackson.module:jackson-module-scala_2.11:2.8.1'
// の指定と、
// CustomConfiguration の
//      objectMapper.registerModule(new DefaultScalaModule) // Scalaの型系
// の指定により、
// - getter/setter無しのフィールド(varは必須、普通なら@BeanPropertyが要る)
// - コンストラクタフィールド(varは必須)
// - scala.ListなどのScalaのコレクション
