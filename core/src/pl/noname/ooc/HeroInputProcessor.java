package pl.noname.ooc;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.ArrayMap;
import pl.noname.ooc.actors.play.Character;

public class HeroInputProcessor implements InputProcessor {
    final private Character hero;
    private boolean up = false, down = false, left = false, right = false;
    private boolean changed = false;
    public HeroInputProcessor(final Character hero) {
        this.hero = hero;
    }

    private void Update() {
        if(up) {
            if(left) {
                hero.setMovement(Character.Direction.NW);
            } else if(right) {
                hero.setMovement(Character.Direction.NE);
            } else {
                hero.setMovement(Character.Direction.N);
            }
        } else if(down) {
            if(left) {
                hero.setMovement(Character.Direction.SW);
            } else if(right) {
                hero.setMovement(Character.Direction.SE);
            } else {
                hero.setMovement(Character.Direction.S);
            }
        } else if(left) {
            hero.setMovement(Character.Direction.W);
        } else if(right) {
            hero.setMovement(Character.Direction.E);
        } else {
            hero.stopMovement();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        changed = false;
        switch (keycode) {
            case Input.Keys.UP: case Input.Keys.W:
                up = true;
                changed = true;
                break;
            case Input.Keys.DOWN: case Input.Keys.S:
                down = true;
                changed = true;
                break;
            case Input.Keys.LEFT: case Input.Keys.A:
                left = true;
                changed = true;
                break;
            case Input.Keys.RIGHT: case Input.Keys.D:
                right = true;
                changed = true;
                break;
        }
        if(changed)
            Update();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        changed = false;
        switch (keycode) {
            case Input.Keys.UP: case Input.Keys.W:
                up = false;
                changed = true;
                break;
            case Input.Keys.DOWN: case Input.Keys.S:
                down = false;
                changed = true;
                break;
            case Input.Keys.LEFT: case Input.Keys.A:
                left = false;
                changed = true;
                break;
            case Input.Keys.RIGHT: case Input.Keys.D:
                right = false;
                changed = true;
                break;
        }
        if(changed)
            Update();
        return true;
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