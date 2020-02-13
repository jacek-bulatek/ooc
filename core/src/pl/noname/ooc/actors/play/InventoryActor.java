package pl.noname.ooc.actors.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import pl.noname.ooc.Assets;
import pl.noname.ooc.OOC;
import pl.noname.ooc.Utility;
import pl.noname.ooc.screens.Play;

/**
 * Created by Jacek on 2020-02-12.
 */

public class InventoryActor extends Actor {
    Sprite sprite;
    Play screen;
    Texture texture = Assets.INVENTORY.get();
    Character hero;
    
    public InventoryActor(Play screen, Character hero){
    	this.hero = hero;
    	this.screen = screen;
        sprite = new Sprite(texture);
        setPosition(hero.getX() - texture.getWidth()/2f, hero.getY() - texture.getHeight()/2f);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        screen.getViewport().apply();
        batch.setColor(1f,1f,1f, getColor().a);
        batch.draw(sprite, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(hero.getX() - texture.getWidth()/2f, hero.getY() - texture.getHeight()/2f);
    }
}
