package game;
// 212259279 Bar Katash


import geometry.Line;
import geometry.Point;
import collision.Collidable;
import collision.CollisionInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is a collection of objects a game object .Ball can collide with.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * this method creates an empty list of Collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }


    /**
     * this method return the collidables list.
     * @return the collidables list
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * this method add the given collidable to the environment.
     *
     * @param c is the given collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }


    /**
     * this method return the information about the closest collision that is
     * going to occur to an object moving from line.start() to line.end().
     *
     * @param trajectory is the line that the object is moving along
     * @return the information about the closest collision that is going to
     * occur, If this object will not collide with any of the collidables
     * in this collection, return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestCollision = null;
        Collidable collisionObject = null;
        double distance = Double.MAX_VALUE;

        for (Collidable collidable : collidables) {
            Point collision =
                    trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collision != null) {
                if (distance > collision.distance(trajectory.start())) {
                    closestCollision = collision;
                    collisionObject = collidable;
                }
            }
        }
        if (closestCollision == null) {
            return null;
        }
        return new CollisionInfo(closestCollision, collisionObject);

    }
}
