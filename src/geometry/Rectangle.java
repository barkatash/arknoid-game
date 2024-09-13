package geometry;
// 212259279 Bar Katash


import java.util.ArrayList;
import java.util.List;

/**
 * this class provides the option to create geometry.Rectangle type objects and use their
 * methods.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Rectangle {

    private Point upperLeft;
    private double x;
    private double y;
    private double width;
    private double height;
    private java.awt.Color color;


    /**
     * this method create a new rectangle with location, width and height.
     *
     * @param upperLeft is the upper left location of the rectangle
     * @param width     is the width of the rectangle
     * @param height    is the height of the rectangle
     * @param color     is the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height,
                     java.awt.Color color) {
        this.x = upperLeft.getX();
        this.y = upperLeft.getY();
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * this method calculates the left line bound of the rectangle.
     *
     * @return the left line bound of the rectangle
     */
    public Line getLeftLine() {
        return new Line(this.upperLeft, new Point(this.x,
                this.upperLeft.getY() + this.height));
    }

    /**
     * this method calculates the right line bound of the rectangle.
     *
     * @return the right line bound of the rectangle
     */
    public Line getRightLine() {
        return new Line(new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY()), new Point(x + this.width,
                this.upperLeft.getY() + height));
    }

    /**
     * this method calculates the upper line bound of the rectangle.
     *
     * @return the upper line bound of the rectangle
     */
    public Line getUpperLine() {
        return new Line(this.upperLeft,
                new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()));
    }

    /**
     * this method calculates the lower line bound of the rectangle.
     *
     * @return the lower line bound of the rectangle
     */
    public Line getLowerLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height);
    }

    /**
     * this method return the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this method create a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line is a specified line
     * @return the list of intersection points with the specified line
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        Line[] rectangleLines = new Line[4];
        Line leftLine = this.getLeftLine();
        Line rightLine = this.getRightLine();
        Line upperLine = this.getUpperLine();
        Line lowerLine = this.getLowerLine();
        rectangleLines[0] = leftLine;
        rectangleLines[1] = rightLine;
        rectangleLines[2] = upperLine;
        rectangleLines[3] = lowerLine;
        for (Line rectangleLine : rectangleLines) {
            Point intersection = rectangleLine.intersectionWith(line);
            if (intersection != null) {
                intersectionPoints.add(intersection);
            }
        }
        return intersectionPoints;
    }

    /**
     * this method return the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * this method return the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * this method return the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * this method return the left part of the rectangle length.
     *
     * @return the left part of the rectangle length.
     */
    public Line getLeftestPart() {
        return new Line(this.upperLeft,
                new Point(this.upperLeft.getX() + this.width / 5,
                        this.upperLeft.getY()));
    }

    /**
     * this method return the second most left part of the rectangle length.
     *
     * @return the second most left part of the rectangle length.
     */
    public Line getSecondLeftestPart() {
        return new Line(new Point(
                this.upperLeft.getX() + this.width / 5, this.upperLeft.getY()),
                new Point(this.upperLeft.getX() + 2 * this.width / 5,
                        this.upperLeft.getY()));
    }


    /**
     * this method return the middle part of the rectangle length.
     *
     * @return the middle part of the rectangle length.
     */
    public Line getMiddlePart() {
        return new Line(new Point(
                this.upperLeft.getX() + 2 * this.width / 5,
                this.upperLeft.getY()),
                new Point(this.upperLeft.getX() + 3 * this.width / 5,
                        this.upperLeft.getY()));
    }

    /**
     * this method return the second most right part of the rectangle length.
     *
     * @return the second most right part of the rectangle length.
     */
    public Line getSecondRightestPart() {
        return new Line(new Point(
                this.upperLeft.getX() + 3 * this.width / 5,
                this.upperLeft.getY()),
                new Point(this.upperLeft.getX() + 4 * this.width / 5,
                        this.upperLeft.getY()));
    }

    /**
     * this method return the right part of the rectangle length.
     *
     * @return the right part of the rectangle length.
     */
    public Line getRightestPart() {
        return new Line(new Point(
                this.upperLeft.getX() + 4 * this.width / 5,
                this.upperLeft.getY()),
                new Point(this.upperLeft.getX() + this.width,
                        this.upperLeft.getY()));
    }

    /**
     * this method change the location of the rectangle.
     *
     * @param x is the new horizontal location
     * @param y is the new vertical location
     */
    public void setUpperLeft(double x, double y) {
        this.upperLeft = new Point(x, y);
    }

    /**
     * this method changes the color of the rectangle to a given one.
     * @param color is the color we change the rectangle color to
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * this method changes the x value of the rectangle to a given one.
     * @param newX is the new x value we change the rectangle location to
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * this method changes the y value of the rectangle to a given one.
     * @param newY is the new y value we change the rectangle location to
     */
    public void setY(double newY) {
        this.y = newY;
    }

}
