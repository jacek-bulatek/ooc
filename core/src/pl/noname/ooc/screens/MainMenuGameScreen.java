package pl.noname.ooc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import pl.noname.ooc.Assets;
import pl.noname.ooc.OOC;
import pl.noname.ooc.Utility;
import pl.noname.ooc.actors.menu.LogoActor;
import pl.noname.ooc.actors.menu.MenuActor;

public class MainMenuGameScreen extends GameScreen {

    MenuActor menuActor;
    LogoActor logoActor;
    Sprite background;

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
        logoActor.setVisible(true);
        menuActor.setColor(0f,0f,0f,0f);
        menuActor.addAction(Actions.delay(2f, Actions.fadeIn(0.5f)));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public MainMenuGameScreen(final OOC game) {
        super(game);
        menuActor = new MenuActor((Skin) Assets.UISKIN.get());
        logoActor = new LogoActor();
        logoActor.setPosition(OOC.WIDTH/2f+300f, OOC.HEIGHT/2f+300f, Align.center);
        background = new Sprite((Texture) Assets.MENU_BG.get());
        Actor actor = Utility.actorFromSprite(background);
        menuActor.addButton("Continue");
        menuActor.addButton("New game", new Runnable() {
            @Override
            public void run() {
                game.switchScreen(OOC.Screens.PLAY);
            }
        });
        menuActor.addButton("Options");
        menuActor.addButton("Exit", new Runnable() {
            @Override
            public void run() {
                Gdx.app.exit();
            }
        });
        menuActor.setPosition(OOC.WIDTH/2f+300f, OOC.HEIGHT/2f, Align.center);
        addActor(actor);
        addActor(menuActor);
        addActor(logoActor);
    }

}
