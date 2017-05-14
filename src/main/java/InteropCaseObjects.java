import caseobject.CustomEnum;
import caseobject.WeekDay;
import scala.Option;
import scala.collection.Seq;

/** Example usages of Scala `case objects` in Java code */
public class InteropCaseObjects {
    public static void main(String[] args) {
        /* Customizable: Support custom data fields other than “name” and “index” */
        /*
        String a = CustomEnum.Foo.custom();     // Error: Cannot find symbol
        System.out.println("a = " + a);
        */

        /* Indexed: Items have a consecutive incremental numeric value */
        /*
        int b = CustomEnum.Foo.order();         // Error: Cannot find symbol
        System.out.println("b = " + b);
        */

        /* Indexed: Items can be retrieved via their index */
        /*
        Option<CustomEnum.CustomEnum> c = CustomEnum.CustomEnum.withOrder(0);   // Error: Cannot find symbol
        System.out.println("c = " + c);
        */

        /* Ordered: Enum items naturally ordered according to their index */
        /*
        boolean d = Foo.compareTo(Bar) < 0;     // Error: Expression expected
        System.out.println("d = " + d);
        */

        /* Iterable: Capable of iterating over the enum items as a collection */
        /*
        Seq<CustomEnum.CustomEnum> e = CustomEnum.CustomEnum.all();     // Error: Cannot find symbol
        System.out.println("e = " + e);
        */

        /* Pattern Matching: Ability to do pattern matching on the items */
        /*
        String f;
        WeekDay day = Sun;      // Error: Expression expected
        switch (day) {
            case Sat:           // Error: Constant expression required
            case Sun:           // Error: Constant expression required
                f = "weekend";
                break;
            default:
                f = "working day";
        }
        System.out.println("f = " + f);
        */

        /* Pattern Matching: Compile time warning about exhaustive pattern matching */
        // N/A (see above)
        // System.out.println("g = " + g);

        /* Serialization: Each enum item has an associated “name” value */
        /*
        String h = WeekDay.Mon.class.getName(); // Error: Cannot find symbol
        System.out.println("h = " + h);
        */

        /* Serialization: Instantiate an enum item from a “name” String */
        /*
        CustomEnum.CustomEnum i = CustomEnum.CustomEnum.withName("Foo").get();  // Error: Cannot find symbol
        System.out.println("i = " + i);
        */

        /* Types: Items are instances of the enumeration, not separate classes */
        /*
        String j = WeekDay.Mon.class.getSimpleName();    // Error: Cannot find symbol
        System.out.println("j = " + j);
        */

        /* Types: Has distinct type after type erasure (i.e. can be used for method overloading) */
        /*
        String k1 = f(CustomEnum.Foo);          // Error: Expression expected
        String k2 = f(WeekDay.Mon);             // Error: Expression expected
        System.out.println("k1 = " + k1);
        System.out.println("k2 = " + k2);
        */
    }

    private static String f(CustomEnum.CustomEnum d) {
        return "custom: " + d;
    }

    private static String f(WeekDay.WeekDay d) {
        return "custom: " + d;
    }
}
