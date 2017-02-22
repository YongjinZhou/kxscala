package com.zhouyongjin.kxscala.chaper2

/**
  * Created by zhouyongjin on 2017/2/22.
  */
class Tst {}

/*
1、如果一个数字为正数，则它的signum为1；如果是负数，则signum为-1；如果是0，则signum是0。编写一个函数来计算这个值。
答案：
def exercise_1(para:Int): Unit ={
  if (para > 0) 1 else if (para < 0) -1 else 0
}
2、一个空的块表达式{}的值时多少？类型时什么？
答案：
scala> val x = {}
x: Unit = ()
3、指出在Scala中何种情况下，赋值语句x=y=1是合法的。（提示：给x找一个合法的类型）
答案：y=1表达式返回的是Uint，所以，让x为Uint类型时，该赋值语句会编译合法。
scala> var x = {}
x: Unit = ()
scala> var y = 0
y: Int = 0
scala> x=y=1
x: Unit = ()
4、针对下列java循环编写一个scala版for(int i=10;i>=0;i--)System.out.println(i)
答案：下面3种方式都是可以。
def exercise_4(): Unit ={
  for (i <- 10.to(1,-1)) print(i + ",")
  for (i <- 10 to 1 by -1) print(i + ",")
  for (i <- 1 to 10 reverse) print(i + ",")
}
5、编写一个过程countdown(n:Int),打印从n到0的数字。
//exercise_5
def countdown(n:Int): Unit ={
  if (n > 0){
    for (i <- n.to(0,-1)) print(i + ",")
  }

  if (n < 0)
    for (i <- n.to(0, 1)) print(i + ",")
}

6、编写一个for循环，计算字符串中所有字母的unicode代码的乘积。举例来说，”Hello”中所有字符的乘积为9415087488L。
答案：
def exercise_6(str: String)={
  var sum: Long = 1
  for (ch <- str){
    sum = sum * ch.toLong
  }
  sum
}
7、同样解决前一习题问题，但是不使用循环。（提示：在scalaDoc中查看StringOps）
def exercise_7(str: StringOps)={
  str.map(_.toLong).product
}
8、编写一个函数product(s:string),计算前面练习中提到的乘积。
def exercise_8(str:String)={
  //实现逻辑和第六题一样。
}

9、把前一个练习中的函数改成递归函数。
def exercise_9(str: String): Long = {
  if (str.length == 1) str.charAt(0).toLong
  else str.take(1).charAt(0).toLong * exercise_9(str.drop(1))
}

10、编写函数计算x^n其中n为整数，使用如下递归定义：
x^n = y^2,如果n为正偶数的话，这里的y=x^(n/2)。
…
答案：
def exercise_10(x: Int, n: Int):BigInt = {
  var rst: BigInt = 0
  if (n > 0 && n % 2 == 0) rst = exercise_10(x,n/2) * exercise_10(x,n/2)
  if (n > 0 && n % 2 == 1) rst = x * exercise_10(x, n - 1)
  if (n == 0) rst = 1
  if (n < 0) rst = 1 / exercise_10(x,-1 * n)
  rst
}


 */
