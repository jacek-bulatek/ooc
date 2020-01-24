package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.model.commands.Command;
import com.mygdx.game.model.drawables.Building;
import com.mygdx.game.model.drawables.Character;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Model implements Observer{
    public Character mainCharacter;
    public Array<Building> buildings;

    public Model()
    {
        buildings = new Array<Building>();
        mainCharacter = new Character(1280/2 - 50, 720/2, 40, 80, 30, 10);
        mainCharacter.addObserver(this);
    }

    public void setCommand(Command command, Character character)
    {
        // if (feasible)
        if(command != null)
            command.execute(character);
    }

    public void addBuilding(Building building) {buildings.add(building);}

    @Override
    public void update(Observable observable, Object o) {
        Character character = (Character) observable;
        float[] newPosition = (float[]) o;
        if(checkCollision(character, newPosition))
            return;
        else
        {
            character.setPosition_X(newPosition[0]);
            character.setPosition_Y(newPosition[1]);
        }
    }

    private boolean checkCollision(Character character, float[] newPosition)
    {
        boolean[] result = new boolean[2];
        for (Building building : buildings)
        {
            if(
                ((newPosition[0] + character.getHitboxOffset_X() + character.getWidth())
                >= (building.getPosition_X() + building.getHitboxOffset_X())
                && (newPosition[0] + character.getHitboxOffset_X())
                <= (building.getPosition_X() + building.getHitboxOffset_X() + building.getWidth()))
                &&
                ((newPosition[1] + character.getHitboxOffset_Y() + character.getHeight())
                >= (building.getPosition_Y() + building.getHitboxOffset_Y())
                && (newPosition[1] + character.getHitboxOffset_Y())
                <= (building.getPosition_Y() + building.getHitboxOffset_Y() + building.getHeight()))
                )
                return true;
        }
        return false;
    }
}
