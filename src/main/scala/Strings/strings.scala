package Strings

object strings extends App {

  val s:String = "Hello"
  for (c <- s) println(c)

  println(s.drop(3).+("l").toUpperCase)
//  println("Hello".compare(null)) exception but
  println("Hello" == null) // no exception

  val sm =
    """This is
        |multiline
        |string
    """.stripMargin

  println(sm)
  val inigo = "Inigo"
  val montoya = "Montoya"
  println(s"My name is $inigo" + raw" $montoya")
  println("only_small_letters".map(_.toUpper))
  "Text".map(println(_))

  def toLower(c:Char)= (c.toByte + 32).toChar
  println("HELLO".map(toLower))



  val numPat = "[0-9]+".r
  val text:String = s"I have around 592 sins on my hell vip card. Only ${1000 - 592} to go"
  numPat.findAllIn(text).foreach(println(_))
  val match1:String = numPat.findFirstIn(text).getOrElse("No Match")
  println(match1)
  //pack into and object and then import
  implicit class StringImprovement(s:String){
    def increment = s.map(c => (c+1).toChar)
  }
  println("HAL".increment)
  val l = "90".toInt
  println(l)
}
