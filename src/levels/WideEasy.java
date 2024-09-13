package levels;
// 212259279 Bar Katash


import collision.Velocity;
import gameobject.TextBlocks;
import gameobject.Background;
import gameobject.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class presents the second level of the game.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */

public class WideEasy implements LevelInformation {
    private static final int MAX_COLOR_COMPONENT_VALUE = 255;
    private static final int GAME_SCREEN_WIDTH = 800;
    private static final int GAME_SCREEN_HEIGHT = 600;
    private static final int BALLS_AMOUNT = 10;
    private static final int BLOCKS_AMOUNT = 10;
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 620;
    private static final int PADDLE_HEIGHT = 20;
    private static final String LEVEL_NAME = "Wide Easy";
    private static final Point PADDLE_LOCATION =
            new Point((GAME_SCREEN_WIDTH - PADDLE_WIDTH) / 2.0,
                    GAME_SCREEN_HEIGHT - 50);

    @Override
    public int numberOfBalls() {
        return BALLS_AMOUNT;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = -50, j = 0; j < BALLS_AMOUNT; i += 10, j++) {
            velocityList.add(Velocity.fromAngleAndSpeed(i, 4.5));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public int paddleHeight() {
        return PADDLE_HEIGHT;
    }

    @Override
    public Point paddleLocation() {
        return PADDLE_LOCATION;
    }

    @Override
    public List<Point> ballsLocation() {
        List<Point> ballsLocation = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ballsLocation.add(new Point(400, 500));
        }
        return ballsLocation;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }


    @Override
    public Sprite getBackground() {
        Background background = new Background();
        background.addToBackground(new Block(new Point(0, 0), GAME_SCREEN_WIDTH,
                GAME_SCREEN_HEIGHT, Color.PINK));
        TextBlocks textBlock = new TextBlocks(new Rectangle(new Point(533, 0),
                267, 25, Color.lightGray), "Level Name: " + this.levelName());
        background.addToBackground(textBlock);
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Random rand = new Random();
        for (int i = 30; i < 750; i += 74) {
            Color randomColor =
                    new Color(rand.nextInt(MAX_COLOR_COMPONENT_VALUE + 1),
                            rand.nextInt(MAX_COLOR_COMPONENT_VALUE + 1),
                            rand.nextInt(MAX_COLOR_COMPONENT_VALUE + 1));
            blocks.add(new Block(new Point(i, 250), 74, 30, randomColor));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_AMOUNT;
    }
}
