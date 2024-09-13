// 212259279 Bar Katash
package levels;

import collision.Velocity;
import gameobject.Background;
import gameobject.TextBlocks;
import gameobject.Block;
import gameobject.Ring;
import gameobject.TargetLine;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class presents the first level of the game.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class DirectHit implements LevelInformation {
    private static final int GAME_SCREEN_WIDTH = 800;
    private static final int GAME_SCREEN_HEIGHT = 600;
    private static final int BALLS_AMOUNT = 1;
    private static final int BLOCKS_AMOUNT = 1;
    private static final int PADDLE_SPEED = 8;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 20;
    private static final int TARGET_BLOCK_WIDTH = 40;
    private static final int TARGET_BLOCK_HEIGHT = 40;
    private static final String LEVEL_NAME = "Direct Hit";
    private static final Point PADDLE_LOCATION =
            new Point((GAME_SCREEN_WIDTH - PADDLE_WIDTH) / 2.0,
                    GAME_SCREEN_HEIGHT - 50);


    @Override
    public int numberOfBalls() {
        return BALLS_AMOUNT;
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<>();
        vel.add(new Velocity(0, 8));
        return vel;
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
        ballsLocation.add(new Point(400, 500));
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
                GAME_SCREEN_HEIGHT, Color.black));
        TextBlocks textBlock = new TextBlocks(new Rectangle(new Point(533, 0),
                267, 25, Color.lightGray), "Level Name: " + this.levelName());
        background.addToBackground(textBlock);
        for (int i = 40; i <= 120; i += 40) {
            Ring ring = new Ring(i,
                    new Point(
                            GAME_SCREEN_WIDTH / 2.0,
                            180 + TARGET_BLOCK_HEIGHT / 2.0), Color.BLUE);
            background.addToBackground(ring);
        }
        TargetLine line1 =
                new TargetLine(new Point((GAME_SCREEN_WIDTH / 2.0) - 130, 200),
                        new Point(GAME_SCREEN_WIDTH / 2.0, 200), Color.BLUE);
        TargetLine line2 =
                new TargetLine(new Point((GAME_SCREEN_WIDTH / 2.0) + 20, 200),
                        new Point(GAME_SCREEN_WIDTH / 2.0 + 130, 200), Color.BLUE);
        TargetLine line3 =
                new TargetLine(new Point(GAME_SCREEN_WIDTH / 2.0, 180),
                        new Point(GAME_SCREEN_WIDTH / 2.0, 70), Color.BLUE);
        TargetLine line4 =
                new TargetLine(new Point(GAME_SCREEN_WIDTH / 2.0, 220),
                        new Point(GAME_SCREEN_WIDTH / 2.0, 330), Color.BLUE);
        background.addToBackground(line1);
        background.addToBackground(line2);
        background.addToBackground(line3);
        background.addToBackground(line4);
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block target = new Block(new Point(
                (GAME_SCREEN_WIDTH - TARGET_BLOCK_WIDTH) / 2.0, 180),
                TARGET_BLOCK_WIDTH, TARGET_BLOCK_HEIGHT, Color.red);
        blocks.add(target);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_AMOUNT;
    }
}
