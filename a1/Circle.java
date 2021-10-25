/**
 * This class the the sub-class for Shape.
 * It includes the unique state and behaviour for circle object.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class Circle extends Shape {
    // Instance Variables and Methods inherit from Shape class

    /**
     * This method set the x and y-coordinates for circle.
     * It override the setVertices method in Shape Class.
     *
     * @param d The radius of the circle.
     */
    public void setVertices(double d) {
        xLocal = new double[]{d, -d};
        yLocal = new double[]{d, -d};
    }

    /**
     * This is a method to retrieve the x-coordinates of
     * the upper left point and the lower right point of the
     * outbound square of the circle.
     *
     * @return rounded x-coordinates.
     */
    public int[] getX() {
        int[] res = new int[2];
        res[0] = (int) Math.round(xLocal[1] + xc);
        res[1] = (int) Math.round(xLocal[0] + xc);
        return res;
    }

    /**
     * This is a method to retrieve the y-coordinates of
     * the upper left point and the lower right point of the
     * outbound square of the circle.
     *
     * @return rounded y-coordinates.
     */
    public int[] getY() {
        int[] res = new int[2];
        res[0] = (int) Math.round(yLocal[1] + yc);
        res[1] = (int) Math.round(yLocal[0] + yc);
        return res;
    }
    
}