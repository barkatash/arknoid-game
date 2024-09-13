package gameobject;
// 212259279 Bar Katash


import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import collision.Collidable;
import collision.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import listener.HitNotifier;
import listener.HitListener;

/**
 * this class provides the option to create game object - block implements
 * Collidable and Sprite type.
 * objects and use their methods.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final int MAX_COLOR_COMPONENT_VALUE = 255;
    private Rectangle block;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * this method creates a new block with location, width and height.
     *
     * @param upperLeft is the upper left location of the block
     * @param width     is the width of the rectangle
     * @param height    is the height of the rectangle
     * @param color     is the color of the rectangle
     */
    public Block(Point upperLeft, double width, double height,
                 Color color) {
        this.block = new Rectangle(upperLeft, width, height, color);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(),
                 currentVelocity.getDy());
        if (this.block.getLeftLine().isPointOnLine(collisionPoint)
                || this.block.getRightLine().isPointOnLine(collisionPoint)) {
            newVelocity.setDx(-newVelocity.getDx());
        }
        if (this.block.getLowerLine().isPointOnLine(collisionPoint)
                || this.block.getUpperLine().isPointOnLine(collisionPoint)) {
            newVelocity.setDy(-newVelocity.getDy());
        }
        this.notifyHit(hitter);
        return newVelocity;

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
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * this method removes this block from the game.
     * @param game is the game we remove this block from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * this method notify all the registered HitListener objects by
     * calling their hitEvent method.
     * @param hitter is the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void timePassed() {
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
