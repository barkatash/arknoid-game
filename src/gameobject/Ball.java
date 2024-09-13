package gameobject;
// 212259279 Bar Katash

import biuoop.DrawSurface;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import sprites.Sprite;
import collision.Velocity;
import java.awt.Color;


/**
 * this class provides the option to create ball type objects and use their
 * methods.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Color color;
    private double x;
    private double y;
    private Velocity v = new Velocity(0, 0);
    private GameEnvironment gameEnvironment;


    // constructors

    /**
     * this a constructor method that creates a new ball with a given center
     * point, radius and color.
     *
     * @param center          is the center point of the ball
     * @param r               is the radius of the ball
     * @param color           is the color of the ball
     * @param gameEnvironment is the game environment
     */
    public Ball(Point center, int r, Color color,
                GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.x = center.getX();
        this.y = center.getY();
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * this a constructor method that creates a new ball with a given 2
     * doubles,radius and color.
     *
     * @param x               is the x value of the center point of the ball
     * @param y               is the y value of the center point of the ball
     * @param r               is the radius of the ball
     * @param color           is the color of the ball
     * @param gameEnvironment is the game environment of the ball
     */
    public Ball(double x, double y, int r, Color color,
                GameEnvironment gameEnvironment) {
        this.r = r;
        this.color = color;
        this.x = x;
        this.y = y;
        this.center = new Point(x, y);
        this.gameEnvironment = gameEnvironment;
    }


    /**
     * this method returns the x round value of the center point of the ball.
     *
     * @return the x round value of the center point of a ball
     */
    public int getX() {
        return (int) Math.round(this.x);
    }

    /**
     * this method returns the y round value of the center point of the ball.
     *
     * @return the y round value of the center point of a ball
     */
    public int getY() {
        return (int) Math.round(this.y);
    }

    /**
     * this method returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getR() {
        return this.r;
    }

    /**
     * this method returns the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * this method changes the center point of the ball.
     *
     * @param newCenter is the new center point of the ball
     */
    public void setCenter(Point newCenter) {
        this.center = new Point(newCenter.getX(), newCenter.getY());
        this.x = newCenter.getX();
        this.y = newCenter.getY();
    }

    /**
     * this method returns the radius(size) of the ball.
     *
     * @return the radius(size) of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * this method returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * this method changes the color of the ball by using 3 params.
     *
     * @param r is the red value of the new color
     * @param g is the green value of the new color
     * @param b is the blue value of the new color
     */
    public void setColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }


    /**
     * this method draw the ball on the given DrawSurface.
     *
     * @param surface is a DrewSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) Math.round(this.center.getX()),
                (int) Math.round(this.center.getY()), this.r);
        surface.setColor(Color.black);
        surface.drawCircle((int) Math.round(this.center.getX()),
                (int) Math.round(this.center.getY()), this.r);
    }

    /**
     * this method notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * this method change the ball velocity to a given one.
     *
     * @param v is the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.v = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * this method change the ball velocity with dx and dy params.
     *
     * @param dx the new dx of the balls velocity
     * @param dy the new dy of the balls velocity
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * this method returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * this method is checking if the ball is inside given boundaries.
     *
     * @param minX is the minimum x value of the boundary
     * @param minY is the minimum y value of the boundary
     * @param maxX is the maximum x value of the boundary
     * @param maxY is the maximum y value of the boundary
     */
    public void checkBoundary(int minX, int minY, int maxX, int maxY) {
        double x = this.center.getX();
        double y = this.center.getY();
        double r = this.getR();

        if (x - r < minX) {
            this.center.setX(minX + r);
            this.v.setDx(Math.abs(this.v.getDx()));
        } else if (x + r > maxX) {
            this.center.setX(maxX - r);
            this.v.setDx(-Math.abs(this.v.getDx()));
        }

        if (y - r < minY) {
            this.center.setY(minY + r);
            this.v.setDy(Math.abs(this.v.getDy()));
        } else if (y + r > maxY) {
            this.center.setY(maxY - r);
            this.v.setDy(-Math.abs(this.v.getDy()));
        }
    }


    /**
     * this method is changing the ball center point by using applyToPoint
     * method and game environment.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center,
                new Point(this.center.getX() + this.getVelocity().getDx(),
                        this.center.getY() + this.getVelocity().getDy()));
        collision.CollisionInfo collisionInfo =
                 this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            double distance = this.center.distance(new Point(
                    this.center.getX() + this.getVelocity().getDx(),
                    this.center.getY() + this.getVelocity().getDy()));
            if (this.center.distance(collisionInfo.collisionPoint())
                    < distance + this.r) {
                this.setVelocity(collisionInfo.collisionObject().hit(
                        this, collisionInfo.collisionPoint(), this.getVelocity()));

            }
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * this method removes this ball from the game.
     * @param game is the game we remove this ball from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
