package pl.noname.ooc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import pl.noname.ooc.actors.play.InventoryActor;
import pl.noname.ooc.inputProcessors.HeroInputProcessor;
import pl.noname.ooc.OOC;
import pl.noname.ooc.actors.play.Character;
import pl.noname.ooc.actors.play.World;
import pl.noname.ooc.inputProcessors.MenuInputProcessor;
import pl.noname.ooc.inputProcessors.WorldInputProcessor;

public class Play extends AbstractScreen {
    public static final int MENU = 1;
    public static final int INVENTORY = 2;
    boolean menuFlag, inventoryFlag;

    Character hero = new Character();
    HeroInputProcessor heroInputProcessor = new HeroInputProcessor(hero);
    WorldInputProcessor worldInputProcessor = new WorldInputProcessor(this);
    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    MenuInputProcessor menuInputProcessor = new MenuInputProcessor(this);
    World world = new World(this);
    Group worldGroup = new Group();
    InventoryActor inventory = new InventoryActor(this, hero);

    public Play(final OOC game) {
        super(game);
        menuFlag = false;
        inventoryFlag = false;
        inputMultiplexer.addProcessor(heroInputProcessor);
        inputMultiplexer.addProcessor(worldInputProcessor);
        inventory.setVisible(false);
    }

    public OOC getGame(){return game;}

    public Character getHero() {return hero;}

    public void setFlag(int flag)
    {
        if(flag == MENU)
            menuFlag = true;
        else if(flag == INVENTORY) {
        	getViewport().setCamera(this.getCamera());
            inventory.setVisible(true);
            inventoryFlag = true;
        }
        return;
    }

    public void clearFlags()
    {
        menuFlag = false;
        inventoryFlag = false;
        inventory.setVisible(false);
        Gdx.input.setInputProcessor(inputMultiplexer);
        return;
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
            Gdx.input.setInputProcessor(menuInputProcessor);
            inventory.act(delta);
        }
        else if(menuFlag) {
            Gdx.input.setInputProcessor(menuInputProcessor);
        }
        else {
            worldGroup.act(delta);
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
        Gdx.input.setInputProcessor(inputMultiplexer);
        // TODO: Change literals to constants
        hero.setPosition(44*64,47);
        hero.setWorld(world);
        worldGroup.addActor(world);
        worldGroup.addActor(hero);
        addActor(worldGroup);
        addActor(inventory);
        getViewport().setCamera(world.getCamera());
    }

}
