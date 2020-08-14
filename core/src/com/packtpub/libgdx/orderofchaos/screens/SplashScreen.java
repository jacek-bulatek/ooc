package com.packtpub.libgdx.orderofchaos.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.packtpub.libgdx.orderofchaos.OrderOfChaos;
import com.packtpub.libgdx.orderofchaos.Utility;


public class SplashScreen extends GameScreen {
    private Stage _stage;
    private Actor _actor;
    private Sprite splashImage;
    private OrderOfChaos _game;

    public SplashScreen(final OrderOfChaos game) {
        _game = game;
        _stage = new Stage();

        splashImage = new Sprite(Utility.SPLASH);

        _actor = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                super.draw(batch, parentAlpha);
                batch.setColor(1f,1f,1f, getColor().a);
                batch.draw(splashImage, getX(), getY(), splashImage.getWidth()/2f, splashImage.getHeight()/2f, splashImage.getWidth(), splashImage.getHeight(), getScaleX(), getScaleY(), getRotation());
            }
        };

        _stage.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                _game.setScreen(_game.getScreenType(OrderOfChaos.ScreenType.MainMenu));
            }
        });

        _actor.setColor(1f,1f,1f, 0f);
        _actor.setPosition(OrderOfChaos.WIDTH/2f - splashImage.getWidth()/2f, OrderOfChaos.HEIGHT/2f - splashImage.getHeight()/2f);
        _stage.addActor(_actor);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _stage.act(delta);
        _stage.draw();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(_stage);
        _actor.addAction(Actions.sequence(Actions.delay(0.5f),
                Actions.fadeIn(2f),
                Actions.delay(1f),
                Actions.rotateBy(720f, 1f),
                Actions.run(new Runnable() {
            @Override
            public void run() {
                _game.setScreen(_game.getScreenType(OrderOfChaos.ScreenType.MainMenu));
            }
        })));
    }

    @Override
    public void hide() {

    }

    @Override
    public void resume() {

    }


}
