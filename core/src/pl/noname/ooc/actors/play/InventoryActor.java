package pl.noname.ooc.actors.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;

import pl.noname.ooc.Assets;
import pl.noname.ooc.inputProcessors.InventoryInputProcessor;
import pl.noname.ooc.screens.Play;

/**
 * Created by Jacek on 2020-02-12.
 */

public class InventoryActor extends Actor {
    Sprite sprite;
    Play screen;
    Texture texture = Assets.INVENTORY_BG.get();
    InventoryInputProcessor inputProcessor;
    HorizontalGroup row = new HorizontalGroup();
    boolean isDragging = false;
    InventoryCell draggedItemCell;
    
    public InventoryActor(Play screen){
    	this.screen = screen;
    	inputProcessor = new InventoryInputProcessor(screen, this);
        sprite = new Sprite(texture);

        row.space(50f);
        for(int i = 0; i < 10; i++)
        	row.addActor(new InventoryCell());
    }
    
    public InventoryInputProcessor getInputProcessor() {return inputProcessor;}
    
    public void addItem(Item item) {
    	for(int i = 0; i < row.getChildren().size; i++) {
    		InventoryCell cell = (InventoryCell) row.getChild(i);
    		if(cell.isEmpty) {
    			cell.addItem(item);
    			return;
    		}
    	}
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(1f,1f,1f, getColor().a);
        batch.draw(sprite, getX(), getY());
        row.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updatePosition();
        if(isDragging) {
        	Vector2 pos = screen.screenToGamePos(Gdx.input.getX(), Gdx.input.getY());
        	draggedItemCell.getItem().setPosition(pos.x - row.getX(), pos.y - row.getY());
        }
    }
    
    public void updatePosition() {
        setPosition(screen.getViewport().getCamera().position.x - texture.getWidth()/2f, screen.getViewport().getCamera().position.y - texture.getHeight()/2f);
        row.setPosition(getX() + 134, getY() + 50);
    }
    
    public InventoryCell getCellFromPosition(float x, float y) {
    	for(int i = 0; i < row.getChildren().size; i++) {
    		InventoryCell cell = (InventoryCell) row.getChild(i);
	    	float minx = cell.getX() + row.getX();
	    	float miny = cell.getY() + row.getY();
	    	float maxx = minx + cell.getTextureWidth();
	    	float maxy = miny + cell.getTextureHeight();
	    	if(minx < x && x < maxx && miny < y && y < maxy) {
	    		return cell;
	    	}
    	}
    	return null;
    }
    
    public void dragItemFromCell(InventoryCell cell) {
    	isDragging = true;
    	draggedItemCell = cell;
    }
    
    public void dropItemFromCell(Vector2 pos) {
    	isDragging = false;
    	InventoryCell cell = getCellFromPosition(pos.x, pos.y);
    	if(cell == null || !cell.isEmpty) {
    		draggedItemCell.placeItem();
    		draggedItemCell = null;
    	}
    	else {
    		cell.addItem(draggedItemCell.getItem());
    		draggedItemCell.removeItem();
    		draggedItemCell = null;
    	}
    		
    }
}