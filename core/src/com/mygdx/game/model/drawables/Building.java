package com.mygdx.game.model.drawables;

/**
 * Created by Jacek on 2020-01-24.
 */

public class Building extends Drawables{

    public Building(float position_X, float position_Y, float width, float height, float hitboxOffset_X, float hitboxOffset_Y)
    {
        this.position_X = position_X;
        this.position_Y = position_Y;
        this.width = width;
        this.height = height;
        this.hitboxOffset_X = hitboxOffset_X;
        this.hitboxOffset_Y = hitboxOffset_Y;
    }
}
