package pl.noname.ooc.actors.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import pl.noname.ooc.Assets;
import pl.noname.ooc.screens.MainGameScreen;

public class InventoryItem extends Actor implements WorldObject{
    Texture texture = Assets.ITEM.get();
    Texture pickTexture = Assets.POPUP.get();
    Sprite sprite;
    boolean showInteraction = false;
    InventoryPopUp popUp;
    World world;
    MainGameScreen screen;
    
	public InventoryItem(Vector2 pos) {
		sprite = new Sprite(texture);
		setPosition(pos.x, pos.y);
		popUp = new InventoryPopUp(pickTexture, getX(), getY());
		popUp.setVisible(false);
	}
	
	public float getWidth() {return texture.getWidth();}
	
	public float getHeight() {return texture.getHeight();}
	
	public void setWorld(World world) {
		this.world = world;
		world.getOnMapObjects().addActor(this);
		world.addObjectToCell(new Vector2(getX(), getY()), this);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(showInteraction) {
			popUp.setVisible(true);
			showInteraction = false;
		}
		else {
			popUp.setVisible(false);
		}		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
        batch.setColor(1f,1f,1f, getColor().a);
        batch.draw(sprite, getX(), getY());
        popUp.draw(batch, parentAlpha);
	}

	@Override
	public boolean collides() {return false;}

	@Override
	public boolean interacts() {return true;}
	
	@Override
	public void interact() {
		world.removeObjectFromCell(new Vector2(getX(), getY()), this);
		world.getScreen().itemFromWorldToInventory(this);
		world = null;
	}

	@Override
	public void showInteraction() {showInteraction = true;}

}
