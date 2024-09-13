package listener;
// 212259279 Bar Katash

/**
 * this interface indicate that objects that implement it send notifications
 * when they are being hit.
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public interface HitNotifier {


    /**
     * this method add hl as a listener to hit events.
     * @param hl is the listener we add to hit events
     */
    void addHitListener(HitListener hl);

    /**
     * this method remove hl from the list of listeners to hit events.
     * @param hl is the listener we remove from the list of hit events
     */
    void removeHitListener(HitListener hl);
}
