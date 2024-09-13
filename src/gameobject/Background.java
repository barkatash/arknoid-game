package gameobject;
// 212259279 Bar Katash

import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Sprite;
import sprites.SpriteCollection;

/**
 * this class provides the option to create Background type objects and use
 * their methods.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Background implements Sprite {
    private SpriteCollection spriteList;

    /**
     * this method is the constructor of background object.
     */
    public Background() {
        this.spriteList = new SpriteCollection();
    }

    /**
     * this method provides the option to add sprite to this background.
     * @param sprite is the sprite we add to this background
     */
    public void addToBackground(Sprite sprite) {
        this.spriteList.addSprite(sprite);
    }
    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite sprite : this.spriteList.getSprites()) {
            sprite.drawOn(d);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        for (Sprite sprite : this.spriteList.getSprites()) {
            game.addSprite(sprite);
        }
    }
}
