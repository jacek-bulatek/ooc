package pl.noname.ooc.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import pl.noname.ooc.Assets;
import pl.noname.ooc.OOC;


public class Loading extends AbstractScreen {

    Actor loadingBar;
    float progress = 0.0f;
    boolean start = false;
    public Loading(final OOC game) {
        super(game);
        loadingBar = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                batch.end();
                game.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
                game.shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
                game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                game.shapeRenderer.setColor(Color.BLUE);
                game.shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
                game.shapeRenderer.setColor(Color.BLACK);
                game.shapeRenderer.rect(getX()+1, getY()+1, getWidth()-2, getHeight()-2);
                game.shapeRenderer.setColor(Color.BLUE);
                game.shapeRenderer.rect(getX(), getY(), getWidth()*progress, getHeight());
                game.shapeRenderer.end();
                batch.begin();
                super.draw(batch, parentAlpha);
            }
        };
        loadingBar.setSize(600f, 15f);
        loadingBar.setPosition(OOC.WIDTH/2f, OOC.HEIGHT/2f, Align.center);
        addActor(loadingBar);
    }

    @Override
    public void show() {
        super.show();
        start = false;
        progress = 0.0f;
    }

    @Override
    public void act(float delta) {
        if(!start) {
            Assets.loadAll();
            start = true;
        } else {
            if (Assets.getManager().update()) {
                game.switchScreen(OOC.Screens.MENU);
            }
            progress = Assets.getManager().getProgress();
        }

        super.act(delta);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
