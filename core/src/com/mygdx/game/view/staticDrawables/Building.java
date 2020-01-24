package com.mygdx.game.view.staticDrawables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.view.Drawables;

/**
 * Created by Jacek on 2020-01-24.
 */

public class Building extends Drawables {
    public Building(Texture texture, float position_X, float position_Y)
    {
        sprite = new Sprite(texture);
        sprite.setX(position_X);
        sprite.setY(position_Y);
    }
}
