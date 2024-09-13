package geometry;
// 212259279 Bar Katash

/**
 * this class provides the option to create point type objects and use their
 * methods.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Point {
    static final double COMPARISON_THRESHOLD = 0.00001;
    private double y;
    private double x;

    /**
     * this a constructor method that creates a new point with a given x and
     * y values.
     *
     * @param x is the horizontal position
     * @param y is the vertical position
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * this method calculates the distance of this point to the other point.
     *
     * @param other is a point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double distance = Math.pow((other.x - this.x), 2)
                + Math.pow((other.y - this.y), 2);
        return Math.sqrt(distance);
    }


    /**
     * this method return true is the points are equal, false otherwise.
     *
     * @param other is a point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this == null || other == null) {
            return false;
        }
        return Math.abs(this.x - other.getX()) < COMPARISON_THRESHOLD
                && Math.abs(this.y - other.getY()) < COMPARISON_THRESHOLD;
    }


    /**
     * this method return the x value of the point.
     *
     * @return the x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * this method return the y value of the point.
     *
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * this method set the x value of the point.
     * @param x is the x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * this method set the x value of the point.
     * @param y is the y value
     */
    public void setY(double y) {
        this.y = y;
    }
}
