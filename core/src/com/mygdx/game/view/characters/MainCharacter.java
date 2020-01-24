package com.mygdx.game.view.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.enums.ECharacterMovingState;
import com.mygdx.game.model.enums.EDirection;

/**
 * Created by Jacek on 2020-01-19.
 */

public class MainCharacter extends Character{

    public MainCharacter(int x, int y)
    {
        walkingFrames = new Texture("character_walking.png");
        walkingAnimationFrames = new TextureRegion[8][8];
        walkingAnimations = new Animation[8];
        runningFrames = new Texture("character_running.png");
        runningAnimationFrames = new TextureRegion[8][8];
        runningAnimations = new Animation[8];
        setMovingAnimation();
        sprite = new Sprite(walkingAnimationFrames[0][0]);
        sprite.setPosition(x, y);
    }

    @Override
    protected void setMovingAnimation()
    {
        TextureRegion[][] tmpWalkingFrames = TextureRegion.split(walkingFrames, walkingFrames.getWidth()/8, walkingFrames.getHeight()/8);
        TextureRegion[][] tmpRunningFrames = TextureRegion.split(runningFrames, runningFrames.getWidth()/8, runningFrames.getHeight()/8);
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++) {
                walkingAnimationFrames[i][j] = tmpWalkingFrames[i][j];
                runningAnimationFrames[i][j] = tmpRunningFrames[i][j];
            }
            walkingAnimations[i] = new Animation<TextureRegion>(0.16f, walkingAnimationFrames[i]);
            runningAnimations[i] = new Animation<TextureRegion>(0.16f, runningAnimationFrames[i]);
        }
    }

    @Override
    public Sprite getSprite(float elapsedTime)
    {
        switch(movingState)
        {
            case STANDS:
                sprite.setRegion(walkingAnimationFrames[direction.toInt()][0]);
                return sprite;
            case WALKS:
                sprite.setRegion(walkingAnimations[direction.toInt()].getKeyFrame(elapsedTime, true));
                return sprite;
            case RUNS:
                sprite.setRegion(runningAnimations[direction.toInt()].getKeyFrame(elapsedTime, true));
                return sprite;
            default:
                return sprite;
        }
    }
}
