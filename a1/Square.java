/**
 * This class the the sub-class for Shape.
 * It includes the unique state and behaviour for square object.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class Square extends Shape {
    // Instance Variables and Methods inherit from Shape class

    /**
     * This method set the x and y-coordinates for square.
     * It override the setVertices method in Shape Class.
     *
     * @param d half of the side length.
     */
    public void setVertices(double d) {
        xLocal = new double[]{d, d, -d, -d};
        yLocal = new double[]{d, -d, -d, d};
    }
}