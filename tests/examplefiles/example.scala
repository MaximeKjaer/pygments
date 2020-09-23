#!/usr/bin/scala

// TODO
import a.{given a}
s"$safeTagMarker${mtch.matched}$safeTagMarker"
val blue = '*
type X[Y] = Y => (1 | 2, 3)
type X[Y] = (Y, 3) => (1 | 2, 3)
Type[A with "user provided string" with B]
(using)
(using  )
(using , )
trait :: :
new A:

// Imports
import // This is incorrect Scala but can still be highlighted correctly
import a.{x => y} // Test comment
import a.{x => } // This is incorrect Scala but can still be highlighted correctly
import a.{x => `test-name`}
import a.given // This should not be highlighted as a keyword, given is only a soft keyword
import a.{given a}
import a.{x, y}
import a._
import a.x
import a.x.y.z
import java.io.{File, IOException, FileNotFoundException}
import java.io.File
import scala.math.{given Ordering[Int]}
import scala.math.{given Ordering[?]}

// Exports
export // This is incorrect Scala but can still be highlighted correctly
export a._
export a.x // Test comment
export a.x.y.z // Test comment
export a.{x, y}
export a.{x => y}
export a.{x => } // This is incorrect Scala but can still be highlighted correctly
export a.{x => `test-name`} // Test comment
export given
export given a // Test comment
export given a.x // Test comment
export given a._
export given a.{x, y} // Test comment
export given a.{x => y}
export given a.{x => `test-name`}
  export scanUnit.scan
  export printUnit.{status => _, _}

// Package declarations
package
package com
package com.example

