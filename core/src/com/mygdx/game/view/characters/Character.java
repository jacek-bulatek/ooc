package com.mygdx.game.view.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.states.CharacterState;
import com.mygdx.game.view.Drawables;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Character extends Drawables{
    protected float elapsedTime;
    protected Texture standingTexture;
    public TextureRegion standingRegion;
    protected Texture movingFrames;
    protected TextureRegion[][] movingAnimationFrames;
    protected TextureRegion displayedRegion;
    public Animation<TextureRegion>[] movingAnimations;
    protected Animation<TextureRegion> movingAnimation;

    public void setPosition_X(float x){ sprite.setX(x); }
    public void setPosition_Y(float y){ sprite.setY(y); }
    public float getPosition_X() { return sprite.getX(); }
    public float getPosition_Y() { return sprite.getY(); }
    public void setDisplayedRegion(TextureRegion displayedRegion) {this.displayedRegion = displayedRegion;}
    public void setMovingAnimation(Animation<TextureRegion> movingAnimation){this.movingAnimation = movingAnimation;}
    public Animation<TextureRegion> getMovingAnimation(){return movingAnimation;}
    public void setElapsedTime(float elapsedTime) { this.elapsedTime = elapsedTime; }

    protected void setMovingAnimation(){}
}
