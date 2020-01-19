package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.model.Model;
import com.mygdx.game.model.enums.EDirection;
import com.mygdx.game.view.View;
import com.mygdx.game.view.characters.MainCharacter;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Controller {
    private View currentView;
    private Model model;
    private MainCharacter mainCharacter;

    public Controller()
    {
        model = new Model();
        mainCharacter = new MainCharacter(0, 0);
        currentView = new View(null);
        currentView.addComponentSprite(mainCharacter);
    }
    public View getView(float elapsedTime)
    {
        mainCharacter.setElapsedTime(elapsedTime);
        setDirection();
        move(mainCharacter.state.isMoving());
        return currentView;
    }

    private void setDirection()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                mainCharacter.state.setState(EDirection.SW);
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                mainCharacter.state.setState(EDirection.SE);
            else
                mainCharacter.state.setState(EDirection.S);
        else if(Gdx.input.isKeyPressed(Input.Keys.UP))
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                mainCharacter.state.setState(EDirection.NW);
            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                mainCharacter.state.setState(EDirection.NE);
            else
                mainCharacter.state.setState(EDirection.N);
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            mainCharacter.state.setState(EDirection.W);
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            mainCharacter.state.setState(EDirection.E);
        else
            mainCharacter.state.setState(EDirection.NONE);
        return;
    }
    private void move(EDirection direction)
    {
        switch (direction)
        {
            case N:
                mainCharacter.setPosition_Y(mainCharacter.getPosition_Y() + 2);
                return;
            case S:
                mainCharacter.setPosition_Y(mainCharacter.getPosition_Y() - 2);
                return;
            case W:
                mainCharacter.setPosition_X(mainCharacter.getPosition_X() - 2);
                return;
            case E:
                mainCharacter.setPosition_X(mainCharacter.getPosition_X() + 2);
                return;
            case SW:
                mainCharacter.setPosition_X(mainCharacter.getPosition_X() - 1);
                mainCharacter.setPosition_Y(mainCharacter.getPosition_Y() - 1);
                return;
            case SE:
                mainCharacter.setPosition_X(mainCharacter.getPosition_X() + 1);
                mainCharacter.setPosition_Y(mainCharacter.getPosition_Y() - 1);
                return;
            case NW:
                mainCharacter.setPosition_X(mainCharacter.getPosition_X() - 1);
                mainCharacter.setPosition_Y(mainCharacter.getPosition_Y() + 1);
                return;
            case NE:
                mainCharacter.setPosition_X(mainCharacter.getPosition_X() + 1);
                mainCharacter.setPosition_Y(mainCharacter.getPosition_Y() + 1);
                return;
            default:
                return;
        }
    }
}
