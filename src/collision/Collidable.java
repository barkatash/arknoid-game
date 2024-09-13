package collision;

import geometry.Point;
import geometry.Rectangle;
import gameobject.Ball;

/**
 * this interface represent all the collidables items on the screen game.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public interface Collidable {

    /**
     * this method return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * this method notify the object that we collided with it at
     * collisionPoint with a given velocity.
     *
     * @param collisionPoint  is the point of the collision
     * @param currentVelocity is the velocity of the collision point
     * @param hitter          is the ball hit the collidable
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
