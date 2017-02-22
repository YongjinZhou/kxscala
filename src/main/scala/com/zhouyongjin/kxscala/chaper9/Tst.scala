package com.zhouyongjin.kxscala.chaper9

/**
  * Created by zhouyongjin on 2017/2/22.
  */
import java.io._

import scala.collection.mutable.ArrayBuffer
import scala.io.{BufferedSource, Source}


object Tst{

  def main(args: Array[String]): Unit = {
    //exercise_1()
    //exercise_2()
    //exercise_3()
    //print(exercise_4())
    exercise_9()//exercise_5()

  }

  /**
    * 编写一小段Scala代码，将某个文件中的行倒转顺序(将最后一行作为第一行,依此类推)
    */
  def exercise_1(): Unit ={
    val path:File = new File("./src/main/resources/test.txt")
    val reader = Source.fromFile(path).getLines()
    val rst = reader.toArray.reverse

    val toPath:File = new File("./src/main/resources/rst.txt")
    val pw = new PrintWriter(toPath)
    rst.foreach(line => pw.write(line + "\n"))
    pw.close()
  }

  /**
    * 2、编写Scala程序,从一个带有制表符的文件读取内容,将每个制表符替换成一组空格,
    * 使得制表符隔开的n列仍然保持纵向对齐,并将结果写入同一个文件
    */
  def exercise_2(): Unit ={
    val path:File = new File("./src/main/resources/test.txt")
    val reader = Source.fromFile(path).getLines()
    val rst = for(line <- reader) yield line.replaceAll("\\\t","")

    val pw = new PrintWriter("./src/main/resources/rst_2.txt")
    rst.foreach(line => pw.write(line + "\n"))
    pw.close()
  }

  /**
    * 3、编写一小段Scala代码,从一个文件读取内容并把所有字符数大于12的单词打印到控制台。如果你能用单行代码完成会有额外奖励
    */
  def exercise_3(): Unit ={
    val path = "./src/main/resources/test.txt"
    Source.fromFile(path).mkString.split("\\s+").foreach(word => if (word.length > 12) print(word + "\n"))
  }

  /**
    * 编写Scala程序，从包含浮点数的文本文件读取内容，打印出文件中所有浮点数之和，平均值，最大值和最小值
    * @return
    */
  def exercise_4() ={
    val path = "./src/main/resources/test.txt"
    val numbers = Source.fromFile(path).mkString.split("\\s+")

    var total:Double = 0.0
    numbers.foreach(total += _.toDouble)
    val avg = total/numbers.length
    val min = numbers.min
    val max = numbers.max
    (total,avg,min,max)
  }

  /**
    * 编写Scala程序，向文件中写入2的n次方及其倒数，指数n从0到20。对齐各列:
    */
  def exercise_5(): Unit ={
    val path = "./src/main/resources/test.txt"
    val pw = new PrintWriter(path)

    for (i <- 0 to 20){
      val t = BigDecimal(2).pow(i)
      pw.write(t.toString + "\t\t\t\t")
      pw.write((1/t).toString + "\n")
    }

    pw.close()
  }

  /**
    *6、编写正则表达式,匹配Java或C++程序代码中类似"like this,maybe with \" or\\"这样的带引号的字符串。
    * 编写Scala程序将某个源文件中所有类似的字符串打印出来
    */
  def exercise_6(): Unit ={
    val path = "./src/main/resources/test.txt"
    val source = Source.fromFile(path).mkString
    val pattern = "\\w+\\s+\"".r
    pattern.findAllIn(source).foreach(println)
  }

  /**
    * 7、编写Scala程序，从文本文件读取内容，并打印出所有的非浮点数的词法单位。要求使用正则表达式
    */
  def exercise_7(): Unit ={
    val path = "./src/main/resources/test.txt"
    val source = Source.fromFile(path).mkString
    val pattern = """\d.\d""".r
    pattern.findAllIn(source).foreach(println)
  }

  /**
    * 8、编写scala程序，打印某个html页面中的所有img标签的src属性
    * @param url
    */
  def exercise_8(url:String): Unit ={
    val source:BufferedSource = null

    val htmlContent = Source.fromURL(url,"utf-8").mkString
    //println(htmlContent)
    val pattern = """<img.*?>""".r
    val srcpattern = """src=("[^"]+")|('[^']+')""".r
    val arr = pattern.findAllIn(htmlContent).toArray
    for(line <- arr){
      println("url:"+line.toString)
      if(srcpattern.findFirstIn(line) != None){
        println(srcpattern.findFirstIn(line).getOrElse())
      }
    }
  }

  /**
    * 9、编写Scala程序，盘点给定目录及其子目录中总共有多少以.class为扩展名的文件
    */
  def exercise_9(): Unit ={
    val file:File = new File("./")
    def subdir(dir:File): Iterator[File] ={
      val child = dir.listFiles().filter(_.getName.endsWith("class"))
      child.toIterator ++ dir.listFiles().filter(_.isDirectory).toIterator.flatMap(subdir _)
    }

    val n = subdir(file).length
    print(n)
  }

  /**
    * 扩展Person类，并将其序列化；最后再读取
    */
  class Person extends Serializable{
    var friends:ArrayBuffer[Person] = new ArrayBuffer[Person]()
    var name:String = ""

    def this(name:String){
      this()
      this.name = name
    }

    def getName():String={
      this.name
    }

    def addFriend(person:Person): Unit ={
      friends += person
    }

    def getFriendList():Unit = {
      for(person <- friends){
        print(person.getName()+" ")
      }
      println
    }
  }

  def exercise_10():Unit = {
    val person1 = new Person("关羽")
    val person2 = new Person("刘备")
    val person3 = new Person("张飞")
    person2.addFriend(person1)
    person2.addFriend(person3)
    val arrs = Array(person1,person2,person3)

    val out = new ObjectOutputStream(new FileOutputStream(""))
    out.writeObject(arrs)
    out.flush()
    out.close()

    val in = new ObjectInputStream(new FileInputStream(""))
    val readObj:Array[Person] = in.readObject().asInstanceOf[Array[Person]]
    println(readObj(1).getName())
    readObj(1).getFriendList()
  }
}

