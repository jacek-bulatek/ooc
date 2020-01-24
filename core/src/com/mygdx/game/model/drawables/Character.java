package com.mygdx.game.model.drawables;

import com.mygdx.game.model.actions.characterActions.CharacterAction;
import com.mygdx.game.model.actions.characterActions.MoveAction;
import com.mygdx.game.model.commands.MoveCommand;
import com.mygdx.game.model.states.CharacterState;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Character {
    private float position_X;
    private float position_Y;
    public float walkingVelocity;                   // in pixels per second
    public float runningVelocity;
    public CharacterState characterState;

    public float getPosition_X(){return position_X;}
    public float getPosition_Y(){return position_Y;}
    public void setPosition_X(float position_X){this.position_X = position_X;}
    public void setPosition_Y(float position_Y){this.position_Y = position_Y;}

    public Character()
    {
        position_X = position_Y = 0;
        walkingVelocity = 100f;
        runningVelocity = 200f;
        characterState = new CharacterState();
    }

    public void performAction(CharacterAction characterAction)
    {
        characterAction.perform(this);
    }

}
