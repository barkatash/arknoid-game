package gameobject;
// 212259279 Bar Katash


import sprites.Sprite;
import collision.Collidable;
import collision.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * this class provides the option to create game object - Paddle implements
 * Sprite and Collidable and use their methods.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle paddleShape;
    private double leftBoarder;
    private double rightBoarder;
    private int width;
    private int speed;
    private int height;

    /**
     * this method creates a new paddle with location, width and height.
     *
     * @param upperLeft    is the upper left location of the block
//     * @param color        is the color of the rectangle
     * @param leftBoarder  is the right bound of the paddle
     * @param rightBoarder is the right bound of the paddle
     * @param keyboard     is the keyboardSensor of the paddle
     * @param width        is the paddle width
     * @param height       is the paddle height
     * @param speed        is the paddle speed
     */
    public Paddle(Point upperLeft, double leftBoarder,
                  double rightBoarder, KeyboardSensor keyboard, int width,
                  int height, int speed) {
        this.paddleShape =
                new Rectangle(upperLeft, width, height, Color.ORANGE);
        this.leftBoarder = leftBoarder;
        this.rightBoarder = rightBoarder;
        this.keyboard = keyboard;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    /**
     * this method move this paddle to the left in his boundaries.
     */
    public void moveLeft() {
        double newX =
                this.paddleShape.getUpperLeft().getX() - this.speed;
        double newY = this.paddleShape.getUpperLeft().getY();
        if (newX < leftBoarder) {
            newX = leftBoarder;
        }
        this.paddleShape.setX(newX);
        this.paddleShape.setY(newY);
        this.paddleShape.setUpperLeft(newX, newY);
    }

    /**
     * this method move this paddle to the right in his boundaries.
     */
    public void moveRight() {
        double newX = this.paddleShape.getUpperLeft().getX() + this.speed;
        double newY = this.paddleShape.getUpperLeft().getY();
        if (newX + this.paddleShape.getWidth() > rightBoarder) {
            newX = rightBoarder - this.paddleShape.getWidth();
        }
        this.paddleShape.setX(newX);
        this.paddleShape.setY(newY);
        this.paddleShape.setUpperLeft(newX, newY);

    }

    /**
     * this method return true if the left key is pressed, else return false.
     *
     * @return true if the left key is pressed, else return false.
     */
    public boolean isLeftPressed() {
        return keyboard.isPressed(KeyboardSensor.LEFT_KEY);
    }

    /**
     * this method return true if the right key is pressed, else return false.
     *
     * @return true if the right key is pressed, else return false.
     */
    public boolean isRightPressed() {
        return keyboard.isPressed(KeyboardSensor.RIGHT_KEY);
    }

    // sprites.Sprite
    @Override
    public void timePassed() {
        if (isLeftPressed()) {
            moveLeft();
        } else if (isRightPressed()) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.getCollisionRectangle().getColor());
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleShape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Velocity newVelocity;
        int part = whichPartOfPaddle(collisionPoint);
        if (part == 1) {
            newVelocity = Velocity.fromAngleAndSpeed(300,
                    Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                            + Math.pow(currentVelocity.getDy(), 2)));
        } else if (part == 2) {
            newVelocity = Velocity.fromAngleAndSpeed(330, Math.sqrt(
                    Math.pow(currentVelocity.getDx(), 2)
                            + Math.pow(currentVelocity.getDy(), 2)));
        } else if (part == 3) {
            newVelocity = Velocity.fromAngleAndSpeed(0, Math.sqrt(
                    Math.pow(currentVelocity.getDx(), 2)
                            + Math.pow(currentVelocity.getDy(), 2)));
        } else if (part == 4) {
            newVelocity = Velocity.fromAngleAndSpeed(30, Math.sqrt(
                    Math.pow(currentVelocity.getDx(), 2)
                            + Math.pow(currentVelocity.getDy(), 2)));
        } else {
            newVelocity = Velocity.fromAngleAndSpeed(60, Math.sqrt(
                    Math.pow(currentVelocity.getDx(), 2)
                            + Math.pow(currentVelocity.getDy(), 2)));
        }
        return newVelocity;
    }


    /**
     * this method add this paddle to the game.
     *
     * @param g is the game we add the paddle to
     */

    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * this method check in which part of the paddle the collision occurred.
     *
     * @param collisionPoint is the point in which the collision occurred
     * @return the number of the part that the collision occurred.
     */
    public int whichPartOfPaddle(Point collisionPoint) {
        int part = 1;
        double distance = Double.MAX_VALUE;
        Line[] paddleParts = new Line[5];
        paddleParts[0] = this.paddleShape.getLeftestPart();
        paddleParts[1] = this.paddleShape.getSecondLeftestPart();
        paddleParts[2] = this.paddleShape.getMiddlePart();
        paddleParts[3] = this.paddleShape.getSecondRightestPart();
        paddleParts[4] = this.paddleShape.getRightestPart();
        for (int i = 0; i < paddleParts.length; i++) {
            if (paddleParts[i].start().distance(collisionPoint) < distance) {
                part = i + 1;
                distance = paddleParts[i].start().distance(collisionPoint);
            }
        }
        return part;
    }
}
