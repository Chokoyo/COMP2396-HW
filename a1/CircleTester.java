import java.awt.Color;
import java.lang.Math;

/**
 * This is a tester class for the Circle.
 * It will access all its instance variables and method,
 * and print the debugging messages to the console.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class CircleTester {
    public static void main(String[] args) {
        // Create an object of a class
        Circle circle = new Circle();
        
        // Access all its instance variables and method
        circle.color = Color.BLACK;
        circle.filled = true;
        circle.theta = 0;
        circle.xLocal = new double[]{1, -1};
        circle.xc = 5.0;
        circle.yLocal = new double[]{1, -1};
        circle.yc = 5.0;
        
        circle.setVertices(2);
        circle.translate(1, 2);
        circle.rotate(Math.PI);
        circle.getX();
        circle.getY();

        // Print out debugging messages to the console
        System.out.println("Color: " + circle.color);
        System.out.println("Filled: " + circle.filled);
        System.out.println("Theta: " + circle.theta);
        System.out.print("xLocal: " + circle.xLocal + " ");
        for (double x : circle.xLocal) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("xc: " + circle.xc);
        System.out.print("yLocal: " + circle.yLocal + " ");
        for (double y : circle.yLocal) {
            System.out.print(y + " ");
        }
        System.out.println();
        System.out.println("yc: " + circle.yc);
    }

}
