package com.packtpub.libgdx.orderofchaos.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.packtpub.libgdx.orderofchaos.OrderOfChaos.ScreenType;
import com.packtpub.libgdx.orderofchaos.OrderOfChaos;
import com.packtpub.libgdx.orderofchaos.Utility;
import com.packtpub.libgdx.orderofchaos.audio.AudioObserver;

public class MainMenuScreen extends GameScreen {

	private Stage _stage;
	private OrderOfChaos _game;
	private Logo _logo;
	private Table _menuTable;


	public MainMenuScreen(OrderOfChaos game){
		_game = game;

		//creation
		_stage = new Stage();
		_logo = new Logo();
		_logo.setPosition(OrderOfChaos.WIDTH/2f+300f, OrderOfChaos.HEIGHT/2f+340f, Align.center);

		_menuTable = new Table();
		_menuTable.setPosition(OrderOfChaos.WIDTH/2f+300f, 280, Align.center);

		Image background = new Image(Utility.MAINMENU_BACKGROUND);
		Image title = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("bludbourne_title"));

		TextButton newGameButton = new TextButton("New Game", Utility.GAME_SKIN);
		TextButton loadGameButton = new TextButton("Load Game", Utility.GAME_SKIN);
		TextButton watchIntroButton = new TextButton("Watch Intro", Utility.GAME_SKIN);
		TextButton creditsButton = new TextButton("Credits", Utility.GAME_SKIN);
		TextButton exitButton = new TextButton("Exit",Utility.GAME_SKIN);

		//Layout
		_menuTable.add(title).spaceBottom(75).row();
		_menuTable.add(newGameButton).spaceBottom(10).row();
		_menuTable.add(loadGameButton).spaceBottom(10).row();
		_menuTable.add(watchIntroButton).spaceBottom(10).row();
		_menuTable.add(creditsButton).spaceBottom(10).row();
		_menuTable.add(exitButton).spaceBottom(10).row();



		_stage.addActor(background);
		_stage.addActor(_logo);
		_stage.addActor(_menuTable);

		//Listeners
		newGameButton.addListener(new ClickListener() {
									  @Override
									  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
										  return true;
									  }

									  @Override
									  public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
										  _game.setScreen(_game.getScreenType(ScreenType.NewGame));
									  }
								  }
		);

		loadGameButton.addListener(new ClickListener() {

									   @Override
									   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
										   return true;
									   }

									   @Override
									   public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
										   _game.setScreen(_game.getScreenType(ScreenType.LoadGame));
									   }
								   }
		);

		exitButton.addListener(new ClickListener() {

								   @Override
								   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
									   return true;
								   }

								   @Override
								   public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
									   Gdx.app.exit();
								   }

							   }
		);

		watchIntroButton.addListener(new ClickListener() {

										 @Override
										 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
											 return true;
										 }

										 @Override
										 public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
											 MainMenuScreen.this.notify(AudioObserver.AudioCommand.MUSIC_STOP, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
											 _game.setScreen(_game.getScreenType(ScreenType.WatchIntro));
										 }
									 }
		);

		creditsButton.addListener(new ClickListener() {

										 @Override
										 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
											 return true;
										 }

										 @Override
										 public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
											 _game.setScreen(_game.getScreenType(ScreenType.Credits));
										 }
									 }
		);

		notify(AudioObserver.AudioCommand.MUSIC_LOAD, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		_stage.act(delta);
		_stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		_stage.getViewport().setScreenSize(width, height);
	}

	@Override
	public void show() {
		notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
		_logo.setVisible(true);
		_menuTable.setColor(0f,0f,0f,0f);
		_menuTable.addAction(Actions.delay(2f, Actions.fadeIn(0.5f)));
		Gdx.input.setInputProcessor(_stage);
	}

	@Override
	public void hide() {
		_logo.setVisible(false);
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		_stage.dispose();
	}

}



