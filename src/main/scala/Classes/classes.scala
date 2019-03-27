package Classes

object classes extends App {

  class Person(val name:String,val surname:String) {

    //    We don't use auxiliaty ocnstructors

    println("Here contructor begins")

    private val HOME = System.getProperty("user.home")
    val age = 0

    override def toString: String = s"$name $surname is $age years old"

    def printHome {
      println(s"$HOME")
    }

    def printFullNmae {
      println(this)
    }

    printHome
    printFullNmae
    println("Everything is a constructor/lie")
  }

//  but apply methods in companion object
//  and we don't have to write new every time
  object Person{
    def apply(name:String,surname:String) = new Person(name,surname)
    def apply() = new Person("Dawid","Laczek")

  }
  val p = Person("dawid","Laczek")

  case class Address(city: String, state: String, zip: String)

  class People(val gender:String,private var _amount:Int){

//    private[This] val m = 1 // only each individual object to access
    private val p = 2 // access have each object of class People
    def amount = _amount
    def amount_=(aAmount:Int){_amount = aAmount}

    var l = None:Option[Address]
  }

  class Marriage(name: String, surname: String,val secondHalf: String)
    extends Person(name,surname)

  var p2 = new People("Woman",2)
  p2.amount = 3
  println(p2.amount)
  p2.l = Some(Address("Włoszczowa", "Swiętokrzyskie", "29-100"))
  p2.l.foreach{a =>
    println(a.city)
    println(a.state)
    println(a.zip)

  }


}
