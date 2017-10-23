package com.zouzou.kxscala.chaper13

import scala.collection.immutable.{HashMap, Map}


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
    val rst2 = (List[Int]() /: lst)(_ :+ _)
    println(rst1)
    println(rst2)

    val rst3 = (lst :\ List[Int]())((a, b) => a :: b)
    println(rst3)

    val rst4 = (List[Int]() /: lst)((a, b) => b :: a)
    println(rst4)
  }
}

object Tst7{
  def main(args: Array[String]): Unit = {
    val prices = List(5.0, 20.0, 9.95)
    val quantities = List(10, 2, 1)

    val func = Function.tupled((p: Double, q: Int) => p * q)

    println((prices zip quantities).map(p => p._1 * p._2))
    println((prices zip quantities).map(Function.tupled(_ * _)))
    println((prices zip quantities).map(func))
  }
}

object Tst8{
  def main(args: Array[String]): Unit = {
    val array = Array[Double](1,2,3,4,5,6)
    val rstArray = array2multiDim(array, 3)
    rstArray.foreach(ele => println(ele.mkString(",")))
  }

  def array2multiDim(array: Array[Double], col: Int) = {
    array.grouped(col).toArray
  }
}

object Tst9{
  def main(args: Array[String]): Unit = {
    val value = "adfafdassfsadfasf"

    val rst = value.par.aggregate(new HashMap[Char, Int])({
      (a, b) => {a + (b -> (a.getOrElse(b,0) + 1))}
      },{
        (mapA, mapB) => (mapA.keySet ++ mapB.keySet).foldLeft(HashMap[Char, Int]()){
          (k, j) => k + (j -> (mapA.getOrElse(j, 0) + mapB.getOrElse(j, 0)))
        }
      }
    )
  }
}