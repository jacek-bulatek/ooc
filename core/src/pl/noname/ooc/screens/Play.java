package pl.noname.ooc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import pl.noname.ooc.actors.play.GameMenu;
import pl.noname.ooc.actors.play.InventoryActor;
import pl.noname.ooc.actors.play.Item;
import pl.noname.ooc.Assets;
import pl.noname.ooc.OOC;
import pl.noname.ooc.actors.play.World;

public class Play extends AbstractScreen {
    public static final int MENU = 1;
    public static final int INVENTORY = 2;
    
    private boolean menuFlag, inventoryFlag;
    private World world;
    private GameMenu menu;
    private InventoryActor inventory;
    private InputMultiplexer inputProcessor;

    public Play(final OOC game) {
        super(game);
        inputProcessor = new InputMultiplexer();
        menuFlag = false;
        inventoryFlag = false;
        world = new World(this);
        menu = new GameMenu("", (Skin) Assets.UISKIN.get(), this);
        menu.setVisible(false);
        inventory = new InventoryActor(this);
        inventory.setVisible(false);
    }

    public OOC getGame(){return game;}

    public void setFlag(int flag)
    {
        if(flag == MENU) {
        	inputProcessor.removeProcessor(world.getInputProcessor());
        	inputProcessor.addProcessor(inventory.getInputProcessor());
            Gdx.input.setInputProcessor(inputProcessor);
        	menu.setVisible(true);
            menuFlag = true;
        }
        else if(flag == INVENTORY) {
        	inputProcessor.removeProcessor(world.getInputProcessor());
            inputProcessor.addProcessor(inventory.getInputProcessor());
            Gdx.input.setInputProcessor(inputProcessor);
            inventory.setVisible(true);
            inventoryFlag = true;
        }
        world.pause();
        return;
    }

    public void clearFlags()
    {
    	if(menuFlag) {
    		menuFlag = false;
    		inputProcessor.removeProcessor(menu.getInputProcessor());
    		menu.setVisible(false);
    	}
    	else if(inventoryFlag) {
    		inventoryFlag = false;
    		inputProcessor.removeProcessor(inventory.getInputProcessor());
    		inventory.setVisible(false);
    	}
        Gdx.input.setCursorCatched(true);
        inputProcessor.addProcessor(world.getInputProcessor());
        Gdx.input.setInputProcessor(inputProcessor);
        world.resume();
        return;
    }
    
    public Vector2 screenToGamePos(int screenX, int screenY) {
    	Vector2 pos = new Vector2();
    	pos.x = getViewport().getCamera().position.x - OOC.WIDTH/2 + screenX;
    	pos.y = getViewport().getCamera().position.y + OOC.HEIGHT/2 - screenY;
    	return pos;
    }
    
    public void itemFromWorldToInventory(Item item) {
		world.getOnMapObjects().removeActor(item);
		inventory.addItem(item);
    }
    
    public void itemFromInventoryToItem(Item item) {
    	
    }

    @Override
    public void act() {
    	// this is not called for some reason
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(inventoryFlag) {
            Gdx.input.setCursorCatched(false);
            inventory.act(delta);
        }
        else if(menuFlag) {
            Gdx.input.setCursorCatched(false);
            menu.act(delta);
        }
        else {
            world.act(delta);
        }
        super.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {
        super.show();
        inputProcessor.addProcessor(this);
        inputProcessor.addProcessor(world.getInputProcessor());
        Gdx.input.setInputProcessor(inputProcessor);
        Gdx.input.setCursorCatched(true);
        addActor(world);
        addActor(inventory);
        addActor(menu);
        getViewport().setCamera(world.getCamera());
    }
}
