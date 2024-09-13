// 212259279 Bar Katash

import biuoop.GUI;
import game.AnimationRunner;
import game.GameFlow;
import levels.DirectHit;
import levels.GreenThree;
import levels.LevelInformation;
import levels.WideEasy;

import java.util.ArrayList;
import java.util.List;


/**
 * this class activate the game.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Ass6Game {
    static final int GAME_SCREEN_WIDTH = 800;
    static final int GAME_SCREEN_HEIGHT = 600;
    static final int FRAMES_PER_SECOND = 60;
    private static GUI gui =
            new GUI("game", GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);

    /**
     * this method checks if a string is a number.
     * @param s is the string we check
     * @return true if s is a number' false otherwise
     */
    public static boolean isANumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * the main method call to functions that run and initialize the game.
     *
     * @param args is string array contains the game levels the user want to
     *             activate
     */
    public static void main(String[] args) {
        if (args.length >= 1 && isANumber(args[0])) {
            List<Integer> levelsOrder = new ArrayList<>();
            for (String arg : args) {
                switch (arg) {
                    case "1" -> levelsOrder.add(1);
                    case "2" -> levelsOrder.add(2);
                    case "3" -> levelsOrder.add(3);
                    default -> {
                    }
                }
            }
            GameFlow gameFlow =
                    new GameFlow(new AnimationRunner(gui, FRAMES_PER_SECOND),
                            gui.getKeyboardSensor(), gui);
            List<LevelInformation> levels = new ArrayList<>();
            for (int level : levelsOrder) {
                switch (level) {
                    case 1 -> levels.add(new DirectHit());
                    case 2 -> levels.add(new WideEasy());
                    case 3 -> levels.add(new GreenThree());
                    default -> {
                    }
                }
            }
            gameFlow.runLevels(levels);
            return;
        }
        GameFlow gameFlow =
                new GameFlow(new AnimationRunner(gui, FRAMES_PER_SECOND),
                        gui.getKeyboardSensor(), gui);
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new GreenThree());
        gameFlow.runLevels(levels);
    }

}

