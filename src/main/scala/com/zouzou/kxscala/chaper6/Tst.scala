package com.zouzou.kxscala.chaper6

/**
  * Created by zhouyongjin on 2017/2/22.
  */
import java.awt.Point

object Conversions{
  def inchesToCentimeters: Unit ={

  }

  def gallonsToLiters: Unit ={

  }

  def milesToKilometers: Unit ={

  }
}

/**
  * 2、前一个不是很面向对象，提供一个通用的超类UnitConversion
  * 并定义超类的InchesToCentimeters,GallonsToLiters和MilesToKilometers对象
  */
abstract class UtilConversions{
  def conver(para:BigInt): BigInt;
}

object InchesToCentimeters extends UtilConversions{
  override def conver(para: BigInt): BigInt = {
    0
  }
}

object GallonsToLiters extends UtilConversions{
  override def conver(para: BigInt): BigInt = {
    0
  }
}

object MilesToKilometers extends UtilConversions{
  override def conver(para: BigInt): BigInt = {
    0
  }
}

/**
  * 3、定义一个扩展自java.awt.Point的Origin对象。为什么说这实际上不是个好主意？(仔细看Point类的方法)
  */
object MyPoint extends java.awt.Point{
  override def getLocation: Point = super.getLocation
}

/**
  * 4、定义一个Point类和一个伴生类对象，使得我们不用new直接用Point(3,4)来构建实例。
  */
class Point4(){
  def this(x:Int,y:Int){
    this()
  }
}

object Point4{
  def apply(x: Int, y: Int): Point4 = new Point4(x, y)
}

/**
  * 5、编写一个Scala应用程序,使用App特质,以反序打印命令行参数,用空格隔开。
  * 举例来说,scala Reverse Hello World应该打印World Hello
  */
object Exercise_5 extends App {
  args.reverse.foreach(args => print(args + " "))
}

/**
  * 6、编写一个扑克牌4种花色的枚举,让其toString方法分别返回♣,♦,♥,♠
  */
object Card extends Enumeration with App{
  val M = Value("♣")
  val T = Value("♠")
  val H = Value("♥")
  val F = Value("♦")

  override def toString(): String = {
    "花桃：" + Card.M +"，黑桃:" + Card.T + "，红桃:" + Card.H + "，方片:" + Card.F
  }

  print(Card)
}

/**
  * 7、实现一个函数,检查某张牌的花色是否为红色
  */
object Card_7 extends Enumeration with App{
  val M = Value("♣")
  val T = Value("♠")
  val H = Value("♥")
  val F = Value("♦")

  override def toString(): String = {
    "花桃：" + Card_7.M +"，黑桃:" + Card_7.T + "，红桃:" + Card_7.H + "，方片:" + Card_7.F
  }

  def color(card:Card_7.Value): Unit ={
    if (card == Card_7.H || card == F) print("red") else print("black")
  }

  print(Card)
}

/**
  * 8、编写一个枚举,描述RGB立方体的8个角。ID使用颜色值(例如:红色是0xff0000)
  */
object RGB extends Enumeration with App{
  val RED = Value(0xff0000,"Red")
  val BLACK = Value(0x000000,"Black")
  val GREEN = Value(0x00ff00,"Green")
  val CYAN = Value(0x00ffff,"Cyan")
  val YELLOW = Value(0xffff00,"Yellow")
  val WHITE = Value(0xffffff,"White")
  val BLUE = Value(0x0000ff,"Blue")
  val MAGENTA = Value(0xff00ff,"Magenta")
}

