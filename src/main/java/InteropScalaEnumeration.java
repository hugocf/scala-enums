import enumeration.CustomEnum;
import enumeration.WeekDay;
import scala.Enumeration;

/** Example usages of Scala `Enumeration` in Java code */
public class InteropScalaEnumeration {
    public static void main(String[] args) {
        /* Customizable: Support custom data fields other than “name” and “index” */
        /*
        String a = CustomEnum.Foo().custom();   // Error: `CustomEnumVal` must be public for this to work
        System.out.println("a = " + a);
        */

        /* Indexed: Items have a consecutive incremental numeric value */
        int b = WeekDay.Mon().id();
        System.out.println("b = " + b);

        /* Indexed: Items can be retrieved via their index */
        Enumeration.Value c = WeekDay.apply(1);
        System.out.println("c = " + c);

        /* Ordered: Enum items naturally ordered according to their index */
        boolean d = WeekDay.Mon().compare(WeekDay.Tue()) < 0;
        System.out.println("d = " + d);

        /* Iterable: Capable of iterating over the enum items as a collection */
        Enumeration.ValueSet e = WeekDay.values();
        System.out.println("e = " + e);

        /* Pattern Matching: Ability to do pattern matching on the items */
        /*
        String f;
        Enumeration.Value day = WeekDay.Sun();
        switch (day) {
            case WeekDay.Sat():     // Error: Constant expression required
            case WeekDay.Sun():     // Error: Constant expression required
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
        String h = WeekDay.Mon().toString();
        System.out.println("h = " + h);

        /* Serialization: Instantiate an enum item from a “name” String */
        Enumeration.Value i = WeekDay.withName("Mon");
        System.out.println("i = " + i);

        /* Types: Items are instances of the enumeration, not separate classes */
        String j = WeekDay.Mon().getClass().getCanonicalName();
        System.out.println("j = " + j);

        /* Types: Has distinct type after type erasure (i.e. can be used for method overloading) */
        /*
        String k1 = f(CustomEnum.Foo());        // Error: Cannot resolve method
        String k2 = f(WeekDay.Mon());           // Error: Cannot resolve method
        System.out.println("k1 = " + k1);
        System.out.println("k2 = " + k2);
        */
    }

    private static String f(CustomEnum d) {
        return "custom: " + d;
    }

    private static String f(WeekDay d) {
        return "custom: " + d;
    }
}
