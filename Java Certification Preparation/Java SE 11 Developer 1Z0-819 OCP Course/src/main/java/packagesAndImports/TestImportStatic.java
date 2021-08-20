package packagesAndImports;

// A single-static-import declaration for static variable
import static java.lang.Math.PI;

// A single-static-import declaration for static method
import static java.lang.Math.sqrt;

//// A static-import on demand declaration for static variable
//import static java.lang.Math.*;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 20/08/2021 at 12:08
 */

public class TestImportStatic {

    public static void main(String[] args) {
        int radius = 2;

        // We can use a static class variable on the Math class get the value of PI
        final double circumference = 2 * PI * radius;
        System.out.println("circumference = " + circumference);

        // We can use a static class method on the Math class to get the square root of a number
        final double sqrt81 = sqrt(81);
        System.out.println("sqrt81 = " + sqrt81);
    }
}
