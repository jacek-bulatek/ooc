package pl.noname.ooc.inputProcessors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import pl.noname.ooc.screens.Play;

/**
 * Created by Jacek on 2020-02-11.
 */

public class InventoryInputProcessor implements InputProcessor{
    Play screen;

    public InventoryInputProcessor(Play screen){this.screen = screen;}

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE)
            screen.clearFlags();
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
