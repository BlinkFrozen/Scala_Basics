package Concurrency

import akka.actor._

import scala.annotation.tailrec
object Actors extends App {

  //akka actors
  class HelloActor(myName:String) extends Actor {
    override def receive: Receive = {
      case "Hello" => println(s"Hello $myName")
      case _ => println(s"Huh? , $myName")
    }
  }
  //actor need ActorSystem
  val system = ActorSystem("HelloSystem")

  val helloActor = system.actorOf(Props(new HelloActor("David")),name = "HelloActor")

  helloActor ! "Hello"
  helloActor ! "Buenos Dias"

  system.terminate


  //comunication between actors
  case object PingMessage
  case object PongMessage
  case object StartMessage
  case object StopMessage

  class Ping(pong:ActorRef) extends Actor{
    var count = 0
    def incrementAndPrint{count += 1; println("Ping")}

    override def receive: Receive = {
      case StartMessage =>
        incrementAndPrint
        pong ! PingMessage
      case PongMessage =>
        incrementAndPrint
        if( count > 9) {
          sender ! StopMessage
          println("ping Stopped")
          context.stop(self)
        }else{
          sender ! PingMessage
        }
      case _ => println("Ping got something unexpected")
    }
  }
  class Pong extends Actor {
    override def receive: Receive = {
      case PingMessage =>
        println("Pong")
        sender ! PongMessage
      case StopMessage =>
        println("Pong stopped")
        context.stop(self)
      case _ => println("Pong got something unexpected")
    }
  }
  val system1 = ActorSystem("PingPongSystem")
  val pong = system1.actorOf(Props(new Pong), name = "pong")
  val ping = system1.actorOf(Props(new Ping(pong)),name = "ping")

  ping ! StartMessage

  Thread.sleep(500)
  system1.terminate

  //child actor inside actors
  case class CreateChild(val string: String)

  class Child extends Actor {

    var name = "noe name"
    override def receive: Receive = {
      case n:String =>
        println(s"Naming child $n")
        name = n
      case _ => println("What do you want?")
    }
  }
  class Parent extends Actor {

    override def receive: Receive = {
      case CreateChild(name) =>
        val child = context.actorOf(Props[Child],name = s"$name")
        context.watch(child)
        child ! name
      case Terminated(child) => println(s"They killed a child: ${child.path.name}")
      case _ => println("Parent got some other message.")
    }

  }

  val parentSystem = ActorSystem("ParentSystem")
  val parent = parentSystem.actorOf(Props[Parent],name = "Parent")

  parent ! CreateChild("Zdzis")
  parent ! CreateChild("Kasia")
  Thread.sleep(500)

  println("Looking for Zdzis")
  val zdis = parentSystem.actorSelection("/user/Parent/Zdzis")
  zdis ! "Pafcio"
  zdis ! PoisonPill


  Thread.sleep(500)
  parentSystem.terminate

  //stopping actors
  //1
  //  system.stop(actorRed)
  //2
  //  PoisonPill
  //3
  //  Programming gracefulStop

  //states with become

  case object ActNormal
  case object TryToFindSolution
  case object BadGuysMakeMeAngry

  class DawidBanner extends Actor{
  import context._

    def angryState:Receive = {
      case ActNormal =>
        println("I'm back to Dawid")
        become(normalState)
//      case TryToFindSolution =>
//        println("Hulk SMASH!!!")
    }

    def normalState:Receive = {
      case TryToFindSolution  =>
        println("Looking for solution")
      case BadGuysMakeMeAngry => {
        println("You don't want to see me angry")
        become(angryState)
      }
    }

    override def receive: Receive = {
      case BadGuysMakeMeAngry => become(angryState)
      case ActNormal => become(normalState)
    }
  }
  val hulkSystem = ActorSystem("DoctorHulk")
  val dawidBanner = hulkSystem.actorOf(Props[DawidBanner],name = "DawidBanner")
    dawidBanner ! ActNormal
    dawidBanner ! TryToFindSolution
    dawidBanner ! BadGuysMakeMeAngry
    Thread.sleep(1000)
    dawidBanner ! TryToFindSolution
    dawidBanner ! ActNormal
    Thread.sleep(1000)
    hulkSystem.terminate



}
