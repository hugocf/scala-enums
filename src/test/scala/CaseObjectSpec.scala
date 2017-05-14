import caseobject.CustomEnum._
import caseobject.WeekDay
import caseobject.WeekDay._
import org.scalatest._

class CaseObjectSpec extends FreeSpec with Matchers {

  "Customizable: Support custom data fields other than “name” and “index”" in {
    Foo.custom shouldBe "This is a foo"
  }

  "Indexed: Items have a consecutive incremental numeric value" in {
    Foo.order shouldBe 1
    Bar.order shouldBe 2
  }

  "Indexed: Items can be retrieved via their index" in {
    CustomEnum.withOrder(0) shouldBe None
    CustomEnum.withOrder(1) shouldBe Some(Foo)
    CustomEnum.withOrder(2) shouldBe Some(Bar)
  }

  "Ordered: Enum items naturally ordered according to their index" in {
    Foo < Bar shouldBe true
  }

  "Iterable: Capable of iterating over the enum items as a collection" in {
    CustomEnum.all shouldBe Seq(Foo, Bar, Baz)
  }

  "Pattern Matching: Ability to do pattern matching on the items" in {
    val day: WeekDay = Sun  // Default inferred type: WeekDay.Sun.type

    val result = day match {
      case Sat | Sun => "weekend"
      case _ => "working day"
    }

    result shouldBe "weekend"
  }

  "Pattern Matching: Compile time warning about exhaustive pattern matching" in {
    val day: WeekDay = Mon  // Default inferred type: WeekDay.Sun.type

    // This code generates a warning:
    /*
    [warn] scala-enums/src/test/scala/CaseObjectSpec.scala:48: match may not be exhaustive.
    [warn] It would fail on the following inputs: Fri, Mon, Thu, Tue, Wed
    [warn]       day match {
    [warn]       ^
     */
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
    CustomEnum.withName("Foo").get.custom shouldBe "This is a foo"
    CustomEnum.withName("Bar") shouldBe Some(Bar)
    CustomEnum.withName("Oops") shouldBe None
  }

  "Types: Items are instances of the enumeration, not separate classes" in {
    Mon shouldBe a[WeekDay]
    Sun shouldBe a[WeekDay]
    // but, also...
    Mon shouldBe a[WeekDay.Mon.type]
    Sun shouldBe a[WeekDay.Sun.type]
  }

  def f(d: CustomEnum) = s"custom: $d"
  def f(d: WeekDay) = s"week: $d"

  "Types: Has distinct type after type erasure (i.e. can be used for method overloading)" in {
    f(Foo) shouldBe "custom: Foo"
    f(Mon) shouldBe "week: Mon"
  }

}
