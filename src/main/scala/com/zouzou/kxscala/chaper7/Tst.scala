package com.zhouyongjin.kxscala.chaper7

/**
  * Created by zhouyongjin on 2017/2/22.
  */

/**
  * 1.编写示例程序，展示为什么
package com.horstmann.impatient
不同于
package com
package horstmann
package impatient

第一种方式子类不可以使用父包里的类.
  */
package com{
  class T1(){}
  package horstmann{
    class T2(t:T1){}
    package impatient{
      class T3(t1:T1,t2:T2){}
    }
  }
}

package com.horstmann.impatient{
  //class T4(t1:T1,t3:T3){}//not found:type T1
}

/**
  * 2.编写一段让你的Scala朋友们感到困惑的代码，使用一个不在顶部的com包
  * 是这个意思吗？
  */
import java.util

import com._
import com.horstmann._

import scala.collection.mutable
object Exercise_2{
  val t1:T1 = new T1()
  var t2:T2 = new T2(t1)
}

/**
  * 3.编写一个包random,加入函数nextInt():Int,nextDouble():Double,setSeed(seed:Int):Unit。
  * 生成随机数的算法采用线性同余生成器:后值 = (前值 * a + b)mod 2^n
  * 其中,a = 1664525,b=1013904223,n = 32,前值的初始值为seed
  */
package random{
  package object random{
    //setSeed()
    var seed:Int = _
    val a = BigDecimal(1664525)
    val b = BigDecimal(1013904223)
    val n = 32

    def nextInt:Int = {
      val tmp = (seed * a + b) % BigDecimal(2).pow(n)
      seed = tmp.toInt
      seed
    }

    def nextDouble:Double = {
      nextInt.toDouble
    }

  }
}

/**
  * 4.在你看来Scala的设计者为什么要提供package object语法而不是简单的让你将函数和变量添加到包中呢？
  * 答案：虚拟机的局限
  */

/**
  * 5.private[com] def giveRaise(rate:Double)的含义是什么？有用吗？
  * 答案：private[com]定义包可见性，除了com包其他包都不能访问。
  */

/**
  * 6、编写一段程序,将Java哈希映射中的所有元素拷贝到Scala哈希映射。用引入语句重命名这两个类.
  */
import java.util.{HashMap => JavaHashMap}
import scala.collection.mutable.HashMap
object Exercise_6 extends App{
  val javaMap = new JavaHashMap[String,Int]()
  javaMap.put("tst1",11)
  javaMap.put("tst2",12)
  javaMap.put("tst3",13)

  val scalaMap = new HashMap[String,Int]()
  for (key <- javaMap.keySet().toArray)
    scalaMap += (key.toString -> javaMap.get(key))

  print(scalaMap.mkString)
}

/**
  * 7、在前一个练习中，将所有引入语句移动到尽可能小的作用域里.
  * 和第六题基本上一样，只是可以把import放作用域中。
  */
object Exercise_7 extends App{
  import java.util.{HashMap => JavaHashMap}
  import scala.collection.mutable.HashMap

  val javaMap = new JavaHashMap[String,Int]()
  javaMap.put("tst1",11)
  javaMap.put("tst2",12)
  javaMap.put("tst3",13)

  val scalaMap = new HashMap[String,Int]()
  for (key <- javaMap.keySet().toArray)
    scalaMap += (key.toString -> javaMap.get(key))

  print(scalaMap.mkString)
}

/**
  * 8.以下代码的作用是什么？这是个好主意吗？
  * import java._
  * import javax._
  * 答案：导入Java和javax中的所有类，这两个包中中只有子包，不包含类，所以无用。
  */


/**
  * 9、编写一段程序，引入java.lang.System类，从user.name系统属性读取用户名，
  * 从Console对象读取一个密码,如果密码不是"secret"，则在标准错误流中打印一个消息；
  * 如果密码是"secret"，则在标准输出流中打印一个问候消息。不要使用任何其他引入，也不要使用任何限定词(带句点的那种).
  * 答案：没有用到java.lang.System?
  */
import java.lang.System
object Exercise_10 extends App{
  val password = Console.in.readLine()
  if (password.equals("secret")) print("Hello" + System.getProperty("user.name"))
  else print("password error.")
}

/**
  *10、除了StringBuilder,还有哪些java.lang的成员是被scala包覆盖的？
  * 答案：从上面的9题可以发现好java.lang中的大多数，在scala包中都有实现。
  */


