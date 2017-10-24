package com.zouzou.kxscala.chaper14

import scala.reflect.ClassTag

object Tst1{

}

object Tst2{
  def swapFun[K, T](tuple: (K, T)) = {
    tuple match {
      case (a, b) => (b, a)
      case _ => println("no Match")
    }
  }

  def main(args: Array[String]): Unit = {
    val rst = swapFun[String, String](("first", "second"))
    println(rst)
  }
}

object Tst3{
  def swapFun[T: ClassTag](array: Array[T]): Array[T] = {
    array match {
      case Array(a, b, arrBuff @ _*) => Array(b, a) ++ arrBuff
      case _ => array
    }
  }

  def main(args: Array[String]): Unit = {
    val array = Array("zouzou", "zhou", "china", "earth")
    val rst = swapFun(array)
    println(rst.mkString(","))
  }
}

object Tst4{
  abstract class Item

  case class Multiple(num: Int, item: Item) extends Item

  case class Article(desc: String, price: Double) extends Item
  case class Bundle(desc: String, discount: Double, items: Item*) extends Item

  def price(item: Item): Double = {
    item match {
      case Article(_, p) => p
      case Bundle(_, discount, items @ _*) => items.map(price _).sum - discount
      case Multiple(n, item) => n * price(item)
    }
  }

  def main(args: Array[String]): Unit = {
    val rst = price(Multiple(10, Article("zouzou", 19.9)))
    print(rst)
  }
}

object Tst5{

  def leafSum(list: List[Any]): Int = {
    var rst = 0

    list.foreach{
      ele => ele match {
        case lst: List[Any] => rst += leafSum(lst)
        case i: Int => rst += i
      }
    }
    rst
  }

  val lst: List[Any] = List(List(3, 8), 2, List(5))

  def main(args: Array[String]): Unit = {
    print(leafSum(lst))
  }
}

object Tst6{
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

  def leafSum(tree: BinaryTree): Int = {
    tree match {
      case Node(l, r) => leafSum(l) + leafSum(r)
      case Leaf(l) => l
    }
  }

  def main(args: Array[String]): Unit = {
    val tree = Node(Node(Leaf(1),Leaf(2)), Node(Leaf(3),Node(Leaf(4), Leaf(5))))
    print(leafSum(tree))
  }
}

object Tst7{
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(tree: BinaryTree*) extends BinaryTree

  val in = Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))

  def leafSum(tree: BinaryTree): Int ={
    tree match {
      case Node(node @ _*) => node.map(leafSum).sum
      case Leaf(l) => l
    }
  }
}

