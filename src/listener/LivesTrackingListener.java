package listener;

/**
 * this class update the lives counter when balls are removed.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class LivesTrackingListener {
    private Counter currentLives;


    /**
     * this method is the constructor of the lives tracking listener.
     * @param livesCounter is the lives counter of this lives tracking listener
     */
    public LivesTrackingListener(Counter livesCounter) {
        this.currentLives = livesCounter;
    }


    /**
     * this method is called whenever the beingHit object is hit.
     */
    public void hitEvent() {
        this.currentLives.decrease(1);
    }
}
