package pl.noname.ooc.inputProcessors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import pl.noname.ooc.actors.play.GameMenu;
import pl.noname.ooc.screens.Play;

public class MenuInputProcessor implements InputProcessor{
	Play screen;
	
	public MenuInputProcessor(Play screen, GameMenu menu) {
		this.screen = screen;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		case Input.Keys.ESCAPE:
			screen.clearFlags();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
