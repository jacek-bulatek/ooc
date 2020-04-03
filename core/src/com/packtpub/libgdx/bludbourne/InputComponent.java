package com.packtpub.libgdx.bludbourne;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Json;
import com.packtpub.libgdx.bludbourne.Component.MESSAGE;

import java.util.HashMap;
import java.util.Map;

public abstract class InputComponent extends ComponentSubject implements Component, InputProcessor {

    protected Entity.Direction _currentDirection = null;
    protected Entity.State _currentState = null;
    protected Json _json;
    protected Timer _animationTimer = new Timer(0);
    protected Timer[] _cooldownTimers = {new Timer(0), new Timer(0), new Timer(0), new Timer(0)};

    protected enum Keys {
        LEFT, RIGHT, UP, DOWN, Q, W, E, R, ESC, TAB, JOURNAL
    }

    protected enum Mouse {
        SELECT, DOACTION
    }

    protected static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
    protected static Map<Mouse, Boolean> mouseButtons = new HashMap<Mouse, Boolean>();

    //initialize the hashmap for inputs
    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.UP, false);
        keys.put(Keys.DOWN, false);
        keys.put(Keys.Q, false);
        keys.put(Keys.W, false);
        keys.put(Keys.E, false);
        keys.put(Keys.R, false);
        keys.put(Keys.JOURNAL, false);
        keys.put(Keys.ESC, false);
        keys.put(Keys.TAB, false);
    };

    static {
        mouseButtons.put(Mouse.SELECT, false);
        mouseButtons.put(Mouse.DOACTION, false);
    };

        InputComponent(){
        _json = new Json();
    }

    public abstract void update(Entity entity, float delta);
    
    protected boolean sendStateUpdate(Entity.State state, Entity entity){
    	if(_animationTimer._isGoing) 
    		return false;
    	else if(state == Entity.State.Q) {
    		if(_cooldownTimers[0]._isGoing) {
    			return false;
    		}
    		else {
    			_cooldownTimers[0].setTime(entity.getEntityConfig().getCooldownQ());
    			_cooldownTimers[0].start();
    			_animationTimer.setTime(0.5f);
    			_animationTimer.start();
    			entity.sendMessage(MESSAGE.CURRENT_STATE, _json.toJson(Entity.State.Q));
    			return true;
    		}
    	}
    	else {
    		entity.sendMessage(MESSAGE.CURRENT_STATE, _json.toJson(state));
    		return true;
    	}
    	
    }

}
