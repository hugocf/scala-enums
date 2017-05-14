import enumeration.CustomEnum.Foo
import enumeration.WeekDay._
import enumeration.{CustomEnum, WeekDay}
import org.scalatest._

class ScalaEnumerationSpec extends FreeSpec with Matchers {

  "Customizable: Support custom data fields other than “name” and “index”" in {
    Foo.custom shouldBe "This is a foo"
  }

  "Indexed: Items have a consecutive incremental numeric value" in {
    Mon.id shouldBe 1
    Sun.id shouldBe 7
  }

  "Indexed: Items can be retrieved via their index" in {
    the [NoSuchElementException] thrownBy WeekDay(0) should have message "key not found: 0"
    WeekDay(1) shouldBe Mon
    WeekDay(7) shouldBe Sun
  }

  "Ordered: Enum items naturally ordered according to their index" in {
    Mon < Tue shouldBe true
  }

  "Iterable: Capable of iterating over the enum items as a collection" in {
    WeekDay.values shouldBe ValueSet(Mon, Tue, Wed, Thu, Fri, Sat, Sun)
  }

  "Pattern Matching: Ability to do pattern matching on the items" in {
    val day = Sun   // Default inferred type: WeekDay.Value

    val result = day match {
      case Sat | Sun => "weekend"
      case _ => "working day"
    }

    result shouldBe "weekend"
  }

  "Pattern Matching: Compile time warning about exhaustive pattern matching" in {
    val day = Mon   // Default inferred type: WeekDay.Value

    // This code generates no warning!
    a[MatchError] shouldBe thrownBy {
      day match {
        case Sat | Sun => "weekend"
      }
    }
  }

  "Serialization: Each enum item has an associated “name” value" in {
    Mon.toString shouldBe "Mon"
    Sun.toString shouldBe "Sun"
  }

  "Serialization: Instantiate an enum item from a “name” String" in {
    CustomEnum.withName("Foo").custom shouldBe "This is a foo"

    WeekDay.withName("Mon") shouldBe Mon
    the[NoSuchElementException] thrownBy WeekDay.withName("Oops") should have message "No value found for 'Oops'"
  }

  "Types: Items are instances of the enumeration, not separate classes" in {
    Mon shouldBe a[WeekDay.Value]
    Sun shouldBe a[WeekDay.Value]
  }

  "Types: Has distinct type after type erasure (i.e. can be used for method overloading)" in {
    /*
    [error] scala-enums/src/test/scala/ScalaEnumerationSpec.scala:74: double definition:
    [error] def f(d: enumeration.CustomEnum.Value): String at line 73 and
    [error] def f(d: enumeration.WeekDay.Value): String at line 74
    [error] have same type after erasure: (d: Enumeration#Value)String
    [error]   def f(d: enumeration.WeekDay.Value) = s"week $d"
    [error]       ^
     */
    """
    def f(d: enumeration.CustomEnum.Value) = s"custom: $d"
    def f(d: enumeration.WeekDay.Value) = s"week: $d"
    """ shouldNot typeCheck
  }

}
