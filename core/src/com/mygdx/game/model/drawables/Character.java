package com.mygdx.game.model.drawables;

import com.mygdx.game.model.actions.characterActions.CharacterAction;
import com.mygdx.game.model.actions.characterActions.MoveAction;
import com.mygdx.game.model.commands.MoveCommand;
import com.mygdx.game.model.states.CharacterState;

import java.util.Observable;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Character extends Drawables{
    public CharacterState characterState;

    public Character(float position_X, float position_Y, float width, float height, float hitboxOffset_X, float hitboxOffset_Y)
    {
        this.position_X = position_X;
        this.position_Y = position_Y;
        this.width = width;
        this.height = height;
        this.hitboxOffset_X = hitboxOffset_X;
        this.hitboxOffset_Y = hitboxOffset_Y;
        walkingVelocity = 100f;
        runningVelocity = 200f;
        characterState = new CharacterState();
    }

    public void performAction(CharacterAction characterAction)
    {
        characterAction.perform(this);
    }

}
