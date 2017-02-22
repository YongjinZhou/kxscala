package com.zhouyongjin.kxscala.chaper8

/**
  * Created by zhouyongjin on 2017/2/22.
  */

import scala.collection.mutable.ArrayBuffer

/**
  * 1、扩展如下的BankAccount类，新类CheckingAccount对每次存款和取款都收取1美元的手续费。
  * @param initialBalance
  */
class BankAccount(initialBalance: Double){

  private var balance = initialBalance

  def deposit(amount: Double) = { balance += amount;balance}

  def withdraw(amount: Double)  = { balance –= amount; balance}

  def getBalance:Double = balance
}

class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance){

  override def deposit(amount: Double): Double = super.deposit(amount - 1)

  override def withdraw(amount: Double): Double = super.withdraw(amount + 1)
}

/**
  * 2.扩展前一个练习的BankAccount类，新类SavingsAccount每个月都有利息产生（earnMonthlyInterest方法被调用），
  * 并且有每月三次免手续费的存款或取款。在earnMonthlyInterest方法中重置交易计数。
  */

class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance){

  private var num:Int = _

  private var rate:Double = _

  def setRate(rate:Double): Unit ={
    if (rate < 1 && rate > 0) this.rate = rate
  }

  def earnMonthlyInterest(): Unit ={
    num = 3
    super.deposit(getBalance * rate)
  }

  override def deposit(amount: Double): Double = {
    num -= 1
    if (num  < 0) super.deposit(amount - 1) else super.deposit(amount)
  }

  override def withdraw(amount: Double): Double = {
    num -= 1
    if (num < 0) super.withdraw(amount + 1) else super.withdraw(amount)
  }
}

/**
  * 3、翻开你喜欢的Java或C++书籍，一定会找到用来讲解继承层级的示例，可能是员工、宠物、图形等，用Scala来实现这个示例。
  */
class Person{

  def eat(): Unit ={}

}

class Teacher extends Person{

  override def eat(): Unit = super.eat()

}

class Student extends Person{

  override def eat(): Unit = super.eat()

}

/**
  * 4、定义一个抽象类Item，加入方法price和description，SimpleItem是一个在构造器中给出价格和描述的物件。
  * 利用val可以重写def这个事实。Bundle是一个可以包含其他物件的物件。
  * 其价格是打包中所有物件的价格之和。同时提供一个将物件添加到打包当中的机制，以及一个合适的description方法。
  */
abstract class Item{

  def price():Double

  def desc():String

  override def toString: String = {"desc:" + desc() + ",price:" + price()}
}

class SimpleItem(val price:Double, val desc:String) extends Item{

}

class Bundle extends Item{

  val item = new ArrayBuffer[Item]()

  def addItem(item: Item): Unit ={
    this.item += item
  }

  def price():Double = {
    var rst:Double = 0.0
    item.foreach(rst += _.price())
    rst
  }

  def desc():String = {
    item.mkString("")
  }
}

/**
  * 5、设计一个Point类，其x和y坐标可以通过构造器提供，提供一个子类LabeledPoint，其构造器接受一个标签值和x\y坐标，
  * 比如：new LabeledPoint(“Black Thurstry”,1929,230.07)
  * @param x
  * @param y
  */
class Point(var x:Double, var y:Double){

}

class LabeledPoint(var lable:String, override var x:Double, override var y:Double) extends Point(x,y){

}

/**
  * 6、定义一个抽象类Shape，
  * 一个抽象方法centerPoint，以及该抽象类的子类Rentangle和Circle。为子类提供合适的构造器，并重写centerPoint方法。
  */
abstract class Shape{
  def centerPoint()
}

class Rentangle(startX:Double,startY:Double,endX:Double,endY:Double) extends Shape{
  def centerPoint(): Unit ={
    val x = (startX + endX) / 2
    val y = (startY + endY) / 2
  }
}

class Circle(x:Double,y:Double,r:Double) extends Shape{
  def centerPoint(): Unit ={
    val cx = x
    val cy = y
  }
}

/**
  * 7、提供一个Square类，扩展自java.awt.Rectangle并且有三个构造器：
  * 一个以给定的端点和宽度构造正方形，一个以（0,0）为端点构造和给定的宽度构造正方形，
  * 一个以（0,0）为端点、0为宽度构造正方形。
  */

import java.awt
class Square(point:awt.Point,width:Int) extends awt.Rectangle(point.x,point.y,width,width){

  def this(width:Int){
    this(new awt.Point(0,0),width)
  }

  def this(){
    this(new awt.Point(0,0),0)
  }
}

/**
  * 编译8.6节中的Person和SecretAgent类并使用javap分析类文件。
  * 总共有多少name的getter方法？它们分别取什么值？（提示：可以用 –c 和 -private选项）
  * 没太懂。
  * @param name
  */
class Person(val name:String){
  override def toString: String = getClass.getName + "[name=" + name +"]"
}

class SecretAgent(codename:String) extends Person(codename){
  override val name = "secret"

  override def toString: String = "secret"
}
/*
Compiled from "Student.scala"
public class SecretAgent extends Person {
  private final java.lang.String name;
  public java.lang.String name();
  public java.lang.String toString();
  public SecretAgent(java.lang.String);
}
Compiled from "Student.scala"
public class Person {
  private final java.lang.String name;
  public java.lang.String name();
  public java.lang.String toString();
  public Person(java.lang.String);
}
*/
/**
  * 9、在8.10节的Creature类中，将val range 替换成一个def。
  * 如果你在Ant子类中也用def的话会有什么效果？如果子类中使用val又会有什么效果？为什么？
  * 答案：我的都可以编译通过，但是网上有其他同学，说使用val不能编译通过。。不知懂为什
  */
class Creature{
  val range:Int = 10
  val env:Array[Int] = new Array[Int](range)
}

class Ant extends Creature{
  override val range: Int = 2
}

/**
  * Compiled from "Tst.scala"
public class Ant extends Creature {
  private final int range;
  public int range();
  public Ant();
}
Compiled from "Tst.scala"
public class Creature {
  private final int[] env;
  public int range();
  public int[] env();
  public Creature();
}
  */

/**
  * 10.文件scala/collection/immutable/Stack.scala包含如下定义：
  * class Stack[A] protected(protected val elems: List[A])请解释protected关键字的含义。
  * （提示：回顾我们在第5章关于私有构造器的讨论。）
  * 被protected修饰的。只能被其子类来调用，而不能被外界直接调用
  */



