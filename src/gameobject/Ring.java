package gameobject;
// 212259279 Bar Katash

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;

/**
 * this class provides the option to create game object - Ring implements
 * Sprite and use its methods.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Ring implements Sprite {
    private int radius;
    private Point location;
    private Color color;

    /**
     * this method is the ring constructor which allow us to create ring
     * objects.
     * @param radius is the radius of the ring
     * @param location is this ring location on screen
     * @param color is the color of the ring
     */
    public Ring(int radius, Point location, Color color) {
        this.radius = radius;
        this.location = location;
        this.location.setX(location.getX());
        this.location.setY(location.getY());
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.location.getX(), (int) this.location.getY(),
                this.radius);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
    }
}
