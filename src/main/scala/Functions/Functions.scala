package Functions


package otherScope {

  class others {
    def sayhello(s: String => Unit, string: String) = s(string)
  }
}

object Functions extends App {

  // Expression Oriented Programming
  //everything returns a value

  val _if = if (Functions.executionStart > 10) "It's more than 10 seconds after 1970" else 12

  println(_if)

  val x = Range(1,10)

  val evens = x.filter((i:Int) => i % 2 == 0)
  //thanks to scala shortcuts we can write is simpler
  //          x.filter(_ % 2 == 0)
  //and we can specify what type of variable it returns by writing it like this
  val even:Int => Boolean = i => {i % 2 == 0}

  println(evens)

  //partialy aplied functions

  val zdis = scala.math.cos(_)

  println(zdis(scala.math.Pi))

  def executeCode(code: (Int,Int) => Int,x:Int,y:Int)=  code(x,y)

  def sum(int: Int,int2: Int) = int + int2
  def multi(int: Int,int1: Int) = int * int1

  println(executeCode(sum,4,4))
  println(executeCode(multi,4,4))

  //closure functions
  var hello = "Aloha"

  def Hello(s: String) = println(s"$hello $s")

  val Henrike = "Henrike"

  val fun = new otherScope.others

  fun.sayhello(Hello, Henrike)

  hello = "Guten Tag"

  fun.sayhello(Hello, Henrike)

  //function returning another function

  def saySomething(prefix:String) = (s:String) => {
    prefix + " " + s
  }

  val something = saySomething("Returned a function (1)")
  println(something("Used returned function (2)"))

  def greeting(language:String):String => String = (name:String) => {
    language match {
      case "english" => "Hello " + name
      case "polish" => "Dzień dobry " + name
    }
  }

  val polish = greeting("polish")

  println(polish("Dawid"))


  //Partial functions

  val divide = new PartialFunction[Int,Int] {
    override def apply(v1: Int): Int = 42 / v1

    override def isDefinedAt(x: Int): Boolean = x != 0
  }

  val divide2: PartialFunction[Int,Int] = {
    case d:Int if d != 0 => 42 / d
  }

  val divideByZero = new PartialFunction[Int,String] {
    override def apply(v1: Int): String = "Wo don't devide by zero"

    override def isDefinedAt(x: Int): Boolean = x == 0
  }

  println(divide.isDefinedAt(1))
  if(divideByZero.isDefinedAt(90)) println(divideByZero(90))

  val divideOrElse = divide orElse divideByZero

  println(divideOrElse(0))
}
