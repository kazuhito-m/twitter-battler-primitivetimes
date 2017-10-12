package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler

import scala.math._

class RandomChoiceIdList(values: List[Long]) {
  def choice(size: Int): List[Long] = {
    var indexSet = Set[Int]()
    while (indexSet.size < size)
      indexSet += floor(random * values.size).toInt
    indexSet.map { index => values(index) }.toList
  }
}
