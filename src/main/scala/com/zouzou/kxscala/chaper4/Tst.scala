package com.zouzou.kxscala.chaper4

/**
  * Created by zhouyongjin on 2017/2/22.
  */
import java.util
import java.util.Calendar

import scala.collection.mutable
import scala.io.Source
import scala.collection.immutable.SortedMap
import scala.collection.JavaConverters._

object Tst{

  /**
    *1.设计1个映射，其中包含你需要的一些装备，以及他们的价格然后构建另一组映射，采用同样的键，但在价格上打九折.
    */
  def exercise_1(): Unit ={
    val map = Map[String,Double]("compter" -> 10000, "phone" -> 5000)
    for ((key,value) <- map) println(key+":" + value + " ")

    val mapRst = mutable.HashMap[String,Double]()
    for ((key,value) <- map) mapRst(key) = value * 0.9
    for ((key,value) <- mapRst) println(key+ ":" + value + " ")
  }

  /**
    * 2、编写一段程序，从文件中读取单词；用一个可变的映射清点单词出现的频率
    * @param filePath
    */
  def exercise_2(filePath: String): Unit ={
    val map = new mutable.HashMap[String,Int]()
    Source.fromFile(filePath).getLines().foreach(
      lines => {
        for (word <- lines.split(" ")){
          if (!map.keySet.contains(word.toLowerCase))
            map(word.toLowerCase) = 0
          map(word.toLowerCase) = map(word.toLowerCase) + 1
        }
      }
    )

    for ((key,value) <- map) println("word:" + key + ",count:" + value)
  }

  /**
    * 3.重复做前一个练习，这次用不可变映射
    * @param filePath
    */
  def exercise_3(filePath: String): Unit ={
    var map = Map[String,Int]()
    Source.fromFile(filePath).getLines().foreach(
      lines => {
        for (word <- lines.split(" ")){
          if (!map.keySet.contains(word.toLowerCase)){
            val newCountMap: Map[String,Int] = map + (word.toLowerCase -> 1)
            map = newCountMap
          }else{
            val count:Int = map(word.toLowerCase) + 1
            val newCountMap: Map[String,Int] = map - word.toLowerCase + (word.toLowerCase -> count)
            map = newCountMap
          }
        }
      }
    )

    for ((key,value) <- map) println("word:" + key + ",count:" + value)
  }

  /**
    * 4.重复前一个练习，这次用排序集合，使得单词可以以排序的方式输出
    * @param filePath
    */
  def exercise_4(filePath: String): Unit ={
    var map = SortedMap[String,Int]()
    Source.fromFile(filePath).getLines().foreach(
      lines => {
        for (word <- lines.split(" ")){
          if (!map.keySet.contains(word.toLowerCase)){
            val newCountMap:SortedMap[String,Int] = map + (word.toLowerCase -> 1)
            map = newCountMap
          }else{
            val count:Int = map(word.toLowerCase) + 1
            val newCountMap:SortedMap[String,Int] = map - word.toLowerCase + (word.toLowerCase -> count)
            map = newCountMap
          }
        }
      }
    )
    for ((key,value) <- map) println("word:" + key + ",count:" + value)
  }

  /**
    * 5.重复前一个练习，这次用java.util.TreeMap，并使其适用于scala
    * @param filePath
    */
  def exercise_5(filePath:String): Unit ={
    val map = new util.TreeMap[String,Int]().asScala
    Source.fromFile(filePath).getLines().foreach(
      lines => {
        for(word <- lines.split(" ")){
          if (!map.keySet.contains(word.toLowerCase)) map(word.toLowerCase) = 0
          map(word.toLowerCase) = map(word.toLowerCase) + 1
        }
      }
    )
    for ((key,value) <- map) println("word:" + key + ",count:" + value)
  }

  /**
    * 6、定义一个链式哈希映射,将"Monday"映射到java.util.Calendar.MONDAY,依次类推加入其他日期。
    * 展示元素是以插入的顺序被访问的LinkedHashMap的使用
    */
  def exercise_6(): Unit ={
    val map = new mutable.LinkedHashMap[String,Int]

    map += ("Monday"->Calendar.MONDAY)
    map += ("Tuesday"->Calendar.TUESDAY)
    map += ("Wednesday"->Calendar.WEDNESDAY)
    map += ("Thursday"->Calendar.THURSDAY)
    map += ("Friday"->Calendar.FRIDAY)
    map += ("Saturday"->Calendar.SATURDAY)
    map += ("Sunday"->Calendar.SUNDAY)

    println(map.mkString(","))
  }

  /**
    * 7.打印java系统属性表格
    */
  def exercise_7(): Unit ={
    val prop:mutable.Map[String, String] = System.getProperties.asScala
    val keys = prop.keySet
    val keyLengths = for (key <- keys) yield key.length
    val maxLengths = keyLengths.max
    for (key <- keys){
      print(key)
      print(" " * (maxLengths - key.length))
      print(" | ")
      println(prop(key))
    }
  }

  /**
    * 8.编写一个函数minmax(values:Array[Int]),返回数组中最大值和最小值的对偶
    * @param values
    * @return
    */
  def minmax(values: Array[Int])={
    (values.min,values.max)
  }

  /**
    * 9.编写一个函数lteqgt(values:Array[Int],v:Int),返回数组中小于v，等于v和大于v的数量要求一起返回
    * @param values
    * @param v
    * @return
    */
  def lteqgt(values:Array[Int],v:Int)={
    var lcount = 0
    var ecount = 0
    var gcount = 0
    for (e <- values){
      if (e < v) lcount += 1
      if (e > v) gcount += 1
      if (e == v) ecount += 1
    }
    (lcount,ecount,gcount)
  }

  /**
    * 当你将两个字符串拉链在一起，比如"Hello".zip("World")，会是什么结果？想出一个讲得通的用例.
    */
  def exercise_10(): Unit ={
    "hello".zip("world")
  }
  def main(args: Array[String]): Unit = {
    //exercise_1()
    //exercise_2("/Users/zhouyongjin/IdeaProjects/kxscala/src/main/resources/wordcount.txt")
    //exercise_4("/Users/zhouyongjin/IdeaProjects/kxscala/src/main/resources/wordcount.txt")
    //exercise_5("/Users/zhouyongjin/IdeaProjects/kxscala/src/main/resources/wordcount.txt")
    //exercise_7()
    val array = Array(1,2,3,4,5)
    print(lteqgt(array,3))
  }
}


