// 212259279 Bar Katash
package levels;


import collision.Velocity;
import gameobject.Block;
import geometry.Point;
import sprites.Sprite;
import java.util.List;

/**
 * this interface specifies the information required to fully describe a
 * game level.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public interface LevelInformation {

    /**
     * this method return the amount of balls in the level.
     *
     * @return the amount of balls in the level
     */
    int numberOfBalls();


    /**
     * this method return list of the initial velocity of each ball.
     *
     * @return list of the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * this method return the paddle speed of this game level.
     *
     * @return the paddle speed og the game level
     */
    int paddleSpeed();

    /**
     * this method return the paddle width of this game level.
     *
     * @return the paddle width of this game level
     */
    int paddleWidth();

    /**
     * this method return the paddle height of this game level.
     *
     * @return the paddle height of this game level
     */
    int paddleHeight();

    /**
     * this method return the paddle location of this game level.
     *
     * @return the paddle location of this game level
     */
    Point paddleLocation();

    /**
     * this method return the balls location of this game level.
     *
     * @return the balls location of this game level
     */
    List<Point> ballsLocation();


    /**
     * this method return the level name will be displayed at the top of the
     * screen.
     *
     * @return the level name will be displayed at the top of the screen
     */
    String levelName();

    /**
     * this method return a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * this method return The Blocks that make up this level, each block
     * contains its size, color and location.
     *
     * @return The Blocks that make up this level
     */
    List<Block> blocks();

    /**
     * this method return the number of blocks that should be removed.
     *
     * @return the number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
}
