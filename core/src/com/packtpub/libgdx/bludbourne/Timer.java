package com.packtpub.libgdx.bludbourne;

import com.badlogic.gdx.Gdx;

/**
 * Created by Jacek on 2020-03-29.
 */

public class Timer {
    private float _clock = 0;
    private float _time = 0;
    public boolean _isGoing = false;

    public Timer(float time){
        _time = time;
    }

    public void setTime(float time){_time = time;}

    public float getDone(){
        if(_time == 0)
            return 1;
        else
            return _clock/_time;
    }

    public void update(float elapsedTime){
        if(_isGoing)
            _clock += elapsedTime;
        if(_clock >= _time){
            _clock = _time;
            _isGoing = false;
        }
    }

    public void start(){
        _clock = 0;
        _isGoing = true;
    }
}
