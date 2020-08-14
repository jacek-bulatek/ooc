package com.packtpub.libgdx.orderofchaos.UI;

import com.badlogic.gdx.Input;

/**
 * Created by Jacek on 2020-04-03.
 */

public interface UIInputable {
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    int _backKey = Input.Keys.ESCAPE;

    void selectUIElement(Direction direction);

    void activateUIElement();

    void back();
}
