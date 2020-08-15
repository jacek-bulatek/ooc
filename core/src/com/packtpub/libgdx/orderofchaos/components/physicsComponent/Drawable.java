package com.packtpub.libgdx.orderofchaos.components.physicsComponent;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Jacek on 8/14/2020.
 */

public interface Drawable {
    void draw(Camera camera, ShapeRenderer shapeRenderer);
}
