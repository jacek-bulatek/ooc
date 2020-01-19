package com.mygdx.game.model.enums;

public enum EDirection {
    SW(0),
    S(1),
    SE(2),
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