package game;
// 212259279 Bar Katash

import collision.Velocity;
import gameobject.Block;
import gameobject.Ball;
import gameobject.LivesIndicator;
import gameobject.ScoreIndicator;
import gameobject.Paddle;
import levels.LevelInformation;
import listener.Counter;
import listener.BlockRemover;
import listener.BallRemover;
import listener.ScoreTrackingListener;
import listener.LivesTrackingListener;
import biuoop.KeyboardSensor;
import sprites.Sprite;
import sprites.SpriteCollection;
import biuoop.DrawSurface;
import geometry.Point;
import java.awt.Color;
import java.util.List;
import collision.Collidable;


/**
 * this class represent the game.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class GameLevel implements Animation {

    private static final int FRAME_RECTANGLE_WIDTH = 30;
    private static final int FRAME_RECTANGLE_HEIGHT = 30;
    static final int GAME_SCREEN_WIDTH = 800;
    static final int GAME_SCREEN_HEIGHT = 600;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * this method ia the constructor of this game level.
     *
     * @param levelInformation is the information of the level
     * @param keyboard         is the keyboard
     * @param runner           is the animationRunner of this level
     * @param score            is the score counter of this game
     * @param lives            is the lives counter of this game
     */
    public GameLevel(LevelInformation levelInformation,
                     KeyboardSensor keyboard, AnimationRunner runner,
                     Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInformation = levelInformation;
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = score;
        this.runner = runner;
        this.keyboard = keyboard;
        this.lives = lives;
    }

    /**
     * this method return the remaining blocks counter of the game.
     *
     * @return the remaining blocks counter of the game
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }
    /**
     * this method return the remaining balls counter of the game.
     *
     * @return the remaining balls counter of the game
     */
    public Counter getRemainingBalls() {
        return this.remainingBlocks;
    }

    /**
     * this method return the remaining lives counter of the game.
     *
     * @return the remaining lives counter of the game
     */
    public Counter getLives() {
        return this.score;
    }

    /**
     * this method add collidable to the collidable list of the game
     * environment.
     *
     * @param c is the collidable we add to the list
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * this method returns the sprites' collection.
     *
     * @return the sprites' collection
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * this method add sprite to the sprites list.
     *
     * @param s is the sprite we add to the list
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * this method remove the collidable c from the colliables list in game
     * environment.
     *
     * @param c is the colliable we want to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * this method remove the sprite s from the sprites list in the game.
     *
     * @param s is the sprite we want to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }


    /**
     * this method creates the frames block of the game.
     *
     * @return an array of those blocks
     */
    public Block[] framesBlocks() {
        Block[] framesBlocks = new Block[3];
        framesBlocks[0] = new Block(new Point(0, 26), FRAME_RECTANGLE_WIDTH,
                GAME_SCREEN_HEIGHT,
                Color.DARK_GRAY);
        framesBlocks[1] = new Block(new Point(
                GAME_SCREEN_WIDTH - FRAME_RECTANGLE_WIDTH, 26),
                FRAME_RECTANGLE_WIDTH,
                GAME_SCREEN_HEIGHT, Color.DARK_GRAY);
        framesBlocks[2] = new Block(new Point(FRAME_RECTANGLE_WIDTH, 26),
                GAME_SCREEN_WIDTH - 2 * FRAME_RECTANGLE_WIDTH,
                FRAME_RECTANGLE_HEIGHT, Color.DARK_GRAY);
        return framesBlocks;
    }

    /**
     * this method initialize a new game: create the Blocks and game object
     * and add them to the game.
     */
    public void initialize() {

        Sprite background = this.levelInformation.getBackground();
        background.addToGame(this);
        Block[] framesBlocks = framesBlocks();
        for (Block block : framesBlocks) {
            block.addToGame(this);
        }
        BlockRemover blockRemover =
                new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreListener =
                new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        this.sprites.addSprite(livesIndicator);
        Block deathRegion = new Block(new Point(0, GAME_SCREEN_HEIGHT + 10),
                GAME_SCREEN_WIDTH, 5, Color.DARK_GRAY);
        deathRegion.addToGame(this);
        scoreIndicator.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        List<Velocity> velocities =
                this.levelInformation.initialBallVelocities();
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball =
                    new Ball(this.levelInformation.ballsLocation().get(i), 6,
                            Color.WHITE,
                            this.environment);
            ball.setVelocity(velocities.get(i).getDx(),
                    velocities.get(i).getDy());
            ball.addToGame(this);

        }
        Paddle paddle =
                new Paddle(this.levelInformation.paddleLocation(),
                        FRAME_RECTANGLE_WIDTH, GAME_SCREEN_WIDTH
                        - FRAME_RECTANGLE_WIDTH, this.keyboard,
                        this.levelInformation.paddleWidth(),
                        this.levelInformation.paddleHeight(),
                        this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
        List<Block> blocks = this.levelInformation.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
        }
        this.remainingBalls.increase(this.levelInformation.numberOfBalls());
        this.remainingBlocks.increase(this.levelInformation.numberOfBlocksToRemove());


    }

    /**
     * this method run the game -- start the animation loop.
     */
    public void run() {
        if (this.lives.getValue() >= 0) {
            this.runner.run(new CountdownAnimation(3, 3, this.sprites));
            this.running = false;
            this.runner.run(this);
            if (this.remainingBlocks.getValue() <= 0) {
                this.running = true;
            }
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        LivesTrackingListener livesTrackingListener =
                new LivesTrackingListener(this.lives);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        this.sprites.addSprite(livesIndicator);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBlocks.getValue() <= 0
                || this.remainingBalls.getValue() <= 0) {
            if (this.remainingBlocks.getValue() <= 0) {
                this.score.increase(100);
            }
            if (this.lives.getValue() < 0) {
                this.running = true;
            }
            if (this.remainingBalls.getValue() <= 0) {
                livesTrackingListener.hitEvent();
                this.initialize();
                this.run();
            }
            this.running = true;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    keyboard.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
    }

    @Override
    public boolean shouldStop() {
        return this.running;
    }
}
