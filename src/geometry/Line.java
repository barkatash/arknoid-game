package geometry;
// 212259279 Bar Katash


/**
 * this class provides the option to create line type objects and use their
 * methods.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Line {
    static final double COMPARISON_THRESHOLD = 0.00001;
    private Point start;
    private Point end;
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    // constructors

    /**
     * this a constructor method that creates a new line with a given start and
     * end points.
     *
     * @param start is the start point of the line
     * @param end   is the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.x1 = start.getX();
        this.x2 = end.getX();
        this.y1 = start.getY();
        this.y2 = end.getY();
    }

    /**
     * this a constructor method that creates a new line with a given 4 doubles.
     *
     * @param x1 is the x value of the start point
     * @param y1 is the y value of the start point
     * @param x2 is the x value of the end point
     * @param y2 is the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x2;
        this.x2 = x1;
        this.y1 = y2;
        this.y2 = y1;
        this.start = new Point(this.x1, this.y1);
        this.end = new Point(this.x2, this.y2);
    }

    /**
     * this method return this line.
     * @return this line
     */
    public Line getLine() {
        return this;
    }

    /**
     * this method return the length of the line.
     *
     * @return the length of the line by using distance method
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * this method return the slope of the line.
     *
     * @return the slope of the line
     */
    public double slope() {
        return (this.y2 - this.y1) / (this.x2 - this.x1);
    }

    /**
     * this method return the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.x2 + this.x1) / 2;
        double middleY = (this.y2 + this.y1) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * this method return the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * this method return the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * this method returns true if the line is vertical, false otherwise.
     *
     * @return true if the line is vertical, false otherwise
     */
    public boolean isVertical() {
        return this.x1 == this.x2 && this.y1 != this.y2;
    }

    /**
     * this method check if intersection point of 2 lines is actually on them.
     *
     * @param other         is a line
     * @param intersectionX is the x value of the intersection point
     * @param intersectionY is the y value of the intersection point
     * @return true if the intersection point of 2 lines is actually on them,
     * false otherwise
     */
    public boolean isPointInBoundaries(Line other,
                                       double intersectionX,
                                       double intersectionY) {
        return !(intersectionX + COMPARISON_THRESHOLD < Math.min(this.x1,
                this.x2))
                && !(intersectionX - COMPARISON_THRESHOLD
                > Math.max(this.x1, this.x2))
                && !(intersectionX + COMPARISON_THRESHOLD
                < Math.min(other.x1, other.x2))
                && !(intersectionX - COMPARISON_THRESHOLD
                > Math.max(other.x1, other.x2))
                && !(intersectionY + COMPARISON_THRESHOLD
                < Math.min(this.y1, this.y2))
                && !(intersectionY - COMPARISON_THRESHOLD
                > Math.max(this.y1, this.y2))
                && !(intersectionY + COMPARISON_THRESHOLD
                < Math.min(other.y1, other.y2))
                && !(intersectionY - COMPARISON_THRESHOLD
                > Math.max(other.y1, other.y2));

    }

    /**
     * this method is checking if 2 lines have intersection point.
     *
     * @param other is a given line
     * @return true if the lines are intersecting, false otherwise
     */
    public boolean isIntersecting(Line other) {

        //if the lines are vertical/parallel/not intersecting
        if (intersectionWith(other) == null) {
            double slope;
            double otherSlope;
            double b1;
            double b2;
            double intersectionX;

            //if one of the lines is a point
            if (this.start.equals(this.end) || other.start.equals(other.end)) {
                return false;
            }


            //checking if they both vertical lines, if so we check if they're
            // intersecting without calculate their slops
            if (this.isVertical() && other.isVertical()) {
                if (this.x1 != other.x1 || Math.min(this.y1, this.y2)
                        > Math.max(other.y1, other.y2)
                        || Math.min(other.y1, other.y2)
                        > Math.max(this.y1, this.y2)) {
                    return false;
                }
                return true;
            }

            //if they may have intersection point
            slope = this.slope();
            otherSlope = other.slope();
            b1 = this.y1 - slope * this.x1;
            b2 = other.y1 - slope * other.x1;

            //if they are parallel
            if (slope == otherSlope && slope == 0
                    && this.y1 != other.y1) {
                return false;
            } else if (slope == otherSlope && b2 != b1) {
                return false;
            }
            if (slope == otherSlope) {
                return true;
            }

            //calculate the supposed to be intersection point and check if
            // its actually on them
            intersectionX = (b2 - b1) / (slope - otherSlope);
            if (intersectionX < Math.min(this.x1, this.x2)
                    || intersectionX > Math.max(this.x1, this.x2)
                    || intersectionX < Math.min(other.x1, other.x2)
                    || intersectionX > Math.max(other.x1, other.x2)) {

                return false;
            }
        }
        return true;
    }


    /**
     * this method returns the intersection point if the lines intersect in
     * final number of points and null otherwise.
     *
     * @param other is a given line
     * @return the intersection point if the lines intersect in
     * final number of points and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double slope;
        double otherSlope;
        double b1;
        double b2;
        double intersectionX;
        double intersectionY;
        // if their both points
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            if (this.start.equals(other.end)) {
                return this.start;
            } else {
                return null;
            }
        }
        //if only one of them is a point
        if (other.start.equals(other.end)) {
            if (this.isVertical()) {
                if (this.start.getX() != other.start.getX()) {
                    return null;
                } else if (this.start.getX() == other.start.getX()) {
                    if ((other.y1 >= this.y1 && other.y1 <= this.y2)
                            || (other.y1 <= this.y1 && other.y1 >= this.y2)) {
                        return this.start;
                    }
                }
                return null;
            }
            slope = this.slope();
            b1 = this.y1 - slope * this.x1;
            if (slope * other.x1 + b1 - this.y1 < COMPARISON_THRESHOLD) {
                return other.start;
            }
            return null;
        }
        if (this.start.equals(this.end)) {
            if (other.isVertical()) {
                if (this.start.getX() != other.start.getX()) {
                    return null;
                } else if (this.start.getX() == other.start.getX()) {
                    if ((this.y1 >= other.y1 && this.y1 <= other.y2)
                            || (this.y1 <= other.y1 && this.y1 >= other.y2)) {
                        return this.start;
                    }
                }
                return null;
            }
            otherSlope = other.slope();
            b2 = other.y1 - otherSlope * other.x1;
            if (otherSlope * this.x1 + b2 - this.y1 < COMPARISON_THRESHOLD) {
                return this.start;
            }
            return null;
        }

        //checking if the lines are both verticals and if so its return
        // null/start/end point because its either endless points or null
        // intersection or one single point.
        if (this.isVertical() && other.isVertical()) {
            if (this.start.equals(other.end)) {
                return this.start;
            }
            if (this.end.equals(other.start)) {
                return this.end;
            }
            return null;
        }

        if (!this.isVertical() && !other.isVertical()) {
            slope = this.slope();
            otherSlope = other.slope();
            b1 = this.y1 - slope * this.x1;
            b2 = other.y1 - otherSlope * other.x1;

            //checking if the slops are equals and if so its return
            // null/start/end point because its either endless points or null
            // intersection or one single point.

            if (slope == otherSlope) {
                if (this.start.equals(other.end)) {
                    return this.start;
                }
                if (this.end.equals(other.start)) {
                    return this.end;
                }
                return null;
            }

            //calculate the supposed to be intersection point and check if
            // its actually on them
            intersectionX = (b2 - b1) / (slope - otherSlope);
            intersectionY = slope * intersectionX + b1;

            if (!this.isPointInBoundaries(other, intersectionX,
                    intersectionY)) {
                return null;
            }
            return new Point(intersectionX, intersectionY);
        }

        //if only one of the lines is vertical
        if (this.isVertical()) {
            otherSlope = other.slope();
            b2 = other.y1 - otherSlope * other.x1;
            intersectionY = otherSlope * this.x1 + b2;
            intersectionX = this.x1;
            if (!this.isPointInBoundaries(other, intersectionX,
                    intersectionY)) {
                return null;
            }
            return new Point(intersectionX, intersectionY);

        }
        if (other.isVertical()) {
            slope = this.slope();
            b1 = this.y1 - slope * this.x1;
            intersectionY = slope * other.x1 + b1;
            intersectionX = other.x1;
            if (!this.isPointInBoundaries(other, intersectionX,
                    intersectionY)) {
                return null;
            }

            return new Point(intersectionX, intersectionY);

        }
        return null;
    }


    /**
     * this method return true is the lines are equal, false otherwise.
     *
     * @param other is a given line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start)
                && this.end.equals(other.end))
                || (this.start.equals(other.end)
                && this.end.equals(other.start)));
    }


    /**
     * this method check if the point is on the line.
     *
     * @param point is the point we want to check
     * @return true if the point is on the line, false otherwise
     */
    public boolean isPointOnLine(Point point) {
        Point intersection = this.intersectionWith(new Line(point, point));
        return intersection != null;
    }

    /**
     * this method return the closest intersection point to the
     * start of the line.
     *
     * @param rect is the rectangle we check intersection points with
     * @return if this line does not intersect with the rectangle, return
     * null, else return the closest intersection point to the
     * start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPoints =
                rect.intersectionPoints(this);
        if (intersectionPoints == null || intersectionPoints.size() == 0) {
            return null;
        }
        Point closestIntersection = intersectionPoints.get(0);
        for (Point intersectionPoint : intersectionPoints) {
            if (intersectionPoint.distance(this.start)
                    < closestIntersection.distance(this.start)) {
                closestIntersection = intersectionPoint;
            }
        }
        return closestIntersection;

    }

    /**
     * this method return true if this line is horizontal, false otherwise.
     *
     * @return true if this line is horizontal, false otherwise
     */
    public boolean isHorizontal() {
        return this.y1 == this.y2;
    }

    /**
     * this method calculates the distance between horizontal/vertical line
     * to a given point.
     *
     * @param point is the point we calculate her distance from this line
     * @return the distance between horizontal/vertical line
     * to a given point
     */
    public double distanceOfHorizontalAndVerticalLinesFromAPoint(Point point) {
        double distance = Double.MAX_VALUE;
        if (this.isHorizontal()) {
            distance = point.getY() - this.y1;
        } else if (this.isVertical()) {
            distance = point.getX() - this.x1;
        }
        return Math.abs(distance);
    }

}
