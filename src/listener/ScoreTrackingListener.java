package listener;
// 212259279 Bar Katash


import gameobject.Ball;
import gameobject.Block;

/**
 * this class update the score counter when blocks are being hit and removed.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * this method is the constructor of the score tracking listener.
     * @param scoreCounter is the score counter of this score tracking listener
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
