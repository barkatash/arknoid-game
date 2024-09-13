package game;
// 212259279 Bar Katash

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * this class takes an Animation object and runs it.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * this method is the constructor of this animation running.
     * @param gui is the gui screen we run this animation on
     * @param framesPerSecond is the amount of frames we run per second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * this method is running this animation.
     * @param animation is the animation we will see on screen
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            Sleeper sleeper = new Sleeper();
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
