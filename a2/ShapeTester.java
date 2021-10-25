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
        
        // Access all its public methods
        shape.setColor(Color.BLACK);
        shape.setFilled(true);
        shape.setTheta(0);
        shape.setxLocal(new double[]{1, 2, 3, 4});
        shape.setyLocal(new double[]{-4, -3, -2, -1});
        shape.setXc(5.0);
        shape.setYc(5.0);

        shape.translate(1, 2);
        shape.rotate(Math.PI);
        shape.getX();
        shape.getY();

        // Print out debugging messages to the console
        System.out.println("Color: " + shape.getColor());
        System.out.println("Filled: " + shape.getFilled());
        System.out.println("Theta: " + shape.getTheta());
        System.out.print("xLocal: " + shape.getXLocal() + " ");
        for (double x : shape.getXLocal()) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("xc: " + shape.getXc());
        System.out.print("yLocal: " + shape.getYLocal() + " ");
        for (double y : shape.getYLocal()) {
            System.out.print(y + " ");
        }
        System.out.println();
        System.out.println("yc: " + shape.getYc());
    }

}
