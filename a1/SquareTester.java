import java.awt.Color;
import java.lang.Math;

/**
 * This is a tester class for the Square.
 * It will access all its instance variables and method,
 * and print the debugging messages to the console.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class SquareTester {
    public static void main(String[] args) {
        // Create an object of a class
        Square square = new Square();
        
        // Access all its instance variables and method
        square.color = Color.BLACK;
        square.filled = true;
        square.theta = 0;
        square.xLocal = new double[]{1, -1, -1, 1};
        square.xc = 5.0;
        square.yLocal = new double[]{-1, -1, 1, 1};
        square.yc = 5.0;
        
        square.setVertices(2);
        square.translate(1, 2);
        square.rotate(Math.PI);
        square.getX();
        square.getY();

        // Print out debugging messages to the console
        System.out.println("Color: " + square.color);
        System.out.println("Filled: " + square.filled);
        System.out.println("Theta: " + square.theta);
        System.out.print("xLocal: " + square.xLocal + " ");
        for (double x : square.xLocal) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("xc: " + square.xc);
        System.out.print("yLocal: " + square.yLocal + " ");
        for (double y : square.yLocal) {
            System.out.print(y + " ");
        }
        System.out.println();
        System.out.println("yc: " + square.yc);
    }

}
