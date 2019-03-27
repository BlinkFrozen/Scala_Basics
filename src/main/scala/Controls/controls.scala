package Controls

import scala.annotation.{switch, tailrec}

object controls extends App {
  for(c <- 1 to 3 ; d <- 1 to 2){
    print(c*d + " ")
  }
  println(" ")
  for{
    c <- 1 to 3
    d <- 2 to 3
  } println(s"c = $c and d = $d")

  val a = List("apple","banana","orange")

  for (e <- a) println(e.capitalize)
  a.map(_.toUpperCase).foreach(println(_))

  val names = Map("fname" -> "Robert", "lname" -> "Goren")
  names.foreach( x => println(s"key: ${x._1}, value: ${x._2}"))

  def factorial(n:Int): BigInt = {
    @tailrec
    def factorialtail(acc: BigInt ,n: Int):BigInt = {
      if(n <= 1) acc
      else  factorialtail(acc * n,n - 1)
    }
    factorialtail(1,n)
  }

  val t0 = System.nanoTime()
  println(factorial(5001))
  val t1 = System.nanoTime()
  println(t1-t0)

  val i = 70
//  class Person(val name:String,val age:Int)
//  object Person{
//    def unapply(arg: Person): Option[(String, Int)] = Some(arg.name,arg.age)
//    def apply(name:String,age:Int): Person = new Person(name,age)
//  }
//  it's the same
  case class Person(name:String,age:Int)


  def matternMatch(x:Any): String = (x: @switch)/* @switch works if simple ->*/ match {
    case m if m == 1  => "Monday" // i could go all the way like this but @switch doesn't work
    case 2 => "Tuesday"
    case 3 => "Wednesday"
    case 4 => "Thursday"
    case 5 => "Friday"
    case 6 => "Saturday"
    case 7 => "Sunday"
    case f:Float => s"haha $f"
    case list:List[_] => s"Nice list : $list"
    case veco @ Vector(2,_*) => s"You got yourself a vector $veco" /*but here we need "@" */
    case veco:Vector[_] => s"I see your gor yourself a nice vector : $veco "/* ":" works here*/
    case p @ Person(_,_) => s"You ${p.name} is a nice ${p.age} fella "
    case (r,b,c) => s"Nice 3-element Tuple : ($r,$b,$c)"
    case default /*or "_"*/ => "you can't use _ but you can use default" + default
  }
  println(matternMatch(5))



}
