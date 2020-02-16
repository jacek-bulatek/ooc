package pl.noname.ooc.actors.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import pl.noname.ooc.Assets;

public class Item extends Actor implements WorldObject{
    Texture texture = Assets.ITEM.get();
    Texture pickTexture = Assets.POPUP.get();
    Sprite sprite;
    boolean showInteraction = false;
    PopUp popUp;
    World world;
    
	public Item(Vector2 pos, Group group) {
		sprite = new Sprite(texture);
		setPosition(pos.x, pos.y);
		group.addActor(this);
		popUp = new PopUp(pickTexture, getX(), getY());
		popUp.setVisible(false);
	}
	
	public void setWorld(World world) {
		this.world = world;
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
	public boolean collides() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean interacts() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void showInteraction() {
		showInteraction = true;
	}

}
