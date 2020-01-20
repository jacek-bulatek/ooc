package com.mygdx.game.model.enums;

public enum EDirection {
    SW(0),                  // logic behind those numbers
    S(1),                   // is placement of frames in
    SE(2),                  // main_character_moving_sheet.png
    W(3),
    E(4),
    NW(5),
    N(6),
    NE(7),
    NONE(8);

    private final int index;

    public int toInt(){ return index; }

    EDirection(int index)
    {
        this.index = index;
    }
}