package game;
// 212259279 Bar Katash

import biuoop.DrawSurface;

/**
 * this interface handle game-specific logic and stopping conditions.
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public interface Animation {

    /**
     * this method is in charge of the game logic.
     * @param d is the draw-surface of the game
     */
    void doOneFrame(DrawSurface d);

    /**
     * this method is in charge of the game stopping condition.
     * @return true id the game should end, false otherwise
     */
    boolean shouldStop();
}
