package com.github.kazuhito_m.twitterbattler.primitive.sample

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.util.Date

import com.github.kazuhito_m.twitterbattler.primitive.domain.entity.Battler
import com.sun.xml.internal.messaging.saaj.util.{ByteInputStream, ByteOutputStream}
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScalaSerializeSpec  extends Specification {

  "Scalaのシリアライズのテスト" should {

    "普通にやるとどうなるのか" in {
      val param = createSampleBattler("t","n")

      val bos = new ByteOutputStream()
      val oos = new ObjectOutputStream(bos)
      oos.writeObject(param)

      val bytes  = bos.getBytes

      println("値:"  + new String(bytes))

      val bis = new ByteInputStream(bytes  , bytes.length)
      val ois = new ObjectInputStream(bis)

      val dec = ois.readObject().asInstanceOf[Battler]

      println("バトラーの名前 : " + dec.screenName)
      dec.id must equalTo("t")

    }

  }

  // ユティリティ

  def createSampleBattler(id: String, name: String) = Battler(id, name, "紹介", 1, 2, 3, 4, 5, 6, "http://example.com", new Date(), new Date())

}
