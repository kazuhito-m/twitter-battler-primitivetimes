package com.github.kazuhito_m.twitterbattler.primitive.security

case class User() {

  private var name: String = null

  def this(value: String) {
    this()
    this.name = value
  }

  def getName: String = name

  def setName(value: String) {
    this.name = value
  }
}
