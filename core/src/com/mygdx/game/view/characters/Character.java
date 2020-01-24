package com.mygdx.game.view.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.enums.ECharacterMovingState;
import com.mygdx.game.model.enums.EDirection;
import com.mygdx.game.model.states.CharacterState;
import com.mygdx.game.view.Drawables;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Character extends Drawables{
    protected ECharacterMovingState movingState;
    protected EDirection direction;
    protected Texture walkingFrames;
    public TextureRegion[][] walkingAnimationFrames;
    public Animation<TextureRegion>[] walkingAnimations;
    protected Texture runningFrames;
    protected TextureRegion[][] runningAnimationFrames;
    public Animation<TextureRegion>[] runningAnimations;

    public void setPosition_X(float x){ sprite.setX(x); }
    public void setPosition_Y(float y){ sprite.setY(y); }
    public float getPosition_X() { return sprite.getX(); }
    public float getPosition_Y() { return sprite.getY(); }
    public void setState(ECharacterMovingState movingState, EDirection direction)
    {
        this.movingState = movingState;
        this.direction = direction;
    }

    protected void setMovingAnimation(){}
}
