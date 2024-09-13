package collision;
// 212259279 Bar Katash

import geometry.Point;

/**
 * this class provides the option to create collision.CollisionInfo type objects and
 * use their methods.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class CollisionInfo {
    private Collidable collisionObject;
    private Point collisionPoint;

    /**
     * this method creates new collision.CollisionInfo with a given collisionPoint and
     * collisionObject.
     * @param collisionPoint is the point of the collision
     * @param collisionObject is the object of the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * this method return the point at which the collision occurs.
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * this method return the collidable object involved in the collision.
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
