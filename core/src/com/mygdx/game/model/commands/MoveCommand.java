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
    public MoveCommand(EDirection direction)
    {
        this.direction = direction;
    }

    @Override
    public void execute(Character character)
    {
        if(direction == EDirection.NONE)
        {
            character.characterState.setMovingState(ECharacterMovingState.STANDS);
            return;
        }
        MoveAction moveAction = new MoveAction(direction);
        character.performAction(moveAction);
    }
}
