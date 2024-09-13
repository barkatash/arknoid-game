package levels;
// 212259279 Bar Katash


import collision.Velocity;
import gameobject.Background;
import gameobject.TextBlocks;
import gameobject.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class presents the third level of the game.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class GreenThree implements LevelInformation {
    private static final int MAX_COLOR_COMPONENT_VALUE = 255;
    private static final int GAME_SCREEN_WIDTH = 800;
    private static final int GAME_SCREEN_HEIGHT = 600;
    private static final int BALLS_AMOUNT = 2;
    private static final int BLOCKS_AMOUNT = 35;
    private static final int FRAME_RECTANGLE_WIDTH = 30;
    private static final int PADDLE_SPEED = 8;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 20;
    private static final String LEVEL_NAME = "Green 3";
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
        velocityList.add(Velocity.fromAngleAndSpeed(45, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(-45, 5));
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
        ballsLocation.add(new Point(400, 450));
        ballsLocation.add(new Point(400, 450));
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
                GAME_SCREEN_HEIGHT, new Color(143, 188, 143)));
        TextBlocks textBlock = new TextBlocks(new Rectangle(new Point(533, 0),
                267, 25, Color.lightGray), "Level Name: " + this.levelName());
        background.addToBackground(textBlock);
        return background;
    }

    /**
     * this method creates blocks with size 60 on 25 in horizontal line.
     *
     * @param index         is the index we start to draw the blocks from
     * @param height        is the height that we draw the blocks
     * @return the list of the blocks
     */
    public  List<Block> createBlock(int index, int height) {
        Random rand = new Random();
        List<Block> blocks = new ArrayList<>();
        for (int i = index; i < 800 - (FRAME_RECTANGLE_WIDTH + 60); i += 60) {
            Color randomColor =
                    new Color(rand.nextInt(MAX_COLOR_COMPONENT_VALUE + 1),
                            rand.nextInt(MAX_COLOR_COMPONENT_VALUE + 1),
                            rand.nextInt(MAX_COLOR_COMPONENT_VALUE + 1));
            blocks.add(new Block(new Point(i, height), 60, 25, randomColor));
        }
        return blocks;
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 229, j = 100; i < 500 && j < 300; i += 60, j += 25) {
            blocks.addAll(createBlock(i, j));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_AMOUNT;
    }
}
