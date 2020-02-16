package pl.noname.ooc.actors.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import pl.noname.ooc.Assets;

public class InventoryCell extends Actor{
	private Texture texture = Assets.INVENTORY_CELL.get();
	private Sprite sprite;
	private Item item;
	public boolean isEmpty = true;
	
	public InventoryCell() {
		sprite = new Sprite(texture);
	}
	
	public void addItem(Item item) {
		this.item = item;
		placeItem();
		isEmpty = false;
	}
	
	public void placeItem() {
		float itemX = getMiddlePosition().x - item.getWidth()/2;
		float itemY = getMiddlePosition().y - item.getHeight()/2;		
		item.setPosition(itemX, itemY);
	}
	
	public float getTextureWidth() {return texture.getWidth();}
	
	public float getTextureHeight() {return texture.getHeight();}
	
	public Vector2 getMiddlePosition() {
		Vector2 vector = new Vector2();
		vector.x = getX() + texture.getWidth() / 2;
		vector.y = getY() + texture.getHeight() / 2;
		return vector;
	}
	
	public Item getItem() {return item;}
	
	public void removeItem() {
		item = null;
		isEmpty = true;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(1f,1f,1f, getColor().a);
        batch.draw(sprite, getX(), getY());
        if (item != null)
        	item.draw(batch, parentAlpha);
	}

}
