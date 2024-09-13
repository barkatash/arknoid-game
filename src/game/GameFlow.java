package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import listener.Counter;

import java.util.List;

/**
 * this class presents the game flow of this game levels.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class GameFlow {

    private Counter score;
    private Counter lives;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;


    /**
     * this method is the constructor of this game flow.
     * @param ar is the animation runner we will use to run this game
     * @param ks is the keyboard sensor of the user
     * @param gui is the gui screen
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter(0);
        this.lives = new Counter(5);
        this.gui = gui;
    }

    /**
     * this method running each level on the levels list.
     * @param levels is a list contains the levels information we will run
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level =
                    new GameLevel(levelInfo, this.keyboardSensor,
                            this.animationRunner, this.score, this.lives);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.getRemainingBlocks().getValue() == 0
                    && levels.lastIndexOf(levelInfo) == levels.size() - 1) {
                this.animationRunner.run(new KeyPressStoppableAnimation(
                        this.keyboardSensor, keyboardSensor.SPACE_KEY, new WinScreen(
                                this.keyboardSensor, this.score)));
                this.gui.close();
                return;
            }
            if (level.shouldStop() && this.lives.getValue() < 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(
                        this.keyboardSensor, keyboardSensor.SPACE_KEY,
                        new GameOverScreen(this.keyboardSensor, this.score)));
                this.gui.close();
                return;
            }
        }
    }
}
