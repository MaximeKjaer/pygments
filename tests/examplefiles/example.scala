#!/usr/bin/scala

// Imports
import // This is incorrect Scala but can still be highlighted correctly
import a.{x => y} // Test comment
import a.{x => } // This is incorrect Scala but can still be highlighted correctly
import a.{x => `test-name`} // Test comment
import a.given // Test comment
import a.{given a} // Test comment
import a.{x, y} // Test comment
import a._ // Test comment
import a.x // Test comment
import a.x.y.z // Test comment

// Literals
true false null
1 2 3 4
1L 1l 1ul 1UL
3.0 12.345
3f 3.0f 3F 3.0F
3d 3.0d 3D 3.0D
1e12 1e+34 1e-56 1e12f 1e+34f 1e-56f 1e12d 1e+34d 1e-56d
1E12 1E+34 1E-56 1E12f 1E+34f 1E-56f 1E12d 1E+34d 1E-56d
0x // Can still be highlighted correctly!
0x1234567890ABCDEF
"test" "\"test\"" "'test'" // comment
"""test: one ", two "", three """""" // comment
't' '"' '\''

def abs[T](x: Int): Int = if x >= 0 then new x else now -x
def abs(x: Int) = if x >= 0 then new x else now -x
def sum[A](xs: List[A])(implicit m: Monoid[A]): A = ???
def sum[A](xs: List[A])(implicit Monoid[A]): A = ???
def sum[A](xs: List[A])(using m: Monoid[A]): A = ???
def sum[A](xs: List[A])(using Monoid[A]): A = ???


given Ord[Int] { 
  def compare(x: List[T], y: List[T]) = ??? 
}

given [T](using Ord[T]) as Ord[List[T]]:
  def compare(x: List[T], y: List[T]) = ??? 

trait Ord[T] {
  def compare(x: T, y: T): Int
  def (x: T) < (y: T) = compare(x, y) < 0
  def (x: T) > (y: T) = compare(x, y) > 0
}

given intOrd as Ord[Int] {
  def compare(x: Int, y: Int) =
    if (x < y) -1 else if (x > y) +1 else 0
}

given listOrd[T](using ord: Ord[T]) as Ord[List[T]] {

  def compare(xs: List[T], ys: List[T]): Int = (xs, ys) match
    case (Nil, Nil) => 0
    case (Nil, _) => -1
    case (_, Nil) => +1
    case (x :: xs1, y :: ys1) =>
      val fst = ord.compare(x, y)
      if (fst != 0) fst else compare(xs1, ys1)
}

class Rational(x: Int, y: Int):
  def numer = x
  def denom = y

class Sub extends Base:
  override def foo = 2
  def bar = 3

extension on (x: Rational):
  def < (y: Rational): Boolean = x.numer * y.denom < y.numer * x.denom
  def > (y: Rational): Boolean = y < x

extension stringOps on (ss: Seq[String]) {
def longestStrings: Seq[String] = {
  val maxLength = ss.map(_.length).max
  ss.filter(_.length == maxLength)
}
def longestString: String = longestStrings.head
}

extension listOps on [T](xs: List[T]) {
def second: T = xs.tail.head
def third: T = xs.tail.second
}

extension on [T](xs: List[T])(using Ordering[T]) {
def largest(n: Int) = xs.sorted.takeRight(n)
}

class Int:
  def + (that: Double): Double
  def + (that: Float): Float
  def + (that: Long): Long
  def + (that: Int): Int // same for -, *, /, %
  def << (cnt: Int): Int // same for >>, >>> */
  def & (that: Long): Long
  def & (that: Int): Int // same for |, ^ */
  def == (that: Double): Boolean
  def == (that: Float): Boolean
  def == (that: Long): Boolean // same for !=, <, >, <=, >=
  ...
end Int

object Zero extends Nat:
  ...

class Succ(n: Nat) extends Nat:
  ...

trait Color
object Red extends Color
object Green extends Color
object Blue extends Color
object Magenta extends Color

enum Color:
  case Red, Green, Blue, Magenta

enum Color(val test: Int):
  case Red, Green, Blue, Magenta

import Color._
def isPrimary(color: Color): Boolean =
  color match
      case Red | Green | Blue => true
      case Magenta => false

enum Vehicle(val numberOfWheels: Int) {
  case Unicycle extends Vehicle(1)
  case Bicycle extends Vehicle(2)
  case Car extends Vehicle(4)
}

abstract class Color
object Color {
  val Red = Color()
  val Green = Color()
  val Blue = Color()
  val Magenta = Color()
  ...
}

trait A:
  def f: Int

class C(x: Int) extends A:
  def f = x

object O:
  def f = 3

enum Color:
  case Red, Green, Blue

type T = A:
  def f: Int

extension on (xs: List[Int]):
  def second: Int = xs.tail.head

new A:
  def f = 3

package p:
  def a = 1

package q:
  def b = 2

extension stringOps {
  def (ss: Seq[String]).longestStrings: Seq[String] = {
    val maxLength = ss.map(_.length).max
    ss.filter(_.length == maxLength)
  }
  def (ss: Seq[String]).longestString: String =
    ss.longestStrings.head
}
extension listOps {
  def [T](xs: List[T]).second: T = xs.tail.head
  def [T](xs: List[T]).third: T = xs.tail.second
}
extension {
  def [T](xs: List[T]).largest(using Ordering[T])(n: Int) =
    xs.sorted.takeRight(n)
}

def f(x: Resettable & Growable[String]) = {
  x.reset()
  x.add("first")
}

opaque type Logarithm = Double

open class Writer[T] {

  /** Sends to stdout, can be overridden */
  def send(x: T) = println(x)

  /** Sends all arguments using `send` */
  def sendAll(xs: T*) = xs.foreach(send)
}

class Copier {
  private val printUnit = new Printer { type PrinterType = InkJet }
  private val scanUnit = new Scanner

  export scanUnit.scan
  export printUnit.{status => _, _}

  def status: List[String] = printUnit.status ++ scanUnit.status
}

enum Tree[T] derives Eq, Ordering, Show {
  case Branch[T](left: Tree[T], right: Tree[T])
  case Leaf[T](elem: T)
}
