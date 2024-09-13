package game;
//212259279 Bar Katash

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class represent the game screen that support waiting-for-key-press.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * this method is the constructor of this animation.
     *
     * @param sensor    is the keyboard
     * @param key       is the key that imply on stop the animation
     * @param animation is the animation we show to the screen
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.isAlreadyPressed && this.sensor.isPressed(key)) {
            this.stop = true;
        }
        if (this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
