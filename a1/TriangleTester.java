import java.awt.Color;
import java.lang.Math;

/**
 * This is a tester class for the Triangle.
 * It will access all its instance variables and method,
 * and print the debugging messages to the console.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class TriangleTester {
    public static void main(String[] args) {
        // Create an object of a class
        Triangle triangle = new Triangle();
        
        // Access all its instance variables and method
        triangle.color = Color.BLACK;
        triangle.filled = true;
        triangle.theta = 0;
        triangle.xLocal = new double[]{0, 1, -1};
        triangle.xc = 5.0;
        triangle.yLocal = new double[]{1, -1, -1};
        triangle.yc = 5.0;
        
        triangle.setVertices(1);
        triangle.translate(1, 2);
        triangle.rotate(Math.PI);
        triangle.getX();
        triangle.getY();

        // Print out debugging messages to the console
        System.out.println("Color: " + triangle.color);
        System.out.println("Filled: " + triangle.filled);
        System.out.println("Theta: " + triangle.theta);
        System.out.print("xLocal: " + triangle.xLocal + " ");
        for (double x : triangle.xLocal) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("xc: " + triangle.xc);
        System.out.print("yLocal: " + triangle.yLocal + " ");
        for (double y : triangle.yLocal) {
            System.out.print(y + " ");
        }
        System.out.println();
        System.out.println("yc: " + triangle.yc);
    }

}
