package com.packtpub.libgdx.orderofchaos;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.packtpub.libgdx.orderofchaos.screens.*;


public class OrderOfChaos extends Game {
	private static final String TAG = OrderOfChaos.class.getSimpleName();
	public static boolean DEBUG_DRAW = false;

	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;

	private static SplashScreen _splashScreen;
	private static MainGameScreen _mainGameScreen;
	private static MainMenuScreen _mainMenuScreen;
	private static LoadGameScreen _loadGameScreen;
	private static NewGameScreen _newGameScreen;
	private static GameOverScreen _gameOverScreen;
	private static CutSceneScreen _cutSceneScreen;
	private static CreditScreen _creditScreen;

	public OrderOfChaos(String[] arg){
		super();

		if(arg.length > 0){
			for(int i = 0; i < arg.length; i++){
				if(arg[i].toLowerCase().equals("draw")){
					DEBUG_DRAW = true;
				}
			}
		}
	}

	public static enum ScreenType{
		Splash,
		MainMenu,
		MainGame,
		LoadGame,
		NewGame,
		GameOver,
		WatchIntro,
		Credits
	}

	public Screen getScreenType(ScreenType screenType){
		switch(screenType){
			case Splash:
				return _splashScreen;
			case MainMenu:
				return _mainMenuScreen;
			case MainGame:
				return _mainGameScreen;
			case LoadGame:
				return _loadGameScreen;
			case NewGame:
				return _newGameScreen;
			case GameOver:
				return _gameOverScreen;
			case WatchIntro:
				return _cutSceneScreen;
			case Credits:
				return _creditScreen;
			default:
				return _mainMenuScreen;
		}

	}

	@Override
	public void create(){
		_splashScreen = new SplashScreen(this);
		_mainGameScreen = new MainGameScreen(this);
		_mainMenuScreen = new MainMenuScreen(this);
		_loadGameScreen = new LoadGameScreen(this);
		_newGameScreen = new NewGameScreen(this);
		_gameOverScreen = new GameOverScreen(this);
		_cutSceneScreen = new CutSceneScreen(this);
		_creditScreen = new CreditScreen(this);
		setScreen(_splashScreen);
	}

	@Override
	public void dispose(){
		_mainGameScreen.dispose();
		_mainMenuScreen.dispose();
		_loadGameScreen.dispose();
		_newGameScreen.dispose();
		_gameOverScreen.dispose();
		_creditScreen.dispose();
	}

}
