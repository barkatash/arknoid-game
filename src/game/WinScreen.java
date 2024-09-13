package game;
// 212259279 Bar Katash

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import listener.Counter;

/**
 * this class present the winning screen of the game.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class WinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * this method is the contractor of the winning screen.
     *
     * @param k     is the keyboard
     * @param score is the final score of the game
     */
    public WinScreen(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(200, d.getHeight() / 2,
                "You Win! Your score is " + this.score.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
