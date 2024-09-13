package gameobject;
//212259279 Bar katash

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * this class provides the option to create textBlocks object as a sprite and
 * use its method.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class TextBlocks implements Sprite {

    private Rectangle block;
    private String text;


    /**
     * this method is the constructor of text blocks items.
     * @param block is the block
     * @param s is the text on the block
     */
    public TextBlocks(Rectangle block, String s) {
        this.block = block;
        this.text = s;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.block.getColor());
        d.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(), (int) this.block.getWidth(),
                (int) this.block.getHeight());
        d.setColor(Color.black);
        d.drawText((int) (this.block.getUpperLeft().getX() + 30),
                (int) (this.block.getUpperLeft().getY() + 17), this.text, 15);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {

    }
}
