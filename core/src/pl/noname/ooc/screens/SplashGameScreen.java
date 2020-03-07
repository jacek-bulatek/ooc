package pl.noname.ooc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.noname.ooc.Assets;
import pl.noname.ooc.OOC;


public class SplashGameScreen extends GameScreen {
    private Actor actor;
    private Sprite splashImage;

    public SplashGameScreen(final OOC game) {
        super(game);
        splashImage = new Sprite(Assets.SPLASH.<Texture>get());

        actor = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                super.draw(batch, parentAlpha);
                batch.setColor(1f,1f,1f, getColor().a);
                batch.draw(splashImage, getX(), getY(), splashImage.getWidth()/2f, splashImage.getHeight()/2f, splashImage.getWidth(), splashImage.getHeight(), getScaleX(), getScaleY(), getRotation());
            }
        };
        addActor(actor);
        actor.setColor(1f,1f,1f, 0f);
        actor.setPosition(OOC.WIDTH/2f - splashImage.getWidth()/2f, OOC.HEIGHT/2f - splashImage.getHeight()/2f);

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
        actor.addAction(Actions.sequence(Actions.delay(0.5f), Actions.fadeIn(2f), Actions.delay(1f), Actions.rotateBy(720f, 1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                game.switchScreen(OOC.Screens.LOADING);
            }
        })));
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        game.switchScreen(OOC.Screens.LOADING);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public void hide() {

    }

    @Override
    public void resume() {

    }

}
