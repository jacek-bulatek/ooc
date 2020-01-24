package com.mygdx.game.model.actions.characterActions;

import com.mygdx.game.model.drawables.Character;
import com.mygdx.game.model.enums.ECharacterMovingState;
import com.mygdx.game.model.enums.EDirection;



public class MoveAction extends CharacterAction {
    private EDirection direction;
    private ECharacterMovingState movingState;
    private float elapsedTime;

    public void setElapsedTime(float elapsedTime) {this.elapsedTime = elapsedTime;}

    public MoveAction(ECharacterMovingState movingState, EDirection direction)
    {
        elapsedTime = 0;
        this.movingState = movingState;
        this.direction = direction;
    }

    @Override
    public void perform(Character character)
    {
        switch (movingState) {
            case WALKS:
                switch (direction) {
                    case N:
                        character.setPosition_Y(character.getPosition_Y() + character.walkingVelocity*elapsedTime);
                        character.characterState.setMovingDirection(EDirection.N);
                        return;
                    case S:
                        character.setPosition_Y(character.getPosition_Y() - character.walkingVelocity*elapsedTime);
                        character.characterState.setMovingDirection(EDirection.S);
                        return;
                    case W:
                        character.setPosition_X(character.getPosition_X() - character.walkingVelocity*elapsedTime);
                        character.characterState.setMovingDirection(EDirection.W);
                        return;
                    case E:
                        character.setPosition_X(character.getPosition_X() + character.walkingVelocity*elapsedTime);
                        character.characterState.setMovingDirection(EDirection.E);
                        return;
                    case SW:
                        character.setPosition_X(character.getPosition_X() - character.walkingVelocity*elapsedTime/2);
                        character.setPosition_Y(character.getPosition_Y() - character.walkingVelocity*elapsedTime/2);
                        character.characterState.setMovingDirection(EDirection.SW);
                        return;
                    case SE:
                        character.setPosition_X(character.getPosition_X() + character.walkingVelocity*elapsedTime/2);
                        character.setPosition_Y(character.getPosition_Y() - character.walkingVelocity*elapsedTime/2);
                        character.characterState.setMovingDirection(EDirection.SE);
                        return;
                    case NW:
                        character.setPosition_X(character.getPosition_X() - character.walkingVelocity*elapsedTime/2);
                        character.setPosition_Y(character.getPosition_Y() + character.walkingVelocity*elapsedTime/2);
                        character.characterState.setMovingDirection(EDirection.NW);
                        return;
                    case NE:
                        character.setPosition_X(character.getPosition_X() + character.walkingVelocity*elapsedTime/2);
                        character.setPosition_Y(character.getPosition_Y() + character.walkingVelocity*elapsedTime/2);
                        character.characterState.setMovingDirection(EDirection.NE);
                        return;
                    default:
                        return;
                }
            case RUNS:
                switch (direction) {
                    case N:
                        character.setPosition_Y(character.getPosition_Y() + character.runningVelocity*elapsedTime);
                        character.characterState.setMovingDirection(EDirection.N);
                        return;
                    case S:
                        character.setPosition_Y(character.getPosition_Y() - character.runningVelocity*elapsedTime);
                        character.characterState.setMovingDirection(EDirection.S);
                        return;
                    case W:
                        character.setPosition_X(character.getPosition_X() - character.runningVelocity*elapsedTime);
                        character.characterState.setMovingDirection(EDirection.W);
                        return;
                    case E:
                        character.setPosition_X(character.getPosition_X() + character.runningVelocity*elapsedTime);
                        character.characterState.setMovingDirection(EDirection.E);
                        return;
                    case SW:
                        character.setPosition_X(character.getPosition_X() - character.runningVelocity*elapsedTime/2);
                        character.setPosition_Y(character.getPosition_Y() - character.runningVelocity*elapsedTime/2);
                        character.characterState.setMovingDirection(EDirection.SW);
                        return;
                    case SE:
                        character.setPosition_X(character.getPosition_X() + character.runningVelocity*elapsedTime/2);
                        character.setPosition_Y(character.getPosition_Y() - character.runningVelocity*elapsedTime/2);
                        character.characterState.setMovingDirection(EDirection.SE);
                        return;
                    case NW:
                        character.setPosition_X(character.getPosition_X() - character.runningVelocity*elapsedTime/2);
                        character.setPosition_Y(character.getPosition_Y() + character.runningVelocity*elapsedTime/2);
                        character.characterState.setMovingDirection(EDirection.NW);
                        return;
                    case NE:
                        character.setPosition_X(character.getPosition_X() + character.runningVelocity*elapsedTime/2);
                        character.setPosition_Y(character.getPosition_Y() + character.runningVelocity*elapsedTime/2);
                        character.characterState.setMovingDirection(EDirection.NE);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }
}
