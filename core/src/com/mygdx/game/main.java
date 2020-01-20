package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.enums.EDirection;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	float elapsedTime;
	Controller controller;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		controller = new Controller();
		elapsedTime = 0.0f;
	}

	@Override
	public void render () {

		elapsedTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		controller.getView(elapsedTime).display(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
