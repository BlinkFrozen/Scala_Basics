package Collections

object Collections extends App {

  //IndexedSeq near team element access and length computation
  val vec = Vector(1,2,3,4,"5",6)
  println(vec.apply(4))

  //fast "head", "tail", "isEmpty" methods
  val list = List(1,2,3,4,"5",6)
  println(list.tail)
  println(list ++ List(28))

  val r = Range(1,40)
  println(r.tail)

  val days = Array[String]("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

  days.zipWithIndex.foreach{
    case(day:String,count:Int) => println(s"$count is $day")
  }

  val h = Vector("adam","kim","malissa")
  val caps = h.filter(_.length < 6).map(_.capitalize)
  println(caps)

  val thick = List(List("Tadzio","zdzisiu"),List("marcela","Jamie"))
  println(thick.flatten)


  val numbers = List("12","65","Aloha","90")

  def toInt(string: String): Option[Int] ={
    try {
      Some(Integer.parseInt(string.trim))
    } catch{
      case e: Exception => None
    }
  }
  println(numbers.flatMap(toInt))

  //splitting sequences in subsets

  val whole = List(12,54,2,6,324)

  val groups = whole.groupBy(_ > 10)
  println(groups(true))
  println(whole.partition(_ > 10))
  println(whole.splitAt(2))

  //how does reduceLeft work

  val findMax = (x:Int,y:Int) =>{
    val winner = x max y
    println(s"compared $x with $y, $winner was larger")
    winner
  }
  whole.reduceLeft(findMax)

  object Margin extends Enumeration{
    type Margin = Value
    val TOP,BOTTOM,LEFT,RIGHT = Value
  }

  val currentMargin = Margin.TOP
  if(currentMargin == Margin.TOP) println(s"This is Enum ${Margin.TOP}")

  Margin.values.foreach(println)
}
