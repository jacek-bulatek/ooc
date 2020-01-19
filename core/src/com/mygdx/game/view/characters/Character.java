package com.mygdx.game.view.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.State;
import com.mygdx.game.view.Drawables;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Character extends Drawables{
    public State state;
    protected float elapsedTime;
    protected int position_X;
    protected int position_Y;
    Texture standingTexture;
    TextureRegion standingRegion;
    Texture movingFrames;
    TextureRegion[][] movingAnimationFrames;
    Animation<TextureRegion>[] movingAnimation;

    public void setPosition_X(int x){ position_X = x; }
    public void setPosition_Y(int y){ position_Y = y; }
    public int getPosition_X() { return position_X; }
    public int getPosition_Y() { return position_Y; }
    public void setElapsedTime(float elapsedTime) { this.elapsedTime = elapsedTime; }

    protected void setMovingAnimation(){}

    public Character()
    {
        state = new State();
    }
}
