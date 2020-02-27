package pl.noname.ooc.actors.play;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

import pl.noname.ooc.OOC;
import pl.noname.ooc.inputProcessors.MenuInputProcessor;
import pl.noname.ooc.screens.Play;

public class GameMenu extends Window{
    final Play screen;
    MenuInputProcessor inputProcessor;
    
    public GameMenu(String title, Skin skin, final Play screen){
    	super(title, skin);
    	this.screen = screen;
    	WindowStyle style = skin.get("Menu", WindowStyle.class);
    	setStyle(style);
    	setWidth(OOC.WIDTH * 0.4f);
    	setHeight(OOC.HEIGHT * 0.8f);
    	align(Align.center);
    	defaults().spaceBottom(10f);
    	row();
    	add(new MenuButton("Resume", skin, new Runnable() {
    		@Override
    		public void run() {
    			screen.clearFlags();
    		}
    	}));
    	row();
    	add(new TextButton("Load Game", skin));
    	row();
    	add(new TextButton("Save Game", skin));
    	row();
    	add(new TextButton("Options", skin));

    	inputProcessor = new MenuInputProcessor(screen, this);
    }
    
    public MenuInputProcessor getInputProcessor() {return inputProcessor;}

    @Override
    public void act(float delta) {
        super.act(delta);
        updatePosition();
    }
    
    public void updatePosition() {
    	float x = screen.getViewport().getCamera().position.x - getWidth()/2f;
    	float y = screen.getViewport().getCamera().position.y - getHeight()/2f;
    	setPosition(x, y);
    }
}
