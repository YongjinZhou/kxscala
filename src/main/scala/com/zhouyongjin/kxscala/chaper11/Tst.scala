package com.zhouyongjin.kxscala.chaper11

/**
  * Created by zhouyongjin on 2017/2/22.
  */
object Tst{
  /**
    * 1、答案：在REPL中执行即可得到结果。都是从左至右执行
    */

  /**
    * 2、答案
Scala中的操作符就是方法，比如a.*()，那么这些操作符（或者说方法）的优先级就是就是是根据"首"字母来判断的，优先级如下
最高优先级:除以下字符外的操作符字符
    * / %
+ -
:
= !
< >
&
ˆ
|
非操作符
最低优先级:赋值操作符，
一般乘方的操作符是优于乘法操作的，如果使用**作为乘方的话，那么其优先级则与*相同，
而如果使用'^'的话，则优先级低于*操作。优先级都是有问题的。故没有使用这两种操作符
    */

}

/**
  * 3、
  */

import scala.math.abs
class Fraction(n:Int,d:Int){

  private val num: Int = if (d == 0) 1 else n * sign(n) / gcd(n, d)
  private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d)

  override def toString: String = num + "/" + den

  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0

  def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)

  def +(other: Fraction):Fraction = {
    new Fraction((this.num * other.den) + (this.den * other.den),this.den * this.den)
  }

  def -(other:Fraction):Fraction = {
    new Fraction((this.num * other.den) - (this.den * other.den),this.den * this.den)
  }

  def *(other:Fraction):Fraction = {
    new Fraction(this.num * other.num, this.den * other.den)
  }

  def /(other:Fraction):Fraction = {
    new Fraction(this.num * other.den, this.den * other.num)
  }
}

object Fraction extends App{
  val f = new Fraction(15,-6)
  val p = new Fraction(20,60)
  println(f)
  println(p)
  println(f + p)
  println(f - p)
  println(f * p)
  println(f / p)
}

/**
  * 4
  */
class Money(val dollar:BigInt, val cent:BigInt){

  def +(other:Money): Money = {
    val (a,b) = (this.toCent + other.toCent) /% 100
    new Money(a,b)
  }

  def -(other:Money): Money = {
    if (this.toCent > other.toCent) {
      val (a, b) = (this.toCent - other.toCent) /% 100
      new Money(a, b)
    } else {
      println()
      null
    }
  }

  def ==(other:Money): Boolean = { this.toCent == other.toCent }

  def <(other:Money): Boolean = { this.toCent < this.toCent }

  private def toCent = { this.dollar * 100 + this.cent}

}

object Money extends App{

  def apply(dollar: BigInt, cent: BigInt): Money = new Money(dollar, cent)
}

/**
  * 5、
  */
class Table{

  var s:String = ""

  def |(str:String):Table={
    val t = Table()
    t.s = this.s + "<td>" + str + "</td>"
    t
  }

  def ||(str:String):Table={
    val t = Table()
    t.s = this.s + "</tr><tr><td>" + str + "</td>"
    t
  }

  override def toString():String={
    "<table><tr>" + this.s + "</tr></table>"
  }
}
object Table{

  def apply(): Table = new Table()
}

/**
  * 6、
  */
import collection.mutable.ArrayBuffer

class ASCIIArt(str:String) {
  val arr: ArrayBuffer[ArrayBuffer[String]] = new ArrayBuffer[ArrayBuffer[String]]()

  if (str != null && !str.trim.eq("")) {
    str.split("[\r\n]+").foreach {
      line =>
        val s = new ArrayBuffer[String]()
        s += line
        arr += s
    }
  }

  def this() {
    this("")
  }

  def +(other:ASCIIArt):ASCIIArt={
    val art = new ASCIIArt()
    val length = if (this.arr.length >= other.arr.length) this.arr.length else other.arr.length
    for(i <- 0 until length){
      val s = new ArrayBuffer[String]()
      val thisArr:ArrayBuffer[String] = if (i < this.arr.length) this.arr(i) else new ArrayBuffer[String]()
      val otherArr:ArrayBuffer[String] = if (i < other.arr.length) other.arr(i) else new ArrayBuffer[String]()
      thisArr.foreach(s += _)
      otherArr.foreach(s += _)
      art.arr += s
    }
    art
  }

  def *(other:ASCIIArt):ASCIIArt={
    val art = new ASCIIArt()
    this.arr.foreach(art.arr += _)
    other.arr.foreach(art.arr += _)
    art
  }

  override def toString()={
    var ss:String = ""
    arr.foreach{
      ss += _.mkString(" ") + "\n"
    }
    ss
  }
}

object ASCIIArt extends App{
  val a = new ASCIIArt(""" /\_/\
                         |( ' ' )
                         |(  -  )
                         | | | |
                         |(__|__)
                         |""".stripMargin)
  val b = new ASCIIArt( """    -----
                          |  / Hello \
                          | <  Scala |
                          |  \ Coder /
                          |    -----
                          |""".stripMargin)
  println(a + b * b)
  println((a + b) * b)
  println(a * b)
}

/**
  * 7、
  * @param value
  */
class BitSequence(private var value:Long = 0){

  def apply(bit: Int): Int = if ((value & 1L << bit % 64) > 0) 1 else 0

  def update(bit:Int, state:Int) = value != (state & 1L << bit % 64)

  override def toString = "%64s".format(value.toBinaryString).replace(" ", "0")
}

/**
  * 8、
  * @param m
  * @param n
  */
class Matrix(val m: Int, val n: Int){
  private val value = Array.ofDim[Double](m, n)
  def update(x: Int, y: Int, v: Double) = value(x)(y) = v
  def apply(x: Int, y: Int) = value(x)(y)
  def +(other: Matrix) = {
    require (n == other.n)
    require (m == other.m)
    var res = new Matrix(m,n)
    for(i <- 0 until m; j <- 0 until n){
      res(i, j) = this.value(i)(j) + other.value(i)(j)
    }
    res
  }
  def *(factor: Double) = {
    var res = new Matrix(m, n)
    for(i <- 0 until m; j <- 0 until n) {
      res(i, j) = this.value(i)(j) * factor
    }
    res
  }
  private def prod(other: Matrix, i: Int, j: Int) = {
    (for (k <- 0 until n) yield value(i)(k) * other.value(j)(k)).sum
  }
  def *(other: Matrix) = {
    require(n == other.m)
    var res = new Matrix(m, n)
    for(i <- 0 until m; j <- 0 until n) {
      res(i, j) = prod(other, i, j)
    }
    res
  }
  override def toString = value.map(_.mkString(" ")).mkString("\n")
}

/**
  * 9、
  */
class RichFile(val path:String){

}

object RichFile{

  def apply(path:String): RichFile = new RichFile(path)

  def unapply(arg: RichFile) = {
    if (arg.path == null){
      None
    } else {
      val reg = "([/\\w+]+)/(\\w+)\\.(\\w+)".r
      val reg(r1,r2,r3) = arg.path
      Some((r1,r2,r3))
    }
  }
}

/**
  * 10、
  * @param path
  */
class RichFile_10(val path:String){

}

object RichFile_10{

  def apply(path: String): RichFile_10 = new RichFile_10(path)

  def unapply(arg: RichFile_10): Option[Seq[String]] = {
    if (arg.path == null){
      None
    } else {
      Some(arg.path.split("/"))
    }
  }
}

