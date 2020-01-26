package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.commands.Command;
import com.mygdx.game.model.Model;
import com.mygdx.game.model.commands.MoveCommand;
import com.mygdx.game.model.drawables.Building;
import com.mygdx.game.model.enums.ECharacterMovingState;
import com.mygdx.game.model.enums.EDirection;
import com.mygdx.game.view.View;
import com.mygdx.game.view.characters.MainCharacter;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Controller {
    private float elapsedTime;
    private float lastElapsedTime;
    private View currentView;
    private Model model;

    public Controller()
    {
        model = new Model();
        model.addBuilding(new Building(1280-590, 720 - 430, 400, 430, 190, 0));
        currentView = new View(null);
        currentView.addComponentDrawable(new com.mygdx.game.view.staticDrawables.Building(new Texture("house.png"), 1280 - 590, 720 - 430));
    }
    public View getView(float elapsedTime)
    {
        lastElapsedTime = this.elapsedTime;
        this.elapsedTime = elapsedTime;
        model.setCommand(getCommand(), model.mainCharacter);
        currentView.setCameraPosition(new float[] {model.mainCharacter.getPosition_X()-(1280f/2f - 50f), model.mainCharacter.getPosition_Y()-720f/2f});
        currentView.mainCharacter.setState(model.mainCharacter.characterState.getMovingState(),model.mainCharacter.characterState.getMovingDirection());
        return currentView;
    }

    private Command getCommand()
    {
        MoveCommand moveCommand;
        if(areArrowsPressed()) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
                moveCommand = new MoveCommand(ECharacterMovingState.RUNS);
            else
                moveCommand = new MoveCommand(ECharacterMovingState.WALKS);

            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    moveCommand.setDirection(EDirection.SW);
                else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    moveCommand.setDirection(EDirection.SE);
                else
                    moveCommand.setDirection(EDirection.S);
            else if (Gdx.input.isKeyPressed(Input.Keys.UP))
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    moveCommand.setDirection(EDirection.NW);
                else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    moveCommand.setDirection(EDirection.NE);
                else
                    moveCommand.setDirection(EDirection.N);
            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                moveCommand.setDirection(EDirection.W);
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                moveCommand.setDirection(EDirection.E);
        }
        else
            moveCommand = new MoveCommand(ECharacterMovingState.STANDS);
        moveCommand.setElapsedTime(elapsedTime-lastElapsedTime);
        return moveCommand;
    }

    boolean areArrowsPressed()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            return true;
        else
            return false;
    }
}
