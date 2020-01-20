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
        standingTexture = new Texture("main_character_stand.png");
        standingRegion = new TextureRegion(standingTexture, 60, 110);
        movingFrames = new Texture("main_character_moving_sheet.png");
        movingAnimationFrames = new TextureRegion[8][8];
        movingAnimations = new Animation[8];
        setMovingAnimation();
        sprite = new Sprite(standingRegion);
        sprite.setPosition(x, y);
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
            movingAnimations[i] = new Animation<TextureRegion>(0.16f, movingAnimationFrames[i]);
        }
    }

    @Override
    public Sprite getSprite()
    {
        sprite.setRegion(displayedRegion);
        return sprite;
    }
}
