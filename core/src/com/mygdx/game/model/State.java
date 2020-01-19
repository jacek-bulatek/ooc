package com.mygdx.game.model;

import com.mygdx.game.model.enums.EDirection;

/**
 * Created by Jacek on 2020-01-19.
 */

public class State {
    EDirection direction;


    public EDirection isMoving()
    {
        return direction;
    }
    public void setState(EDirection direction)
    {
        this.direction = direction;
    }
    public State()
    {
        direction = EDirection.NONE;
    }
    public State(State state)
    {
        this.direction = state.isMoving();
    }
}
