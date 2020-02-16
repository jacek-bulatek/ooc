package pl.noname.ooc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

import pl.noname.ooc.actors.play.InventoryActor;
import pl.noname.ooc.actors.play.Item;
import pl.noname.ooc.OOC;
import pl.noname.ooc.actors.play.World;

public class Play extends AbstractScreen {
    public static final int MENU = 1;
    public static final int INVENTORY = 2;
    boolean menuFlag, inventoryFlag;

    World world;
    public InventoryActor inventory;

    public Play(final OOC game) {
        super(game);
        menuFlag = false;
        inventoryFlag = false;
        world = new World(this);
        inventory = new InventoryActor(this);
        inventory.setVisible(false);
    }

    public OOC getGame(){return game;}

    public void setFlag(int flag)
    {
        if(flag == MENU)
            menuFlag = true;
        else if(flag == INVENTORY) {
        	getViewport().setCamera(this.getCamera());
            inventory.setVisible(true);
            inventoryFlag = true;
        }
        world.pause();
        return;
    }

    public void clearFlags()
    {
        menuFlag = false;
        inventoryFlag = false;
        inventory.setVisible(false);
        Gdx.input.setInputProcessor(world.getInputProcessor());
        Gdx.input.setCursorCatched(true);
        world.resume();
        return;
    }
    
    public Vector2 screenToGamePos(int screenX, int screenY) {
    	Vector2 pos = new Vector2();
    	pos.x = getViewport().getCamera().position.x - OOC.WIDTH/2 + screenX;
    	pos.y = getViewport().getCamera().position.y + OOC.HEIGHT/2 - screenY;
    	return pos;
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(inventoryFlag) {
            Gdx.input.setInputProcessor(inventory.getInputProcessor());
            Gdx.input.setCursorCatched(false);
            inventory.act(delta);
        }
        else if(menuFlag) {
            Gdx.input.setInputProcessor(inventory.getInputProcessor());
            Gdx.input.setCursorCatched(false);
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
        Gdx.input.setInputProcessor(world.getInputProcessor());
        Gdx.input.setCursorCatched(true);
        addActor(world);
        addActor(inventory);
        getViewport().setCamera(world.getCamera());
    }
}
