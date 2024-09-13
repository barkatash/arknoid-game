package collision;
// 212259279 Bar Katash

import geometry.Point;

/**
 * this class specifies the change in position on the `x` and the `y`.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * this a constructor method that creates a new velocity with a given dx and
     * dy values.
     *
     * @param dx is the horizontal change
     * @param dy is the vertical change
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * this a constructor method that creates a new velocity with a given angle
     * and speed values.
     *
     * @param angle is the angle of the movement
     * @param speed is the speed of the ball movement
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians);
        return new Velocity(dx, dy);
    }

    /**
     * this method return the dx value of the velocity.
     *
     * @return the dx value of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * this method return the dy value of the velocity.
     *
     * @return the dy value of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * this method is setting the dx value of the velocity.
     *
     * @param dx is the dx new value
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * this method is setting the dy value of the velocity.
     *
     * @param dy is the dy new value
     */
    public void setDy(double dy) {
        this.dy = dy;
    }


    /**
     * this method gets a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p is a point
     * @return p with a new position
     */
    public Point applyToPoint(Point p) {

        p.setX(p.getX() + this.dx);
        p.setY(p.getY() + this.dy);
        return p;
    }
}
