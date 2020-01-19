package com.mygdx.game.model;

import com.mygdx.game.model.commands.Command;
import com.mygdx.game.model.drawables.Character;

/**
 * Created by Jacek on 2020-01-19.
 */

public class Model {
    public Character mainCharacter;

    public Model()
    {
        mainCharacter = new Character();
    }

    public void setCommand(Command command, Character character)
    {
        // if (feasible)
        command.execute(character);
    }
}
