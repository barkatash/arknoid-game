package game;
// 212259279 Bar Katash

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


/**
 * this class provides the option to pause the game.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * this method is the contractor of pause screen.
     * @param k is the keyboard
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(170, d.getHeight() / 2, "paused -- press space to continue",
                32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
