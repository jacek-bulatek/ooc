package com.mygdx.game.model.actions.characterActions;

import com.mygdx.game.model.drawables.Character;
import com.mygdx.game.model.enums.ECharacterMovingState;
import com.mygdx.game.model.enums.EDirection;

/**
 * Created by Jacek on 2020-01-19.
 */

public class MoveAction extends CharacterAction {
    private EDirection direction;

    public void setDirection(EDirection direction){this.direction = direction;}

    public MoveAction(EDirection direction)
    {
        this.direction = direction;
    }

    @Override
    public void perform(Character character)
    {
        switch (direction)
        {
            case N:
                character.setPosition_Y(character.getPosition_Y() + 2);
                character.characterState.setMovingState(ECharacterMovingState.MOVES);
                character.characterState.setMovingDirection(EDirection.N);
                return;
            case S:
                character.setPosition_Y(character.getPosition_Y() - 2);
                character.characterState.setMovingState(ECharacterMovingState.MOVES);
                character.characterState.setMovingDirection(EDirection.S);
                return;
            case W:
                character.setPosition_X(character.getPosition_X() - 2);
                character.characterState.setMovingState(ECharacterMovingState.MOVES);
                character.characterState.setMovingDirection(EDirection.W);
                return;
            case E:
                character.setPosition_X(character.getPosition_X() + 2);
                character.characterState.setMovingState(ECharacterMovingState.MOVES);
                character.characterState.setMovingDirection(EDirection.E);
                return;
            case SW:
                character.setPosition_X(character.getPosition_X() - 1);
                character.setPosition_Y(character.getPosition_Y() - 1);
                character.characterState.setMovingState(ECharacterMovingState.MOVES);
                character.characterState.setMovingDirection(EDirection.SW);
                return;
            case SE:
                character.setPosition_X(character.getPosition_X() + 1);
                character.setPosition_Y(character.getPosition_Y() - 1);
                character.characterState.setMovingState(ECharacterMovingState.MOVES);
                character.characterState.setMovingDirection(EDirection.SE);
                return;
            case NW:
                character.setPosition_X(character.getPosition_X() - 1);
                character.setPosition_Y(character.getPosition_Y() + 1);
                character.characterState.setMovingState(ECharacterMovingState.MOVES);
                character.characterState.setMovingDirection(EDirection.NW);
                return;
            case NE:
                character.setPosition_X(character.getPosition_X() + 1);
                character.setPosition_Y(character.getPosition_Y() + 1);
                character.characterState.setMovingState(ECharacterMovingState.MOVES);
                character.characterState.setMovingDirection(EDirection.NE);
                return;
            default:
                return;
        }
    }
}
