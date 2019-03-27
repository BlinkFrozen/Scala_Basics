package Traits

object Traits extends App {

  //trait as interface
  trait BaseSoundPlayer extends {

    //if they don't take parameters you only have to write a name
    def play
    def close
    def pause
    def resume
    //if they take parameters you
    def stop(HowLongTillStopping: Int)

  }

  class MP3 extends BaseSoundPlayer {
    override def play: Unit = println("Playing")

    override def close: Unit = println("closing")

    override def pause: Unit = println("Pausing")

    override def resume: Unit = println("Resuming")

    override def stop(HowLongTillStopping: Int): Unit = println(s"Stopping in $HowLongTillStopping")
  }


  //limiting use of a trait by only a subclasses of certain class and certain method

  class Starship

  trait WarpCore{
    this: Starship {

      def ejectWarpCore(password: String): Boolean
      def isThereWarpCore:Boolean
    } =>

    //code here
  }

  class Enterprise extends Starship  {
    def ejectWarpCore(password: String): Boolean = {
    if(password == "password" && warpcore == true) {
      println("Ejecting core")
      warpcore = false
      true
    } else
      false
    }

    def isThereWarpCore : Boolean ={
      if(warpcore == true)
        true
      else false
    }

    var warpcore:Boolean = true

  }

  trait CaptainsLog{
    println(
      s"""behlulu
         |Captain's Log start date: ${Traits.executionStart}""".stripMargin)
  }

  val StarTrek = new Enterprise with CaptainsLog with WarpCore


}
