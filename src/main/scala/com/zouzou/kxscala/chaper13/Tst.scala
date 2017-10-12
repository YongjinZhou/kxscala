package com.zouzou.kxscala.chaper13

import scala.collection.mutable.HashMap
import scala.collection.immutable.Map


object Tst1 {

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