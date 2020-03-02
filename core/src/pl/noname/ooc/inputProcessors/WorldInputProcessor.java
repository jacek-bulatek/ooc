package pl.noname.ooc.inputProcessors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import pl.noname.ooc.actors.play.Entity;
import pl.noname.ooc.screens.MainGameScreen;

public class WorldInputProcessor implements InputProcessor {
    final private Entity hero;
    final private MainGameScreen screen;
    private boolean up = false, down = false, left = false, right = false, shift = false;
    private boolean changed = false;
    public WorldInputProcessor(final Entity hero, final MainGameScreen screen) {
        this.hero = hero;
        this.screen = screen;
    }

    private void Update() {
    	if(shift)
    		hero.setState(Entity.State.RUN);
    	else
    		hero.setState(Entity.State.WALK);
        if(up) {
            if(left) {
                hero.setMovement(Entity.Direction.NW);
            } else if(right) {
                hero.setMovement(Entity.Direction.NE);
            } else {
                hero.setMovement(Entity.Direction.N);
            }
        } else if(down) {
            if(left) {
                hero.setMovement(Entity.Direction.SW);
            } else if(right) {
                hero.setMovement(Entity.Direction.SE);
            } else {
                hero.setMovement(Entity.Direction.S);
            }
        } else if(left) {
            hero.setMovement(Entity.Direction.W);
        } else if(right) {
            hero.setMovement(Entity.Direction.E);
        } else {
            hero.stopMovement();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        changed = false;
        switch (keycode) {
            case Input.Keys.UP:
                up = true;
                changed = true;
                break;
            case Input.Keys.DOWN:
                down = true;
                changed = true;
                break;
            case Input.Keys.LEFT:
                left = true;
                changed = true;
                break;
            case Input.Keys.RIGHT:
                right = true;
                changed = true;
                break;
            case Input.Keys.SHIFT_LEFT:
            	shift = true;
            	changed = true;
            	break;
            case Input.Keys.TAB:
            	up = down = left = right = false;
            	changed = true;
            	screen.setFlag(MainGameScreen.INVENTORY);
            	break;
            case Input.Keys.ESCAPE:
            	up = down = left = right = false;
            	changed = true;
            	screen.setFlag(MainGameScreen.MENU);
            	break;
            case Input.Keys.D:
            	hero.interactible.interact();
        }
        if(changed)
        {
            Update();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        changed = false;
        switch (keycode) {
            case Input.Keys.UP:
                up = false;
                changed = true;
                break;
            case Input.Keys.DOWN:
                down = false;
                changed = true;
                break;
            case Input.Keys.LEFT:
                left = false;
                changed = true;
                break;
            case Input.Keys.RIGHT:
                right = false;
                changed = true;
                break;
            case Input.Keys.SHIFT_LEFT:
            	shift = false;
            	changed = true;
        }
        if(changed)
        {
            Update();
            return true;
        }
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
