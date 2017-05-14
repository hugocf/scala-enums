package caseobject

object CustomEnum {
  sealed abstract class CustomEnum(val order: Int, val custom: String) extends Ordered[CustomEnum] {
    override def compare(that: CustomEnum): Int = this.order - that.order
  }
  case object Foo extends CustomEnum(1, "This is a foo")
  case object Bar extends CustomEnum(2, "This is a bar")
  case object Baz extends CustomEnum(3, "This is a baz")

  object CustomEnum {
    val all = Seq(Foo, Bar, Baz)

    def withOrder(o: Int): Option[CustomEnum] = all.find(_.order == o)

    def withName(n: String): Option[CustomEnum] = all.find(_.toString == n)
  }
}
