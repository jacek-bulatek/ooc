package com.packtpub.libgdx.orderofchaos.UI;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Jacek on 2020-04-02.
 */

public class UIInputComponent implements InputProcessor {
    private final static String TAG = UIInputComponent.class.getSimpleName();
    UIInputable _UIComponent;

    public UIInputComponent(UIInputable UIComponent){
        _UIComponent = UIComponent;
    }

    public void setUIComponent(UIInputable UIComponent){_UIComponent = UIComponent; }

    @Override
    public boolean keyDown(int keycode) {
        if(_UIComponent == null) {
            return false;
        }
        if(keycode == _UIComponent._backKey){
            _UIComponent.back();
        }
        else if(keycode == Input.Keys.UP){
            _UIComponent.selectUIElement(UIInputable.Direction.UP);
        }
        else if(keycode == Input.Keys.DOWN){
            _UIComponent.selectUIElement(UIInputable.Direction.DOWN);
        }
        else if(keycode == Input.Keys.RIGHT){
            _UIComponent.selectUIElement(UIInputable.Direction.RIGHT);
        }
        else if(keycode == Input.Keys.LEFT){
            _UIComponent.selectUIElement(UIInputable.Direction.LEFT);
        }
        else if(keycode == Input.Keys.D){
            _UIComponent.activateUIElement();
        }
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
