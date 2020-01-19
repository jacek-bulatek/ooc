package com.mygdx.game.view;

/**
 * Created by Jacek on 2020-01-18.
 */

public class Resolution {
    private int width;
    private int height;

    Resolution()
    {
        width = 1280;
        height = 720;
    }

    Resolution(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    Resolution(Resolution resolution)
    {
        width = resolution.width;
        height = resolution.height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
