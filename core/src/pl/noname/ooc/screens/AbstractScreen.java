package pl.noname.ooc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import pl.noname.ooc.OOC;

public abstract class AbstractScreen extends Stage implements Screen {

    final OOC game;

    public AbstractScreen(final OOC game) {
        super(game.viewport, game.batch);
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getCamera().update();
        act();
        super.draw();
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }
}
