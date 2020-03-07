package pl.noname.ooc.actors.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InventoryPopUp extends Actor {
	Sprite sprite;
	
	public InventoryPopUp(Texture texture, float x, float y) {
		sprite = new Sprite(texture);
		setPosition(x-texture.getWidth()/2f, y+20);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(isVisible()) {
	        batch.setColor(1f,1f,1f, getColor().a);
	        batch.draw(sprite, getX(), getY());
		}
	}
}
