package com.zouzou.kxscala.chaper5

/**
  * Created by zhouyongjin on 2017/2/22.
  */
import scala.beans.BeanProperty

object Tst{
  def main(args: Array[String]): Unit = {
    /*第5题调用Student类中的JavaBean属性。
    val student:Student = new Student
    student.setName("zhou")
    println(student.getName)
    */
    /**第7题。
    val person7:Person7 = new Person7("zhou yong")
    println(person7) */
  }
}

/**
  * 1.改进Counter类，让它不要在大于Int.maxValue时变为负数
  */
class Counter{
  private var value = 0

  def increment(): Unit ={
    if (value < Int.MaxValue) value += 1
    else value = 0
  }

  def current() = value
}

/**
  * 2.写一个BankAccount类，加入deposit和withdraw方法，和一个只读的balance属性
  */
class BankAccount{
  private var balance:Double = 0

  def getBalance(): Double = {
    balance
  }

  def deposit(num:Double): Unit ={
    balance = balance + num
  }

  def withdraw(num:Double): Unit ={
    if (num < balance)balance = balance - num
    else throw new Exception("num over balance.")
  }
}

/**
  *3、定义一个Time类，有两个属性hours和minutes，
  *hour介于0和23之间另外还有一个检测时间是否早于另一个时间的方法，before(other:Time):Boolean
  * @param hours
  * @param minutes
  */
class Time(var hours:Int, var minutes:Int){
  if (hours >= 0 && hours <=23) this.hours = hours
  else{
    throw new Exception("First param is not allow")
  }
  if (minutes >= 0 && hours < 59) this.minutes = minutes
  else {
    throw new Exception("Second param is not allow.")
  }

  def before(other: Time): Boolean = {
    if (hours < other.hours || (hours == other.hours && minutes < other.minutes)) true else false

  }
}

/**
  * 4.重新实现Timer类，内部呈现改成字午夜起的分钟数
  * @param hours
  * @param minutes
  */
class Time4(hours:Int, minutes:Int){
  var min:Int = 0
  if (hours < 23 && hours >=0 && minutes >= 0 && minutes < 59) min = hours * 69 + minutes

  def before(other: Time4): Boolean = {
    if (this.min < other.min) true else false
  }
}

/**
  * 5、创建一个student类，加入可读写的JavaBean属性name(类型String)和id（类型Int）。有哪些方法被生成？
  * 用javap反编译。你可以在Scala中调用JavaBean版的getter和setter方法吗？因该这么做吗？
  */
class Student{
  @BeanProperty var name:String = ""
  var id:Int = 0
}

/**name作为JavaBean属性，编译后会生成4个函数。
  * Compiled from "Student.scala"
  public class Student {
  public java.lang.String name();
  public void name_$eq(java.lang.String);
  public int id();
  public void id_$eq(int);
  public java.lang.String getName();
  public void setName(java.lang.String);
  public Student();
}
  */

/**
  * 6.在5.2节的Persion类中加一个主构造器，将负年龄换为0
  * @param age
  */
class Person(var age:Int){
  if (age < 0) this.age = 0 else this.age = age
}

/**
  * 7、编写一个Person类，其主构造器接受一个字符串，该字符串包含名字、空格和姓，
  * 如new Person("Fred Smith")。提供只读属性，firstName和lastName,主构造器应该是var还是val还是普通参数？
  * @param name
  */
class Person7(name:String){
  val firstName:String = name.split(" ")(0)
  val lastName:String = name.split(" ")(1)

  override def toString: String = {
    "firstName:" + firstName + ",lastName:" + lastName
  }
}

/**
  * 8、创建一个Car类，以只读属性对应制造商，型号名称，型号年份以及一个可读写的属性用于车牌。
  * 提供四组构造器。每个构造器fc都要求制造商和型号为必填。型号年份和车牌可选，
  * 如果未填，则型号年份为-1，车牌为空串。你会选择哪一个作为你的主构造器？为什么
  * @param maker
  * @param typeName
  * @param year
  * @param carLice
  */
class Car(val maker:String,val typeName:String,val year:Int = -1,var carLice:String = ""){
  def this(maker:String,typeName:String){
    this(maker,typeName,-1,"")
  }

  def this(maker:String,typeName:String,year:Int){
    this(maker,typeName,year,"")
  }

  def this(maker:String,typeName:String,carLice:String){
    this(maker,typeName,-1,carLice)
  }
}

/**
  *10、重写该类,使用显示的字段定义，和一个缺省主构造器。你更倾向于使用哪种形式？为什么？
  */
class Employ{
  val name:String = "John Q. Public"
  var salary:Double = 0.0
}


