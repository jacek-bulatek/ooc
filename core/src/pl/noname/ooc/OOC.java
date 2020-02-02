package pl.noname.ooc;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import pl.noname.ooc.screens.*;

import java.util.EnumMap;


public class OOC extends Game {

	public enum Screens {
		SPLASH,
		MENU,
		LOADING,
		PLAY;

		AbstractScreen scr = null;
		public AbstractScreen get(final OOC game) {
			if(this.scr == null) {
				Gdx.app.debug("OOC", "Creating screen " + this.toString());
				switch (this) {
					case SPLASH:
						this.scr = new Splash(game);
						break;
					case LOADING:
						this.scr = new Loading(game);
						break;
					case MENU:
						this.scr = new Menu(game);
						break;
					case PLAY:
						this.scr = new Play(game);
						break;
				}
			}
			return this.scr;
		}
	}


	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;

	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	public Viewport viewport;
	public Camera camera;


	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.debug("OOC", "Game starts");
		Assets.SPLASH.load();
		Assets.SPLASH.waitForFinish();
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new StretchViewport(WIDTH, HEIGHT);
		switchScreen(Screens.SPLASH);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void switchScreen(Screens screen) {
		setScreen(screen.get(this));
	}
}
