package pl.noname.ooc.actors.play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import pl.noname.ooc.Assets;

public class InventoryCell extends ImageButton {
	private final InventoryActor inventory;
	private InventoryItem item;
	public boolean isEmpty = true;
	
	public InventoryCell(Skin skin, final InventoryActor inventory) {
		super(skin);
		this.inventory = inventory;
		addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(inventory.isDragging) {
					inventory.dropItemToCell((InventoryCell) actor);
				}
				else {
					inventory.dragItemFromCell((InventoryCell) actor);
				}
			}
		});
	}
	
	public void addItem(InventoryItem item) {
		this.item = item;
		placeItem();
		isEmpty = false;
	}
	
	public void placeItem() {
		float itemX = getMiddlePosition().x - item.getWidth()/2;
		float itemY = getMiddlePosition().y - item.getHeight()/2;		
		item.setPosition(itemX, itemY);
	}
	
	public Vector2 getMiddlePosition() {
		Vector2 vector = new Vector2();
		vector.x = getX() + getWidth() / 2;
		vector.y = getY() + getHeight() / 2;
		return vector;
	}
	
	public InventoryItem getItem() {return item;}
	
	public void removeItem() {
		item = null;
		isEmpty = true;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(item != null)
        	item.draw(batch, parentAlpha);
	}

}
