package listener;
// 212259279 Bar Katash
import gameobject.Block;
import gameobject.Ball;

/**
 * this interface represent objects that want to be notified of hit events.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public interface HitListener {

    /**
     * this method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the object that hit
     * @param hitter   is the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
