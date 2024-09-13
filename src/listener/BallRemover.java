package listener;
// 212259279 Bar Katash

import game.GameLevel;
import gameobject.Ball;
import gameobject.Block;

/**
 * this class is in charge of removing balls from the game, as well as
 * keeping count of the number of balls that remain.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * this method is the constructor of ball remover.
     * @param game is the game we remove the ball from
     * @param remainingBalls is the amount of balls left in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
