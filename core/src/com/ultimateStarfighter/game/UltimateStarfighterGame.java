package com.ultimateStarfighter.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ultimateStarfighter.game.screens.MainMenuScreen;
import com.ultimateStarfighter.game.screens.OptionScreen;
import com.ultimateStarfighter.game.screens.USFScreen;

public class UltimateStarfighterGame extends Game {
	SpriteBatch batch;
	Texture img;
	private USFScreen mainMenuScreen;
	private OptionScreen optionScreen;
	
	public void create () {
		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
		mainMenuScreen = new MainMenuScreen(this);
		//optionScreen = new OptionScreen(this);
		setScreen(mainMenuScreen);
	}

//	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
}
