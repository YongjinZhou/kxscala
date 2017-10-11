package com.zouzou.kxscala.chaper3

/**
  * Created by zhouyongjin on 2017/2/22.
  */
import java.awt.datatransfer.{DataFlavor, SystemFlavorMap}
import java.util.TimeZone

import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import scala.collection.JavaConverters._

object Tst{
  /**
    * 1、编写一段代码，将a设置为一个n个随机整数的数组，要求随机数介于0（包含）和n（不包含）之间。
    * @param n
    * @return Array[Int]
    */
  def exercise_1(n:Int): Array[Int] = {
    val random: Random = new Random()
    val array:Array[Int] = new Array[Int](n)
    for (i <- 0 until n) array(i) = random.nextInt(n)
    array
  }

  /**
    * 2、编写一段代码，将整个数组中相邻的值交换。例如Array(1,2,3,4,5)经过置换后变为Array(2,1,4,3,5)
    * @param array
    * @return
    */
  def exercise_2(array: Array[Int]): Array[Int] = {
    for (i <- 0 until array.length by 2){
      val tmp = array(i)
      array(i) = array(i + 1)
      array(i + 1) = tmp
    }
    array
  }

  /**
    * 3、重复前一个练习，不过这次生成的值交换过的数组，用for/yield
    * @param array
    * @return
    */
  def exercise_3(array: Array[Int]): Array[Int] = {
    val rstArr = for (i <- Range(0,array.length)) yield {
      if (array.length % 2 == 1 && i == array.length - 1) array(i)
      else if (i % 2 == 0) array(i + 1)
      else array(i - 1)
    }
    rstArr.toArray
  }

  /**
    * 4、给定一个整数数组，参数一个新的数组，包含原数组中的所有正值，以原有顺序排序。
    * 之后的元素是所有的零或负值，以原有顺序排序。
    * @param array
    * @return
    */
  def exercise_4(array: Array[Int]): Array[Int] = {
    var arrTmp1: Array[Int] = for (e <- array if e > 0) yield e
    val arrTmp2: Array[Int] = for (e <- array if e <= 0) yield e
    arrTmp1 = arrTmp1 ++ arrTmp2
    arrTmp1
  }

  /**
    * 5、如何计算Array[Double]的平均值。
    * @param array
    * @return
    */
  def exercise_5(array: Array[Double]): Double = {
    array.sum / array.length
  }

  /**
    * 6、如何重新组织Array[Int]的元素将它们以反序排列？对于ArrayBuffer[Int]你又会怎么做呢？
    * @param array
    * @return
    */
  def exercise_6(array: Array[Int]): Array[Int] = {
    val arrayBuffer: ArrayBuffer[Int] = ArrayBuffer(1,2,3,4,5,6)
    for (e <- arrayBuffer.reverse) print(e + ",")
    array.reverse
  }

  /**
    * 7.编写一段代码，产出数组中的所有值，去掉重复项。（提示查看Scaladoc）
    * @param array
    * @return
    */
  def exercise_7(array: Array[Int]): Array[Int] = {
    array.distinct
  }

  /**
    * 8.重新编写3.4节结尾的实例。手收集负值元素的下标，反序，去掉最后一个下标，
    * 然后对每一个下标调用a.remove(i)。比较这样做的效率和3.4节中另外两种方法的效率。
    * @param array
    * @return
    */
  def exercise_8(array: Array[Int]): Array[Int] = {
    val arrIndex = for (i <- 0 until array.length if array(i) < 0) yield{i}
    val indexArraybuffer = arrIndex.toBuffer
    indexArraybuffer.remove(0)

    val rst = array.toBuffer
    for (i <- indexArraybuffer){
      rst.remove(i)
    }
    rst.toArray
  }

  /**
    * 9.创建一个java.util.TimeZone.getAvailableIDs返回的时区的集合，判断条件是它们在美洲；
    * 去掉"America/"前缀并排序
    * @return
    */
  def exercise_9(): Array[String] = {
    val timezoneArr:Array[String] = TimeZone.getAvailableIDs
    val filerArr = for (e <- timezoneArr if !e.startsWith("America/")) yield e
    filerArr.sorted
  }

  /**
    * 10.引入java.awt.datatransfer._并构建一个类型为SystemFlavorMap()类型的对象：
    * val flavors = SystemFlavorMap.getDefaultMap().asInstanceOf[SystemFlavorMap]
    * 然后以DataFlavor.imageFlavor为参数调用getNativesForFlavor方法，以Scala缓冲保存返回值。（
    * 为什么用这样一个晦涩难懂的类？因为在java标准类库中很难找得到使用java.util.List的代码）
    */
  def execise10():Unit = {
    val flavors = SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]
    val buffer:Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor).asScala
  }

  def main(args: Array[String]): Unit = {
    //exerceise_x
  }
}
