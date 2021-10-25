import java.awt.Color;
import java.lang.Math;

/**
 * This class the the super class for Square, Circle, and Triangle.
 * It includes the default state and behaviour for any shape object.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class Shape {
    // Instance Variables
    /**
     * color variable stores the color info of the shape.
     *
     * @see Color
     */
    public Color color;
    /**
     * filled variable represents the filled status of the shape
     */
    public boolean filled;
    /**
     * theta variable stores the rotation angle.
     */
    public double theta;
    /**
     * xc is the central x-coordinate of the shape
     */
    public double xc;
    /**
     * yc is the central y-coordinate of the shape
     */
    public double yc;
    /**
     * xLocal stores a set of x-coordinates at the vertices of the shape
     */
    public double[] xLocal;
    /**
     * yLocal stores a set of y-coordinates at the vertices of the shape
     */
    public double[] yLocal;

    // Methods Declaration
    /**
     * This is a dummy method, always override by sub-classes.
     *
     * @param d The value of vertices.
     */
    public void setVertices(double d) {}

    /**
     * This method translate the shape according to the user input.
     *
     * @param dx The x-coordinate value need to be translated.
     * @param dy The y-coordinate value need to be translated.
     */
    public void translate(double dx, double dy) {
        xc += dx;
        yc += dy;
    }

    /**
     * This method rotate the shape according to the user input angle.
     *
     * @param dt The angle needed to be rotate in radian.
     */
    public void rotate(double dt) {
        theta += dt;
    }

    /**
     * This method retrieve the x-coordinates of the vertices
     * of the shape in a anti-clockwise order.
     *
     * @return rounded x-coordinates of vertices
     */
    public int[] getX() {
        int[] res = new int[xLocal.length];
        for (int i = 0; i < xLocal.length; i++) { // traverse the xLocal array
            res[i] = (int) Math.round(xLocal[i] * Math.cos(theta) - yLocal[i] * Math.sin(theta) + xc);
        }
        return res;
    }

    /**
     * This method retrieve the y-coordinates of the vertices
     * of the shape in a anti-clockwise order
     *
     * @return rounded y-coordinates of vertices.
     */
    public int[] getY() {
        int[] res = new int[yLocal.length];
        for (int i = 0; i < yLocal.length; i++) { // traverse the yLocal array
            res[i] = (int) Math.round(xLocal[i] * Math.sin(theta) + yLocal[i] * Math.cos(theta) + yc);
        }
        return res;
    }
}