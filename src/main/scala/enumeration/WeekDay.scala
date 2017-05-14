package enumeration

object WeekDay extends Enumeration(initial = 1) {
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}
