package com.zouzou.kxscala.chaper1

/**
  * Created by zhouyongjin on 2017/2/22.
  */
class Tst {}

/*
1、在scala中输入3，然后按tab键，看一下有哪些方法可用？
答案：输入3tab没有任务反应，3.tab，会有如下方法可用。
scala> 3.
!=   ==             doubleValue   isInstanceOf    min              toChar          unary_-
##   >              ensuring      isNaN           ne               toDegrees       unary_~
%    >=             eq            isNegInfinity   notify           toDouble        underlying
&    >>             equals        isPosInfinity   notifyAll        toFloat         until
*    >>>            floatValue    isValidByte     round            toHexString     wait
+    ^              floor         isValidChar     self             toInt           |
-    abs            formatted     isValidInt      shortValue       toLong          →
->   asInstanceOf   getClass      isValidLong     signum           toOctalString
/    byteValue      hashCode      isValidShort    synchronized     toRadians
<    ceil           intValue      isWhole         to               toShort
<<   compare        isInfinite    longValue       toBinaryString   toString
<=   compareTo      isInfinity    max             toByte           unary_+
2、在scala在计算3的平方根，然后再对该值求平方。现在，这个结果与相差多少？
答案：
scala> math.sqrt(3)
res0: Double = 1.7320508075688772

scala> res0*res0
res1: Double = 2.9999999999999996

3、res是val还是var？
答案：我们尝试给res1赋值，结果提示我们错误：无法给val赋值。
scala> res1=12
<console>:12: error: reassignment to val
       res1=12
4、Scala允许你用数字去乘字符串——去repl中试一下，”carzy” * 3。这个操作做什么？在Scaladoc中怎么找到这个操作？
答案：字符串乘数字是重复字符串。
scala> "crazy" * 3
res2: String = crazycrazycrazy
5、10 max 2 的含义是什么？max方法定义在哪个类中？
答案：max取两者的最大值，与min对应。max定义在scala.math包中，不定义在哪个类中。
scala> 10 max 2
res3: Int = 10
scala> import scala.math.
BigDecimal                     Numeric                      abs     exp     package   tan
BigInt                         Ordered                      acos    expm1   pow       tanh
E                              Ordering                     asin    floor   random    toDegrees
Equiv                          PartialOrdering              atan    hypot   rint      toRadians
Fractional                     PartiallyOrdered             atan2   log     round     ulp
IEEEremainder                  Pi                           cbrt    log10   signum
Integral                       ScalaNumber                  ceil    log1p   sin
LowPriorityEquiv               ScalaNumericAnyConversions   cos     max     sinh
LowPriorityOrderingImplicits   ScalaNumericConversions      cosh    min     sqrt
6、用BigInt计算2的1024次方。
答案：
scala> BigInt(2).pow(1024)
res4: scala.math.BigInt = 179769313486231590772930519078902473361797697894230657273430081157732675805500963132708477322407536021120113879871393357658789768814416622492847430639474124377767893424865485276302219601246094119453082952085005768838150682342462881473913110540827237163350510684586298239947245938479716304835356329624224137216
7、为了在使用probablePrime（100，Random）过去随机数时，不再probablePrime以及Random之前写任何限定符，你需要引入什么？
答案：
(1)直接引入相应的类路径。
import scala.util.Random
import scala.math.BigInt.probablePrime
(2)只所在的包的所有
import scala.math.BigInt._
import scala.util._

8、创建随机文件的方式之一是生成一个随机的BigInt，然后将它转换成三十六进制，输出类似"qsnvbevtomcj38o06kul"这样的字符串。查阅Scaladoc，找到在Scala中实现该逻辑的办法。
到BigInt中查找方法。
答案：
scala> BigInt(scala.util.Random.nextInt).toString(36)
res6: String = e9adus

9、在Scala中如何获取字符串的首字符和尾字符？
答案：
//首字符
scala> "scala"(0)
res7: Char = s
scala> "scala".take(1)
res8: String = s
//尾字符
scala> "scala".takeRight(1)
res9: String = a
scala> "scala".reverse(0)
res11: Char = a
10、take,drop,takeRight和dropRight这些字符串函数是做什么用的？和substring相比，他们的优点和缺点都是哪些？
答案：
//take时取前2个字符。
scala> "scala".take(2)
res12: String = sc
//drop时去掉前2个字符。
scala> "scala".drop(2)
res13: String = ala
//取后面2个字符
scala> "scala".takeRight(2)
res14: String = la
//去掉后面2个字符
scala> "scala".dropRight(2)
res15: String = sca
和substring的区别：substring指定开始位置和结束位置，重要的是位置不能越界。而take，不存在这个问题。
scala> "scala".substring(0,2)
res16: String = sc

scala> "scala".substring(0,7)
java.lang.StringIndexOutOfBoundsException: String index out of range: 7
  at java.lang.String.substring(String.java:1963)
  ... 29 elided

scala> "scala".take
take   takeRight   takeWhile

scala> "scala".take(7)
res18: String = scala

 */
