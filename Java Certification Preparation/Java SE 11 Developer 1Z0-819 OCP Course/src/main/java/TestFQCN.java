/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 20/08/2021 at 10:27
 */
public class TestFQCN {

    public static void main(String[] args) {

        //String class in the java.lang package, simple class name is ok
        final String message = "We are testing imports using FQCN";
        System.out.println(message);

        //Any class in other packages must be qualified or imported
        garden.vegetable.VineVegetable.main(args);
    }
}
