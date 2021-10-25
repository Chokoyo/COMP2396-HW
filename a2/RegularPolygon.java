/**
 * This class the sub-class for Shape.
 * It inherits the default state and behaviour from shape class,
 * and specify the extra details of a regular polygon.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class RegularPolygon extends Shape {
    // Instance Variables
    private int numOfSides;
    private double radius;

    // Methods Declaration
    /**
     * This constructor takes numOfSides and Radius as the input and
     * will create a RegularPolygon instance with those values
     *
     * @param n number of sides
     * @param r radius of the polygon
     */
    public RegularPolygon(int n, double r) {
        numOfSides = Math.max(n, 3);
        radius = r;
        setVertices();
    }

    /**
     * This constructor takes numOfSides as the input and create
     * a RegularPolygon instance with the input and set the value
     * of the radius to 1.0 as default.
     *
     * @param n number of sides
     */
    public RegularPolygon(int n) {
        this(n, 1.0);
    }

    /**
     * This constructor take no arguments and create a RegularPolygon
     * instance with 3 numOfSides and 1.0 radius as default value.
     */
    public RegularPolygon() {
        this(3, 1.0);
    }

    /**
     * This method returns the value of the instance variable numOfSides.
     *
     * @return numOfSides of the shape
     */
    public int getNumOfSides() {
        return numOfSides;
    }

    /**
     * This method returns the value of the instance variable radius.
     * The radius refers to the distance from the centre to one of the
     * vertices of the regular polygon.
     *
     * @return radius of the shape
     */
    public double getRadius() {
        return radius;
    }

    /**
     * The method modify the value of the instance numOfSides
     * according to the input parameter.
     *
     * @param numOfSides of the polygon
     */
    public void setNumOfSides(int numOfSides) {
        this.numOfSides = Math.max(numOfSides, 3);
        setVertices();
    }

    /**
     * The method modify the value of the instance radius
     * according to the input parameter.
     * The radius refers to the distance from the centre to one of the
     * vertices of the regular polygon.
     *
     * @param radius of the polygon
     */
    public void setRadius(double radius) {
        this.radius = Math.max(radius, 0);
        setVertices();
    }

    private void setVertices() {
        final double alpha = (numOfSides % 2 != 0)? 0 : Math.PI / numOfSides;
        double[] xLocal = new double[numOfSides];
        double[] yLocal = new double[numOfSides];
        for (int i = 0; i < numOfSides; i++) {
            xLocal[i] = radius * Math.cos(alpha - i * 2 * Math.PI / numOfSides);
            yLocal[i] = radius * Math.sin(alpha - i * 2 * Math.PI / numOfSides);
        }
        setxLocal(xLocal);
        setyLocal(yLocal);
    }

    /**
     * This method return whether a point, given screen coordinates (x, y)
     * align in/on the RegularPolygon instance.
     *
     * @param x screen coordinate of the given point
     * @param y screen coordinate of the given point
     * @return a boolean value whether the point is in/on the polygon
     */
    public boolean contains(double x, double y) {
        double xL = (x-getXc()) * Math.cos(-getTheta()) - (y - getYc()) * Math.sin(-getTheta());
        double yL = (x-getXc()) * Math.sin(-getTheta()) + (y - getYc()) * Math.cos(-getTheta());
        double r = Math.sqrt(xL * xL + yL * yL); // distance of the point to centre of the shape
        double theta = Math.atan2(yL, xL); // the initial angle wrt the positive x-axis
        double xLeft = getXLocal()[numOfSides - numOfSides / 2]; // the left-most point of the polygon
        for (int i = 0; i < numOfSides; i++) {
            double xL_r = r * Math.cos(theta - i * 2 * Math.PI / numOfSides);
            if (xL_r < xLeft) { return false; }
        }
        return true;
    }
}
