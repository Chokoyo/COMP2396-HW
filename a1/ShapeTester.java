import java.awt.Color;
import java.lang.Math;

/**
 * This is a tester class for the Shape.
 * It will access all its instance variables and method,
 * and print the debugging messages to the console.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class ShapeTester {
    public static void main(String[] args) {
        // Create an object of a class
        Shape shape = new Shape();
        
        // Access all its instance variables and method
        shape.color = Color.BLACK;
        shape.filled = true;
        shape.theta = 0;
        shape.xLocal = new double[]{1, 2, 3, 4};
        shape.xc = 5.0;
        shape.yLocal = new double[]{-4, -3, -2, -1};
        shape.yc = 5.0;
        
        shape.setVertices(1);
        shape.translate(1, 2);
        shape.rotate(Math.PI);
        shape.getX();
        shape.getY();

        // Print out debugging messages to the console
        System.out.println("Color: " + shape.color);
        System.out.println("Filled: " + shape.filled);
        System.out.println("Theta: " + shape.theta);
        System.out.print("xLocal: " + shape.xLocal + " ");
        for (double x : shape.xLocal) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("xc: " + shape.xc);
        System.out.print("yLocal: " + shape.yLocal + " ");
        for (double y : shape.yLocal) {
            System.out.print(y + " ");
        }
        System.out.println();
        System.out.println("yc: " + shape.yc);
    }

}
