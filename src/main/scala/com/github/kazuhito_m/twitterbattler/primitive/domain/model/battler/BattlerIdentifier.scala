package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler

case class BattlerIdentifier(value: Long) {

  override def toString(): String = value.toString;

  override def hashCode(): Int = value.toInt;

}
