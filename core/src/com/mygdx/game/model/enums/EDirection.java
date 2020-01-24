package com.mygdx.game.model.enums;

public enum EDirection {
    SW(4),                  // logic behind those numbers
    S(0),                   // is placement of frames in
    SE(6),                  // main_character_moving_sheet.png
    W(1),
    E(2),
    NW(5),
    N(3),
    NE(7);

    private final int index;

    public int toInt(){ return index; }

    EDirection(int index)
    {
        this.index = index;
    }
}