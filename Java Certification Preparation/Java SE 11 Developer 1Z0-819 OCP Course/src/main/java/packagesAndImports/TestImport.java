package packagesAndImports;// A single-type-import declaration
import packagesAndImports.vegetable.VineVegetable;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 20/08/2021 at 10:29
 */
public class TestImport {

    public static void main(String[] args) {

        //String class in the java.lang package, simple class name is ok, no import statement needed
        final String message = "We are testing imports using FQCN";
        System.out.println(message);

        //We use simple name because the location is specified in the import statement
        VineVegetable.main(args);
    }
}
