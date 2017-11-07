package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle

import com.github.kazuhito_m.twitterbattler.primitive.domain.model.battle.command.Commands

/**
  * とりあえず「トランザクションスクリプトをここへ書く」ために「進行役」というクラスを設ける。
  * TODO 最終目的は「こいつがいなくなる」or「限りなく薄くなる」こと。
  */
class BattleFacilitator {

  def proceedNextTurn(commands: Commands, battle: Battle): Battle = battle // TODO 今は仮実装

}
