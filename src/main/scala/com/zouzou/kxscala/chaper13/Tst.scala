package com.zouzou.kxscala.chaper13

import scala.collection.mutable.HashMap
import scala.collection.immutable.Map


object Tst1{

  def main(args: Array[String]): Unit = {
    for ((key, set) <- indexesSet("zhouzouzou")){
      print(key + ":")
      for (value <- set){
        print(value)
      }
      println()
    }
  }

  def indexesSet(str: String): Map[Char, Set[Int]]={
    val rstMap = new HashMap[Char, Set[Int]]()
    for(i <- 0 until str.length){
      val ch = str.charAt(i)

      if (!rstMap.contains(ch)){
        val bufferSet = Set(i)
        rstMap += (ch -> bufferSet)
      }else{
        val bufferSet = rstMap.get(ch) match{case Some(x) => x + i}
        rstMap.put(ch, bufferSet)
      }
    }
    rstMap.toMap
  }
}

object Tst2{

  def main(args: Array[String]): Unit = {
    for ((key, list) <- indexesList("zhouzouzou")){
      print(key + ":")
      for (value <- list){
        print(value)
      }
      println()
    }
  }

  def  indexesList(str: String): Map[Char, List[Int]] = {
    val rstMap = new HashMap[Char, List[Int]]()
    for (i <- 0 until str.length){
      val ch = str.charAt(i)

      if (!rstMap.contains(ch)){
        val bufferList = List(i)
        rstMap += (ch -> bufferList)
      }else{
        val bufferList = rstMap.get(ch) match {case Some(x) => i :: x.reverse}
        rstMap.put(ch, bufferList.reverse)
      }
    }
    rstMap.toMap
  }
}

object Tst3{
  def main(args: Array[String]): Unit = {
    val list = List(1,2,4,0,9,0)
    for (value <- remove0(list)){
      print(value + ",")
    }
  }

  def remove0(list: List[Int]): List[Int] = {
    var rstList: List[Int] = Nil
    for (value <- list if value != 0) {
      rstList = value :: rstList
    }
    rstList.reverse
  }
}

object Tst4{
  def main(args: Array[String]): Unit = {
    val arr = Array("Tom","Fred","Harry")
    val map = Map("Tom" -> 3,"Harry" -> 5, "Sony" -> 7)
    getNumbers(arr, map)
  }

  def getNumbers(array: Array[String], map: Map[String, Int]): Array[Int] = {
    array.flatMap(key => {
      map.get(key)
    })
  }
}

object Tst5{
  def main(args: Array[String]): Unit = {
    val array = Array("a", "b", "c")
    val rst = customMKString(array, "-")
    println(rst)
  }

  def customMKString(array: Array[String], string: String): String = {
    def func(strL: String, strR: String): String = {
      strL + string + strR
    }
    array.reduceLeft(func)
  }
}

object Tst6{
  def main(args: Array[String]): Unit = {
    val lst = List(1,3,5)
    val rst1 = (lst:\ List[Int]())(_ :: _)
    val rst2 = (List[Int]() /: lst)(_ :: _)
    println(rst1)
    println(rst2)

    val rst3 = (lst :\ List[Int]())(_ :: _)
    println(rst3)
  }
}