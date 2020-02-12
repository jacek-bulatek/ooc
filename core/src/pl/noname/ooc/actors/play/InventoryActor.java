package pl.noname.ooc.actors.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import pl.noname.ooc.Assets;
import pl.noname.ooc.OOC;

/**
 * Created by Jacek on 2020-02-12.
 */

public class InventoryActor extends Actor {
    Sprite sprite;
    public InventoryActor(){
        Texture texture = Assets.INVENTORY.get();
        sprite = new Sprite(texture);
        sprite.setX(OOC.WIDTH/2f - texture.getWidth()/2f);
        sprite.setY(OOC.HEIGHT/2f - texture.getHeight()/2f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
