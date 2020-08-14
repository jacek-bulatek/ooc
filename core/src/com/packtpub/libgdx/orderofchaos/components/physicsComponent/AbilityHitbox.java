package com.packtpub.libgdx.orderofchaos.components.physicsComponent;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.packtpub.libgdx.orderofchaos.Entity;

/**
 * Created by Jacek on 8/14/2020.
 */

public class AbilityHitbox extends Polygon implements Drawable{
    public AbilityHitbox(){}
    public AbilityHitbox(float[] vertices){
        super(vertices);
    }
    @Override
    public void draw(Batch batch) {

    }

    public void setDirection(Entity.Direction _currentDirection){
        float degreeStep = 45;
        switch(_currentDirection){
            case S:
                break;
            case SW:
                rotate(1*degreeStep);
                break;
            case W:
                rotate(2*degreeStep);
                break;
            case NW:
                rotate(3*degreeStep);
                break;
            case N:
                rotate(4*degreeStep);
                break;
            case NE:
                rotate(5*degreeStep);
                break;
            case E:
                rotate(6*degreeStep);
                break;
            case SE:
                rotate(7*degreeStep);
                break;
        }
    }
}
