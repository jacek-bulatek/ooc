package pl.noname.ooc.actors.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

import pl.noname.ooc.Assets;
import pl.noname.ooc.OOC;
import pl.noname.ooc.inputProcessors.InventoryInputProcessor;
import pl.noname.ooc.screens.Play;

/**
 * Created by Jacek on 2020-02-12.
 */

public class InventoryActor extends Window {
    final Play screen;
    InventoryInputProcessor inputProcessor;
    boolean isDragging = false;
    InventoryCell draggedItemCell;
    
    public InventoryActor(String title, Skin skin, final Play screen){
    	super(title, skin);
    	this.screen = screen;
    	WindowStyle style = skin.get("Inventory", WindowStyle.class);
    	setStyle(style);
    	setWidth(OOC.WIDTH * 0.8f);
    	setHeight(OOC.HEIGHT * 0.6f);
    	align(Align.center);
    	defaults().spaceBottom(10f);
    	row();
    	add(new InventoryCell(skin, this));
    	add(new InventoryCell(skin, this));
    	
    	inputProcessor = new InventoryInputProcessor(screen, this);
    }
    
    public InventoryInputProcessor getInputProcessor() {return inputProcessor;}
    
    public void addItem(Item item) {
    	for(int i = 0; i < getCells().size; i++) {
    		InventoryCell cell = (InventoryCell) getCells().get(i).getActor();
    		if(cell.isEmpty) {
    			cell.addItem(item);
    			return;
    		}
    	}
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updatePosition();
        if(isDragging) {
        	Vector2 pos = screen.screenToGamePos(Gdx.input.getX(), Gdx.input.getY());
        	draggedItemCell.getItem().setPosition(pos.x - defaults().getTable().getX(), pos.y- defaults().getTable().getY());
        }
    }
    
    public void updatePosition() {
        setPosition(screen.getViewport().getCamera().position.x - getWidth()/2f, screen.getViewport().getCamera().position.y - getHeight()/2f);
    }
    
    public void dragItemFromCell(InventoryCell cell) {
    	isDragging = true;
    	draggedItemCell = cell;
    }
    
    public void dropItemToCell(InventoryCell cell) {
    	isDragging = false;
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