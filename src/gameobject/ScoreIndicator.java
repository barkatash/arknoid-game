package gameobject;
//212259279 Bar katash

import listener.Counter;
import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Sprite;

import java.awt.Color;

/**
 * this class is in charge of displaying the current score.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * this method create a new score indicator.
     * @param score is the score value of this indicator
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(260, 0, 300, 25);
        d.setColor(Color.black);
        d.drawText(360, 17, "Score: " + this.score.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
