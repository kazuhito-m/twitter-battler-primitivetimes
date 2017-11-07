package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.result

sealed abstract class VictoryOrDefeat

object VictoryOrDefeat {

  case object Win extends VictoryOrDefeat

  case object Loss extends VictoryOrDefeat

  case object None extends VictoryOrDefeat

}
