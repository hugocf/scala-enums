package enumeration

import scala.language.implicitConversions

object CustomEnum extends Enumeration {
  type CustomEnum = Value
  val Foo = CustomEnumVal("This is a foo")
  val Bar = CustomEnumVal("This is a bar")
  val Baz = CustomEnumVal("This is a baz")

  protected case class CustomEnumVal(custom: String) extends Val
  implicit def convert(value: Value): CustomEnumVal = value.asInstanceOf[CustomEnumVal]
}
