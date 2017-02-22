package com.zhouyongjin.kxscala.chaper10

/**
  * Created by zhouyongjin on 2017/2/22.
  */
import java.awt.geom.Ellipse2D
import java.io.{FileInputStream, InputStream}

object Tst{
  def main(args: Array[String]): Unit = {

  }
}

/**
  * 1、答案：
  */
trait RectangleLike{
  this:Ellipse2D.Double =>

  def translate(x:Double,y:Double): Unit ={
    this.x = x
    this.y = y
  }

  def grow(x:Double,y:Double): Unit ={
    this.x = x
    this.y = y
  }
}

object Tst_1 extends App{
  val egg = new Ellipse2D.Double(1,2,20,30) with RectangleLike
}

/**
  * 2、
  */
import java.awt.Point
class OrderedPoint extends Point with Ordered[Point]{
  def compare(that: Point): Int = {
    if (this.x <= that.x && this.y < that.y) -1
    else if(this.x == that.x && this.y == that.y) 0
    else 1
  }
}

/**
  * 3、这个。。。
  */

/**
  * 4、提供一个CryptoLogger类，将日志消息以凯撒密码加密。
  * 缺省情况下密匙为3，不过使用者也可以重写它。提供缺省密匙和-3作为密匙是的使用示例
  */

trait   Logger {
  def log(msg: String) = {}
}
trait ConsoleLogger extends Logger {
  override def log(msg: String) = Console.println(msg)
}
trait CaesarLogger extends Logger {
  val shift: Int = 3
  override def log(msg: String) = {
    super.log((for(x <- msg) yield (x + shift).toChar).mkString)
    // more elegant
    super.log(msg.map(_ + shift).map(_.toChar).mkString)
    // speedup but less elegant
    super.log(msg.map((x : Char) => (x + shift).toChar).mkString)
  }
}


/**
  * 5、
  */

import java.awt.Point
import java.beans.PropertyChangeSupport

trait PropertyChange extends PropertyChangeSupport{}

object Tst_5{
  val p = new Point() with PropertyChange
}

/**
  * 6、因为java是单继承
  */

/**
  * 7、
  */
trait Swim{
  val stype:Int = 1

  def swim(): Unit ={
    println("swim")
  }

  def breaststroke()
}

trait Run{
  var speed:Int

  def run(): Unit ={
    print("run")
  }
}

class Person{
  var name:String = _
}

class swimmer extends Person with Swim with Run{
  var speed:Int = 10

  override def breaststroke(): Unit = {
    print("breaststroke")
  }
}

/**
  * 8、在java.io类库中，你可以通过BufferedInputStream修饰器来给输入流增加缓冲机制。
  * 用特质来重新实现缓冲。简单起见，重写read方法
  */
trait MyBuffer_8{
  this:InputStream =>
  val SIZE:Int = 5
  private val buffer = new Array[Byte](SIZE)
  private var bufferSize:Int = 0
  private var pos:Int = 0

  override def read(): Int = {
    if (pos >= bufferSize){
      bufferSize = this.read(buffer,0,SIZE)
      if (bufferSize > 0) -1
      pos = 0
    }
    pos += 1
    buffer(pos - 1)
  }
}

object Tst_8 extends App{
  val f = new FileInputStream("test.txt") with MyBuffer_8
  for( i <- 1 to 10) println(f.read())
}
/**
  *
  */
trait Logger_9{
  def log(msg:String)
}

trait PrintLogger extends Logger_9{
  def log(msg:String) = println(msg)
}

trait MyBuffer_9{
  this:InputStream with Logger_9 =>
  val SIZE:Int = 5
  private val buffer = new Array[Byte](SIZE)
  private var bufferSize:Int = 0
  private var pos:Int = 0

  override def read(): Int = {
    if (pos >= bufferSize){
      bufferSize = this.read(buffer,0,SIZE)
      log("xxx")
      if (bufferSize > 0) -1
      pos = 0
    }
    pos += 1
    buffer(pos - 1)
  }
}

object Tst_9 extends App{
  val f = new FileInputStream("test.txt") with MyBuffer_9 with PrintLogger
  for(i <- 1 to 10) println(f.read())
}

/**
  *10、实现一个IterableInputStream类，扩展java.io.InputStream并混入Iterable[Byte]特质
  */
trait IterableInputStream extends java.io.InputStream with Iterable[Byte]{
  class InputStreamIterator(outer: IterableInputStream) extends Iterator[Byte]{
    def hasNext: Boolean = outer.available() > 0
    def next: Byte = outer.read().toByte
  }
  override def iterator: Iterator[Byte] = new InputStreamIterator(this)
}

object Tst_10 extends App{
  val f = new java.io.FileInputStream("test.txt") with IterableInputStream
  for(b <- f) println(b.toChar)
}

