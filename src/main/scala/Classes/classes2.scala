package Classes

object classes2 extends App {

  abstract class Animal(val name: String) {

    var greeting: String
    var age: Int

    def sayHello {
      println(greeting)
    }

    override def toString: String = s"I say $greeting, and I'm $age"

    def setgreeting(string: String): this.type ={
      greeting = string
      this
    }

    def setage(int: Int): this.type ={
      age = int
      this
    }
  }

  class Dog(name: String) extends Animal(name) {
    var greeting: String = "Woof"
    var age: Int = 2
  }

  class Cat(name: String) extends Animal(name) {

    override var greeting: String = "Meow" //override not necessary
    override var age = 5

    def printAll(strings: String*): Unit ={
      strings.foreach(println)
    }
  }

  class ChooseYourAnimal(name: String) extends Animal(name){

    override var greeting: String = _
    override var age: Int = _

    override def toString: String = s"I'm a $name I'm $age and my greeting is $greeting"
  }

  val c = new Cat("Zdzisław")
  println(c)
  val list:List[String] = List("Bobo", "bobuś", "zdzisaław")
  c.printAll(list: _*) //this 'll work but without : _* not


  val donkey = new ChooseYourAnimal("zdisław")

  donkey.setage(12).setgreeting("ihaaa")
  println(donkey)

  import java.util.{ArrayList => JavaList}

  val lista = new JavaList[String]
//  val list = new ArrayList[String] won't work we

 //to hide one something while importing use this
  import scala.util.{Random => _,_}//we are hiding Random and with "_" importing everything else from scala.util



  //but wait there's more
}
