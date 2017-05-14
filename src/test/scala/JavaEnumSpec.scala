import javaenum.CustomEnum._
import javaenum.WeekDay._
import javaenum.{CustomEnum, WeekDay}

import org.scalatest._

class JavaEnumSpec extends FreeSpec with Matchers {

  "Customizable: Support custom data fields other than “name” and “index”" in {
    FOO.custom shouldBe "This is a foo"
  }

  "Indexed: Items have a consecutive incremental numeric value" in {
    MON.ordinal shouldBe 0
    SUN.ordinal shouldBe 6
  }

  "Indexed: Items can be retrieved via their index" in {
    val values: Array[WeekDay] = WeekDay.values()  // every call returns a newly cloned array
    values(0) shouldBe MON
    values(6) shouldBe SUN
    the [ArrayIndexOutOfBoundsException] thrownBy values(7) should have message "7"
  }

  "Ordered: Enum items naturally ordered according to their index" in {
    MON.ordinal < TUE.ordinal shouldBe true
    MON.compareTo(TUE) should be < 0
  }

  "Iterable: Capable of iterating over the enum items as a collection" in {
    WeekDay.values shouldBe Array(MON, TUE, WED, THU, FRI, SAT, SUN)
  }

  "Pattern Matching: Ability to do pattern matching on the items" in {
    val day = SUN   // Default inferred type: WeekDay

    val result = day match {
      case SAT | SUN => "weekend"
      case _ => "working day"
    }

    result shouldBe "weekend"
  }

  "Pattern Matching: Compile time warning about exhaustive pattern matching" in {
    val day = MON   // Default inferred type: WeekDay

    // This code generates a warning:
    /*
    [warn] scala-enums/src/test/scala/JavaEnumSpec.scala:50: match may not be exhaustive.
    [warn] It would fail on the following inputs: FRI, MON, THU, TUE, WED
    [warn]       day match {
    [warn]       ^
     */
    a[MatchError] shouldBe thrownBy {
      day match {
        case SAT | SUN => "weekend"
      }
    }
  }

  "Serialization: Each enum item has an associated “name” value" in {
    MON.toString shouldBe "MON"
    SUN.toString shouldBe "SUN"
  }

  "Serialization: Instantiate an enum item from a “name” String" in {
    CustomEnum.valueOf("FOO").custom shouldBe "This is a foo"

    WeekDay.valueOf("MON") shouldBe MON
    the[IllegalArgumentException] thrownBy WeekDay.valueOf("Oops") should have message "No enum constant javaenum.WeekDay.Oops"
  }

  "Types: Items are instances of the enumeration, not separate classes" in {
    MON shouldBe a[WeekDay]
    SUN shouldBe a[WeekDay]
  }

  def f(d: CustomEnum) = s"custom: $d"
  def f(d: WeekDay) = s"week: $d"

  "Types: Has distinct type after type erasure (i.e. can be used for method overloading)" in {
    f(FOO) shouldBe "custom: FOO"
    f(MON) shouldBe "week: MON"
  }

}
