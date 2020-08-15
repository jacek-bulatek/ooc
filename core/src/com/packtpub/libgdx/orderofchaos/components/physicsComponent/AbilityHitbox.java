package com.packtpub.libgdx.orderofchaos.components.physicsComponent;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    public void draw(Camera camera, ShapeRenderer shapeRenderer) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 1);

        float[] vertices = getTransformedVertices();
        if(vertices.length > 2) {
            for (int i = 0; i < vertices.length - 2; i += 2) {
                shapeRenderer.line(vertices[i], vertices[i + 1], vertices[i + 2], vertices[i + 3]);
            }
            shapeRenderer.line(vertices[vertices.length - 2], vertices[vertices.length - 1], vertices[0], vertices[1]);
        }
        shapeRenderer.end();
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
