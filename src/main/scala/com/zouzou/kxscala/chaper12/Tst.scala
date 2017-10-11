package com.zouzou.kxscala.chaper12

/**
  * Created by zhouyongjin on 2017/2/22.
  */
object Tst{
  def main(args: Array[String]): Unit = {
    //println(value(x => x * x, -5, 5))
    //exercise_2()
    //exercise_3(1,0)
    //val arr = Array(3,2,6,8,4,6,9,3,6,7,1,2)
    //largest(x=>10*x-x*x,arr)
    //exercise_7()
    //exercise_8()
    exercise_9()
  }
  /**
    * 1、
    * @param fun
    * @param low
    * @param high
    * @return
    */
  def value(fun:(Int) => Int, low:Int, high:Int) = {
    var array = List[(Int,Int)]()
    (low to high).foreach(
      num => array = (num,fun(num)) :: array
    )
    array
  }

  /**
    * 2、
    */
  def exercise_2(): Unit ={
    val array = for (num <- 1.to(10)) yield num
    val max = array.reduceLeft((a,b) => if (a > b) a else b)
    print(max)
  }

  /**
    * 3、
    * @param from
    * @param to
    */
  def exercise_3(from:Int, to:Int): Unit ={
    var rst:Int = 0
    if (from < to){
      rst = from.to(to).reduceLeft(_ * _)
    } else if (from == to){
      rst = from
    } else {
      rst = from.to(to, -1).reduceLeft(_ * _)
    }
    println(rst)
  }

  /**
    * 4、
    * @param from
    * @param to
    */
  def exercise_4(from:Int,to:Int): Unit ={
    var rst:Int = 0
    rst = from.to(to).foldLeft(1)(_ * _)
    println(rst)
  }

  /**
    * 5、
    * @param fun
    * @param inputs
    */
  def largest(fun:(Int) => Int,inputs:Seq[Int]): Unit ={
    var rst:Int = 0
    rst = inputs.reduceLeft((a,b) => if(fun(a) > fun(b)) fun(a) else fun(b))
    println(rst)
  }

  /**
    * 6、
    * @param fun
    * @param inputs
    */
  def exercise_6(fun:(Int) => Int,inputs:Seq[Int]): Unit ={
    var rst:Int = 0
    rst = inputs.reduceLeft((a,b) => if (fun(a) > fun(b)) a else b)
    println(rst)
  }

  /**
    * 7、
    */
  def exercise_7() = {
    def adjustToPair(fun:(Int,Int) => Int)(tup:(Int,Int)) = {
      fun(tup._1,tup._2)
    }

    val pairs=(1 to 10) zip (11 to 20)
    val rst = pairs.map(a => adjustToPair(_+_)(a))
    println(rst)
  }

  /**
    * 8、
    */
  def exercise_8() = {
    val name = Array("zhou","yong","jin")
    val count = Array(4,4,3)
    println(name.corresponds(count)(_.length==_))
  }

  /**
    * 9、
    */
  def exercise_9() ={
    val name = Array("zhou","yong","jin")
    val count = Array(4,4,3)

    def adjustToPair(fun:(String,Int) => Boolean)(tup:(String,Int)) = {
      fun(tup._1,tup._2)
    }

    val pairs = name zip count
    val rst:Boolean = true
    pairs.map(a => adjustToPair(_.length==_)(a)).foreach( b => b&rst)
    println(rst)
  }

  /**
    * 10、
    * @param condition
    * @param block
    */
  def unless(condition: => Boolean)(block: => Unit): Unit ={
    if (!condition) block
  }

  {
    var x = 5
    unless(x == 0){x = x -1;println(x)}
  }
}



