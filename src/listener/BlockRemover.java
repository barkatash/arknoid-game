package listener;
// 212259279 Bar Katash

import game.GameLevel;
import gameobject.Ball;
import gameobject.Block;

/**
 * this class is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * this method is the constructor of block remover.
     * @param game is the game we remove the ball from
     * @param removedBlocks is the amount of blocks left in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit is the object that hit
     * @param hitter   is the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
