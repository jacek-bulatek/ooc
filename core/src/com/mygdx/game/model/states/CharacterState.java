package com.mygdx.game.model.states;

import com.mygdx.game.model.enums.ECharacterMovingState;
import com.mygdx.game.model.enums.EDirection;

/**
 * Created by Jacek on 2020-01-19.
 */

public class CharacterState {
    EDirection movingDirection;
    ECharacterMovingState movingState;

    public ECharacterMovingState getMovingState(){return movingState;}
    public void setMovingState(ECharacterMovingState movingState) {this.movingState = movingState;}
    public EDirection getMovingDirection()
    {
        return movingDirection;
    }
    public void setMovingDirection(EDirection direction)
    {
        this.movingDirection = direction;
    }
    public CharacterState()
    {
        movingDirection = EDirection.NONE;
        movingState = ECharacterMovingState.STANDS;
    }
}
