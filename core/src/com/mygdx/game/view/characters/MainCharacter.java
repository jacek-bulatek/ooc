package com.mygdx.game.view.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.State;
import com.mygdx.game.model.enums.EDirection;

/**
 * Created by Jacek on 2020-01-19.
 */

public class MainCharacter extends Character{

    public MainCharacter(int x, int y)
    {
        standingTexture = new Texture("main_character_stand.png");
        standingRegion = new TextureRegion(standingTexture, 60, 110);
        movingFrames = new Texture("main_character_moving_sheet.png");
        movingAnimationFrames = new TextureRegion[8][8];
        movingAnimation = new Animation[8];
        position_X = x;
        position_Y = y;
        setMovingAnimation();
        sprite = new Sprite(standingRegion);
        sprite.setPosition(position_X, position_Y);
    }

    @Override
    protected void setMovingAnimation()
    {
        TextureRegion[][] tmpFrames = TextureRegion.split(movingFrames, 60, 110);
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++) {
                movingAnimationFrames[i][j] = tmpFrames[i][j];
            }
            movingAnimation[i] = new Animation<TextureRegion>(0.16f, movingAnimationFrames[i]);
        }
    }

    @Override
    public Sprite getSprite()
    {
        sprite.setPosition(position_X, position_Y);
        if(state.isMoving() == EDirection.NONE)
            sprite.setRegion(standingRegion);
        else
            sprite.setRegion(movingAnimation[state.isMoving().toInt()].getKeyFrame(elapsedTime, true));
        return sprite;
    }
}
