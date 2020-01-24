package com.mygdx.game.model.drawables;


import java.util.Observable;

/**
 * Created by Jacek on 2020-01-24.
 */

public class Drawables extends Observable {
    protected float position_X;
    protected float position_Y;
    protected float width;
    protected float height;
    protected float hitboxOffset_X;
    protected float hitboxOffset_Y;
    public float walkingVelocity;                   // in pixels per second
    public float runningVelocity;

    public float getPosition_X(){return position_X;}
    public float getPosition_Y(){return position_Y;}
    public float getWidth() {return width;}
    public float getHeight() {return height;}
    public float getHitboxOffset_X() {return hitboxOffset_X;}
    public float getHitboxOffset_Y() {return hitboxOffset_Y;}

    public void setPosition_X(float position_X){this.position_X = position_X;}
    public void setPosition_Y(float position_Y){this.position_Y = position_Y;}
    public void setPosition(float position_X, float position_Y)
    {
        setChanged();
        notifyObservers(new float[] {position_X, position_Y});
    }

}
