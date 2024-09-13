package sprites;
// 212259279 Bar Katash

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represent a collection of sprites.
 *
 * @author Bar Katash
 * @version 19.0.2
 * @since 2023-01-17
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * this method is the constructor of the spriteCollection which creates
     * an empty sprites.Sprite array list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * this method add sprite to the sprites collection.
     * @param s is the sprite we add to the list
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * this method return the sprites list.
     * @return the sprites list
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * this method call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * this method call drawOn(d) on all sprites.
     *
     * @param d is the drawSurface we draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}
