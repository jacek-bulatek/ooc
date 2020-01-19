package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.model.commands.Command;
import com.mygdx.game.model.Model;
import com.mygdx.game.model.commands.MoveCommand;
import com.mygdx.game.model.drawables.Character;
import com.mygdx.game.model.enums.ECharacterMovingState;
import com.mygdx.game.model.enums.EDirection;
import com.mygdx.game.view.View;
import com.mygdx.game.view.characters.MainCharacter;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Controller {
    private View currentView;
    private Model model;

    public Controller()
    {
        model = new Model();
        currentView = new View(null);
    }
    public View getView(float elapsedTime)
    {
        model.setCommand(getCommand(), model.mainCharacter);
        currentView.mainCharacter.setPosition_X(model.mainCharacter.getPosition_X());
        currentView.mainCharacter.setPosition_Y(model.mainCharacter.getPosition_Y());
        if(model.mainCharacter.characterState.getMovingState() == ECharacterMovingState.STANDS)
            currentView.mainCharacter.setDisplayedRegion(currentView.mainCharacter.standingRegion);
        else
        {
            currentView.mainCharacter.setMovingAnimation(currentView.mainCharacter.movingAnimations[model.mainCharacter.characterState.getMovingDirection().toInt()]);
            currentView.mainCharacter.setDisplayedRegion(currentView.mainCharacter.getMovingAnimation().getKeyFrame(elapsedTime, true));
            currentView.mainCharacter.setElapsedTime(elapsedTime);
        }
        return currentView;
    }

    private Command getCommand()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                return new MoveCommand(EDirection.SW);
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                return new MoveCommand(EDirection.SE);
            else
                return new MoveCommand(EDirection.S);
        else if(Gdx.input.isKeyPressed(Input.Keys.UP))
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                return new MoveCommand(EDirection.NW);
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                return new MoveCommand(EDirection.NE);
            else
                return new MoveCommand(EDirection.N);
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            return new MoveCommand(EDirection.W);
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            return new MoveCommand(EDirection.E);
        else
            return new MoveCommand(EDirection.NONE);
    }
}
