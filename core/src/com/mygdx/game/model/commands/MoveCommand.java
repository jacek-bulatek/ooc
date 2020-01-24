package com.mygdx.game.model.commands;

import com.mygdx.game.model.actions.characterActions.MoveAction;
import com.mygdx.game.model.enums.ECharacterMovingState;
import com.mygdx.game.model.enums.EDirection;
import com.mygdx.game.model.drawables.Character;

/**
 * Created by Jacek on 2020-01-19.
 */

public class MoveCommand extends Command{
    EDirection direction;
    ECharacterMovingState movingState;
    float elapsedTime;

    public void setElapsedTime(float elapsedTime){this.elapsedTime = elapsedTime;}

    public MoveCommand(ECharacterMovingState movingState)
    {
        this.movingState = movingState;
    }

    public void setDirection(EDirection direction) {this.direction = direction;}

    @Override
    public void execute(Character character)
    {
        character.characterState.setMovingState(movingState);
        if(movingState != ECharacterMovingState.STANDS)
        {
            MoveAction moveAction = new MoveAction(movingState, direction);
            moveAction.setElapsedTime(elapsedTime);
            character.performAction(moveAction);
        }
    }
}
