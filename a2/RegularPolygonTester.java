import java.awt.*;

/**
 * This is a tester class for the RegularPolygon.
 * It will access all its instance variables and method,
 * and print the debugging messages to the console.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class RegularPolygonTester {
    public static void main(String[] args) {
        // Create objects of a class
        RegularPolygon rp1 = new RegularPolygon();
        RegularPolygon rp2 = new RegularPolygon(5);
        RegularPolygon rp3 = new RegularPolygon(1,30);

        // Access all its public methods
        rp3.setColor(Color.BLACK);
        rp3.setFilled(true);
        rp3.setTheta(0);
        rp3.setxLocal(new double[]{1, 2, 3, 4});
        rp3.setyLocal(new double[]{-4, -3, -2, -1});
        rp3.setXc(5.0);
        rp3.setYc(5.0);
        rp3.setRadius(50);
        rp3.setNumOfSides(-1);

        rp3.translate(1, 2);
        rp3.rotate(Math.PI);
        rp3.getX();
        rp3.getY();
        rp3.contains(120, 50);

        // Print out debugging messages to the console
        System.out.println("Color: " + rp3.getColor());
        System.out.println("Filled: " + rp3.getFilled());
        System.out.println("Theta: " + rp3.getTheta());
        System.out.print("xLocal: " + rp3.getXLocal() + " ");
        for (double x : rp3.getXLocal()) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println("xc: " + rp3.getXc());
        System.out.print("yLocal: " + rp3.getYLocal() + " ");
        for (double y : rp3.getYLocal()) {
            System.out.print(y + " ");
        }
        System.out.println();
        System.out.println("yc: " + rp3.getYc());
        System.out.println("radius: " + rp3.getRadius());
        System.out.println("numOfSides: " + rp3.getNumOfSides());
    }
}