// Literals
true false null
1 2 3 4
1L 1l 10L 12123123L
3.0 12.345
3f 3.0f 3F 3.0F
3d 3.0d 3D 3.0D
110_222_795_799.99 110.9499_999 2_000.343_999e561_100
1e12 1e+34 1e-56 1e12f 1e+34f 1e-56f 1e12d 1e+34d 1e-56d
1E12 1E+34 1E-56 1E12f 1E+34f 1E-56f 1E12d 1E+34d 1E-56d
.1e12 .1e+34 .1e-56 .1e12f .1e+34f .1e-56f .1e12d .1e+34d .1e-56d
.1E12 .1E+34 .1E-56 .1E12f .1E+34f .1E-56f .1E12d .1E+34d .1E-56d
0x // Can still be highlighted correctly!
0x1234567890ABCDEF 0x1234567890abcdef
0x123_abc 0x123_ABC
"test" "\"test\"" "'test'" // comment
"""test: one ", two "", three """""" // comment
't' '"' '\'' '\n' ' '

// String interpolation
s"1 + 2 = ${ 1 + { val x = 2; x } }."
s"""1 + 2 = ${
  def add(x: Int, y: Int) = {
    x + y
  }
  add(1, 2)
}."""
s"$first$second"
s"$safeTagMarker${mtch.matched}$safeTagMarker"
s"${x$}"
val a = 4; foo(a)
s"$safeTagMarker${val a = 4; foo(a)}$safeTagMarker"

// Vals & vars
val x: Int
val y: Int = 1
val z = 1
var x: Int
var y: Int = 1
var z = 1
val (a, b) = (1, 2)
val Some(a) = Some(1, 2)
var Pair(a, b) = Pair(1, 2)
val Test.Pair(a) = Test.Pair(1, 2)

// Defs
def abs[T](x: Int): Int = if x >= 0 then new x else now -x
def abs(x: Int) = if x >= 0 then new x else now -x
def sum[A](xs: List[A])(implicit m: Monoid[A]): A = ???
def sum[A](xs: List[A])(implicit Monoid[A]): A = ???
def sum[A](xs: List[A])(using m: Monoid[A]): A = ???
def sum[A](xs: List[A])(using Monoid[A]): A = ???
def reduceRight(op: (T, T) => T): T = ???
def foldRight[](z: U)(op: (T, U) => U): U = ???
def obj(fields: (String, Any)*, test: String): Json

// Using
def f(using x: Int): Unit = ()
f(using 2)
f(using .2)
class A(using x: Int)
new A(using 3)
given [T](using x: Ord[T], using: Int) as Ord[List[T]]
given [T](using Ord[T]) as Ord[List[T]]
f(using ())
f(using {})
f(using ' ')
f(using "")

// Declarations
trait X {}
object X
class Y
open object X:
open class Y:
case object X
case class Y()
package object x {}
package object y:

// Quoted
'{ 2 }
'[ String ]

// Symbols
object Unicode {
    val blue = '* //red
    val stillRed = '*
    val invalidSymbol  = '**_x //'
    val symbolFollowedByOp = 'symbol*
    val symbolEndedWithOp  = 'symbol_*
    val unclosedSymbol = '1 //'
    val symbolWithDigit = 'symbol1 //'
    val characterLit = 'x'
    val greekSymbol = 'ξφδ
    val greekSymbolDigit = 'φδφ0
    val greekSymbolWithOp = 'δφξφξ_+-
    val multiOpSymbol = '***
}

// Type aliases
type X
type X <: Y
type X = Y
type X[Y] = Y with Z
type X[Y] = Y => (1 | 2, 3)
type X[Y] = (Y, 3) => (1 | 2, 3)
type Foo = Bar.Baz
opaque type Logarithm = Double

// Type lambda
[X, Y] =>> Map[Y, X]

// Match types
type Elem[X] = X match {
  case String => Char
  case Array[t] => t
  case Iterable[t] => t
}
type Concat[Xs <: Tuple, +Ys <: Tuple] <: Tuple = Xs match {
  case Unit => Ys
  case x *: xs => x *: Concat[xs, Ys]
}

// Dependent function types
trait Entry { type Key; val key: Key }
def extractKey(e: Entry): e.Key = e.key
val extractor: (e: Entry) => e.Key = extractKey
type Extractor = Function1[Entry, Entry#Key] {
  def apply(e: Entry): e.Key
}

// Singleton types
val x = ???
trait Foo[T <: x.type]
val a: x.type = ???
val b: Foo[x.type] = ???

// Union and intersection types
Type[A with "user provided string" with B]
def help(id: UserName | Password) = ???
val either: Password | UserName = ???
val both: Object & Product = ???

// Soft keywords (should not be highlighted as keywords here)
val open = true
val inline = true
(using)
(using  )
(using , )
(usingSomething)

// Storage modifiers
private object a {}
private[com] object b {}
private[com.example] object c {}
protected object d {}
protected[com] object e {}
protected[com.example] object f {}
synchronized {}
abstract class g {}
final val h = ???
lazy val i = ???
sealed trait j
implicit val k = ???
enum m {}
inline val n = ???
opaque type o = Unit
@volatile @transient @native
override def p = ???

// Meta bounds
<% =:= <:< <%< >: <:

// Given
given Foo = ???
given foo = ???
given as Foo = ???
given as foo = ???
given bar as foo = ???
given Foo as foo = ???
given (x: X) as Foo = ???
given [X](x: X) as Foo = ???
given foo(x: X) as Foo = ???
given foo[X](x: X) as Foo = ???
given foo[X <: Y { type A = 1; def f(using a: Int): 2 }](x: X = 2) as Foo = ???
given (using x: X = "abs")(using y: Y = s"y: $x", y: Char = if true then 'a' else 2) as Foo = ???
given Ord[Int] { 
  def compare(x: List[T], y: List[T]) = ??? 
}
given Ordering[Int]:
  def compare(x: Int, y: Int): Int = ???
given [T](using Ord[T]) as Ord[List[T]]:
  def compare(x: List[T], y: List[T]) = ??? 

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


// Classes
class Bar :
class Foo:
class ::
class Rational(x: Int, y: Int):
  def numer = x
  def denom = y
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
end Int
class Sub extends Base with Something {
  override def foo = 2
  def bar = 3
}
class Succ(n: Nat) extends Nat:
  // ...
open class Writer[T] {
  /** Sends to stdout, can be overridden */
  def send(x: T) = println(x)
  /** Sends all arguments using `send` */
  def sendAll(xs: T*) = xs.foreach(send)
}

// Traits
trait Foo:
trait Bar :
trait *:
trait *: :
trait :: :
1 :: Nil
1 ::

// Objects
object Foo:
object Bar :
object Zero extends Nat:
  ...

// Enums
object Enum extends Enumeration {
  val Foo, Bar, Baz = Value
}
enum Color:
  case Red, Green, Blue, Magenta
enum Color(val test: Int):
  case Red, Green, Blue, Magenta
  def isPrimary(color: Color): Boolean =
    color match
        case Red | Green | Blue => true
        case Magenta => false
abstract class Color
object Color {
  val Red = Color()
  val Green = Color()
  val Blue = Color()
  val Magenta = Color()
  ...
}
enum Vehicle(val numberOfWheels: Int) {
  case Unicycle extends Vehicle(1)
  case Bicycle extends Vehicle(2)
  case Car extends Vehicle(4)
}
enum Vehicle(val numberOfWheels: Int):
  case Unicycle extends Vehicle(1)
  case Bicycle extends Vehicle(2)
  case Car extends Vehicle(4)

// New
new A
new { }
new Foo
new foo.Foo
new Foo.Foo
new A:
  def f = 3

// End
new Foo:
  // ...
end new
end extension
end if
end while
end for
end match
class Foo
end Foo
end bar
end `bar`
end // test comment
package p1.p2:
  abstract class C():
    def this(x: Int) =
      this()
      if x > 0 then
        val a :: b =
          x :: Nil
        end val // test comment
        var y =
          x
        end y // test comment
        while y > 0 do
          println(y)
          y -= 1
        end while // test comment
        try
          x match
            case 0 => println("0")
            case _ =>
          end match // test comment
        finally
          println("done")
        end try // test comment
      end if // test comment
    end this // test comment
    def f: String
  end C // test comment
  object C:
    given C =
      new C:
        def f = "!"
        end f // test comment
      end new // test comment
    end given // test comment
  end C // test comment
  extension (x: C)
    def ff: String = x.f ++ x.f
  end extension // test comment
end p2 // test comment

// Extension methods
extension on (x: Rational):
  def > (y: Rational): Boolean = y < x
extension Ops on (x: Rational):
  def > (y: Rational): Boolean = y < x
extension stringOps {  }
extension {  }
extension (x: T) def combine (y: T): T
extension [T](x: T) def combine (y: T): T

// Extends
trait A extends B
trait A extends (B => B){}
trait Color
object Red extends Color

// Derives
enum Tree[T] derives Eq, Ordering, Show {
  case Branch[T](left: Tree[T], right: Tree[T])
  case Leaf[T](elem: T)
}

