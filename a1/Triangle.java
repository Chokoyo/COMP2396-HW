/**
 * This class the the sub-class for Shape.
 * It includes the unique state and behaviour for triangle object.
 *
 * @author Gu Zhuangcheng
 * @version 1.0
 */
public class Triangle extends Shape {
    // Instance Variables and Methods inherit from Shape class

    /**
     * This method set the x and y-coordinates for triangle.
     * It override the setVertices method in Shape Class.
     *
     * @param d distance from the centre.
     */
    public void setVertices(double d) {
        xLocal = new double[]{d, -d * Math.cos(Math.PI / 3), -d * Math.cos(Math.PI / 3)};
        yLocal = new double[]{0, d * Math.sin(Math.PI / 3), -d * Math.sin(Math.PI / 3)};
    }
    
}