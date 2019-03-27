package Concurrency

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random
import scala.util.{Success,Failure}

object Futures extends App {

//  implicit val baseTime = System.currentTimeMillis()
  val f  = Future {
    Thread.sleep(Random.nextInt(500))
    if (Random.nextInt(500) > 250) throw new Exception("Yikes!")
    else 1+1
  }
  def longRunningComputation(int: Int):Future[Int] = Future {
    Thread.sleep(450)
    int+1
  }
  //onComplete
  f.onComplete{
    case Success(value) => println(s"We did it $value")
    case Failure(e) => e.printStackTrace
  }
  //we can write onSuccess or onFailure instead
  longRunningComputation(11).onSuccess{
    case result => println(s"After hard computations we did it = $result")
  }
  longRunningComputation(12).onFailure{
    case e => println(s"We couldn't do it. Exception : $e")
  }

//  val result = Await.result(f,1 second)
//  println(result)

  println("A ..."); Thread.sleep(100)
  println("B ..."); Thread.sleep(100)
  println("C ..."); Thread.sleep(100)
  println("D ..."); Thread.sleep(100)
  println("E ..."); Thread.sleep(100)
  println("F ..."); Thread.sleep(100)

  Thread.sleep(1000)
}
