package gameobject;

import biuoop.DrawSurface;
import game.GameLevel;
import listener.Counter;
import sprites.Sprite;

import java.awt.Color;

/**
 * this class is in charge of displaying the current lives.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * this method create a new lives' indicator.
     * @param lives is the lives value of this indicator
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 266, 25);
        d.setColor(Color.black);
        d.drawText(20, 17, "Lives: " + this.lives.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
