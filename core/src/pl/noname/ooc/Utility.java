package pl.noname.ooc;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Utility {
    public static Actor actorFromSprite(final Sprite sprite) {
        return new Actor() {

            {
                setSize(sprite.getWidth(), sprite.getHeight());
            }

            @Override
            public void draw(Batch batch, float parentAlpha) {
                super.draw(batch, parentAlpha);
                batch.setColor(1f,1f,1f, getColor().a);
                batch.draw(sprite, getX(), getY(), sprite.getWidth()/2f, sprite.getHeight()/2f, sprite.getWidth(), sprite.getHeight(), getScaleX(), getScaleY(), getRotation());
            }
        };
    }
}
