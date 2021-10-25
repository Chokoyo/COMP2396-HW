import java.awt.Color;
import java.lang.Math;

/**
 * This class the super class for RegularPolygon.
 * It includes the default state and behaviour for any shape object.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class Shape {
    // Instance Variables
    private Color color;
    private boolean filled;
    private double theta;
    private double xc;
    private double yc;
    private double[] xLocal;
    private double[] yLocal;

    // Methods Declaration
    /**
     * This method returns the value of the instance variable color.
     *
     * @return color of the shape
     */
    public Color getColor() {
        return color;
    }

    /**
     * This method returns the value of the instance variable filled.
     *
     * @return filled status of the shape
     */
    public boolean getFilled() {
        return filled;
    }

    /**
     * This method returns the value of the instance variable theta.
     *
     * @return theta, the rotation angle of the shape
     */
    public double getTheta() {
        return theta;
    }

    /**
     * This method returns the value of the instance variable xc.
     *
     * @return x-coordinate of the centre of the shape
     */
    public double getXc() {
        return xc;
    }

    /**
     * This method returns the value of the instance variable yc.
     *
     * @return y-coordinate of the centre of the shape
     */
    public double getYc() {
        return yc;
    }

    /**
     * This method retrieve the x-coordinates of the vertices
     * of the shape in an anti-clockwise order.
     *
     * @return x-coordinates of vertices
     */
    public double[] getXLocal() {
        return xLocal;
    }

    /**
     * This method retrieve the y-coordinates of the vertices
     * of the shape in an anti-clockwise order.
     *
     * @return y-coordinates of vertices
     */
    public double[] getYLocal() {
        return yLocal;
    }

    /**
     * The method modify the value of the instance variable
     * color according to the input parameter.
     *
     * @param color value of the shape need to assign
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The method modify the value of the instance variable
     * filled according to the input parameter.
     *
     * @param filled value of the shape need to assign
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * The method modify the value of the instance variable
     * theta according to the input parameter.
     *
     * @param theta the new rotation angle of the shape
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }

    /**
     * The method modify the value of the instance variable
     * xc according to the input parameter.
     *
     * @param xc the new x-coordinate of the centre of the shape
     */
    public void setXc(double xc) {
        this.xc = xc;
    }

    /**
     * The method modify the value of the instance variable
     * yc according to the input parameter.
     *
     * @param yc the new y-coordinate of the centre of the shape
     */
    public void setYc(double yc) {
        this.yc = yc;
    }

    /**
     * The method modify the value of the instance variable
     * xLocal array according to the input parameter.
     *
     * @param xLocal array contains the new x-coordinates of vertices
     */
    public void setxLocal(double[] xLocal) {
        this.xLocal = xLocal;
    }

    /**
     * The method modify the value of the instance variable
     * yLocal array according to the input parameter.
     *
     * @param yLocal array contains the new y-coordinates of vertices
     */
    public void setyLocal(double[] yLocal) {
        this.yLocal = yLocal;
    }

    /**
     * This method translate the shape according to the user input.
     *
     * @param dx The x-coordinate value need to be translated
     * @param dy The y-coordinate value need to be translated
     */
    public void translate(double dx, double dy) {
        xc += dx;
        yc += dy;
    }

    /**
     * This method rotate the shape according to the user input angle.
     *
     * @param dt The angle needed to be rotated in radian
     */
    public void rotate(double dt) {
        theta += dt;
    }

    /**
     * This method retrieve the x-coordinates of the vertices
     * of the shape in an anti-clockwise order.
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
     * of the shape in an anti-clockwise order
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