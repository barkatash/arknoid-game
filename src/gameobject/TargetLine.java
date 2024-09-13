package gameobject;
//212259279 Bar katash

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;

/**
 * this class provides the option to create target Lines object that extends
 * Line class as a sprite and use its method.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class TargetLine extends Line implements Sprite {

    private Line line;
    private Color color;

    /**
     * this method is the constructor of this target line.
     * @param start is the starting point of this line
     * @param end is the end point of this line
     * @param color is the color of this line
     */
    public TargetLine(Point start, Point end, Color color) {
        super(start, end);
        this.color = color;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) super.getLine().start().getX(),
                (int) super.getLine().start().getY(),
                (int) super.getLine().end().getX(),
                (int) super.getLine().end().getY());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {

    }
}
