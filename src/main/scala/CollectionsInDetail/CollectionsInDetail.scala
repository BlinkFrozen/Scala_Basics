package CollectionsInDetail

object CollectionsInDetail extends App {

  //List

  var l = List(1,2,4,5)
  l = l ++ List(6)
  println(l)

  //lazy List stream
  val st = (1 to 10000).toStream

  //strict method evaluates st
  println(st.max)

  val states = Map("AL" -> "Alabama", "AK" -> "Alaska","AR" -> "Arizona")
  states.foreach(x => println(s"${x._1} -> ${x._2}"))
  println(states.get("AB").getOrElse(None))

}
