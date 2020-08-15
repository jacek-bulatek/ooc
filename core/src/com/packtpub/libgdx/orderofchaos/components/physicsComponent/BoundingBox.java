package com.packtpub.libgdx.orderofchaos.components.physicsComponent;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.packtpub.libgdx.orderofchaos.Map;
import com.packtpub.libgdx.orderofchaos.components.graphicsComponent.GraphicsComponent;

/**
 * Created by Jacek on 8/14/2020.
 */

public class BoundingBox extends Rectangle implements Drawable {

    @Override
    public void draw(Camera camera, ShapeRenderer shapeRenderer) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(getX() * Map.UNIT_SCALE , getY() * Map.UNIT_SCALE, getWidth() * Map.UNIT_SCALE, getHeight()*Map.UNIT_SCALE);
        shapeRenderer.end();
    }
}
