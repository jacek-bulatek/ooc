package com.packtpub.libgdx.orderofchaos.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.packtpub.libgdx.orderofchaos.Utility;

public class Logo extends VerticalGroup {
    private Actor _order;
    private Actor _of;
    private Actor _chaos;
    private final static float FADE_DURATION = 0.75f;

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public Logo() {
        this.space(10f);
        TextureAtlas atlas = Utility.LOGO_TEXTUREATLAS;
        _order = actorFromSprite(atlas.createSprite("order"));
        _of = actorFromSprite(atlas.createSprite("of"));
        _chaos = actorFromSprite(atlas.createSprite("chaos"));

        addActor(_order);
        addActor(_of);
        addActor(_chaos);
        setVisible(false);
    }


    public static Actor actorFromSprite(final Sprite sprite) {
        return new Actor() {

            {
                setSize(sprite.getWidth(), sprite.getHeight());
            }

            @Override
            public void draw(Batch batch, float parentAlpha) {
                super.draw(batch, parentAlpha);
                batch.setColor(1f,1f,1f, getColor().a);
                batch.draw(sprite, getX(), getY(), sprite.getWidth()/2f, sprite.getHeight()/2f, sprite.getWidth(), sprite.getHeight(), getScaleX(), getScaleY(), getRotation());
            }
        };
    }

    @Override
    public void layout() {
        super.layout();
        _chaos.moveBy(0, 20f);
    }

    @Override
    public void setVisible(boolean visible) {
        if(visible) {
            float delay = 0.0f;
            for(Actor actor : getChildren()) {
                actor.addAction(Actions.delay(delay, Actions.fadeIn(FADE_DURATION)));
                delay += FADE_DURATION;
            }
        } else {
            _order.setColor(0f,0f,0f,0f);
            _of.setColor(0f,0f,0f,0f);
            _chaos.setColor(0f,0f,0f,0f);
        }
        super.setVisible(visible);
    }
}
