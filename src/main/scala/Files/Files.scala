package Files

import scala.io.{BufferedSource, Source}

object Files extends App {

  //opening file

  //shitty way of doing this
  //we don't close the file
  val filename = "C:\\Users\\BlinkFrozen\\IdeaProjects\\Cwiczenia\\src\\main\\scala\\CubeCalculator.scala"

  for(line <- Source.fromFile(filename).getLines){
    println(line)
  }

  val fileText = Source.fromFile(filename).getLines.mkString

  println(fileText)

  //This is how you do it babe

  val file: BufferedSource = Source.fromFile(filename)
  for (line <- file.getLines){
    println(line.toUpperCase())
  }

  //loan pattern
  def forPrint(buffer: BufferedSource)= {
    for (line <- buffer.getLines) {
      println(line.toLowerCase)
    }
  }
  def using[A <: {def close():Unit},B](resource: A)(f: A => B):B={
    try {
      f(resource)
    } finally{
      resource.close
    }
  }
  using(Source.fromFile(filename))(forPrint)
//  using(filename)(forPrint)

  import java.io.File
  //writing to file
  val text = "printf(\"HAHHA\")"

  //PrintWriter
//  val pw = new PrintWriter(new File(filename))
//  pw.write(text)
//  pw.close

  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }
  val files = getListOfFiles("/")
  println(files)


}

