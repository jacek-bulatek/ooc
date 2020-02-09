package pl.noname.ooc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import pl.noname.ooc.HeroInputProcessor;
import pl.noname.ooc.OOC;
import pl.noname.ooc.actors.play.Character;
import pl.noname.ooc.actors.play.World;

public class Play extends AbstractScreen {
    Character hero = new Character();
    HeroInputProcessor heroInputProcessor = new HeroInputProcessor(hero);
    World world = new World();

    public Play(final OOC game) {
        super(game);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.Update(hero);
        world.DrawDebug(game.shapeRenderer);
        super.act();
        super.draw();
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

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(heroInputProcessor);
        hero.setPosition(44*64,47);
        hero.setWorld(world);
        addActor(hero);
        getViewport().setCamera(world.getCamera());
    }

}
