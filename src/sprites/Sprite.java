package sprites;
// 212259279 Bar Katash


import biuoop.DrawSurface;
import game.GameLevel;

/**
 * this interface represent all the items on the screen game.
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public interface Sprite {

    /**
     * this method draw the sprite to the screen.
     *
     * @param d is the drawSurface we want to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * this method notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * this method will be in charge of adding the ball to the
     * game.
     * @param game is the game we add the ball to
     */
    void addToGame(GameLevel game);
}
