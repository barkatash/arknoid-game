package game;
// 212259279 Bar Katash

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * this method is the constructor of the counting down animation.
     * @param numOfSeconds is the number of second we would run this animation
     * @param countFrom is the number we will start counting from
     * @param gameScreen is the background
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.white);
        d.drawText((d.getWidth() - 20) / 2, (d.getHeight() - 20) / 2,
                Integer.toString(this.countFrom), 40);
        if (this.countFrom < 3) {
            int sleepTime = 1000 * (int) (this.numOfSeconds / 3);
            sleeper.sleepFor(sleepTime);
        }
        this.countFrom = this.countFrom - 1;
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom <= -1;
    }
}
