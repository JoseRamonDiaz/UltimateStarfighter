package com.ultimateStarfighter.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ultimateStarfighter.game.screens.MainMenuScreen;

public class UltimateStarfighterGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
	}
}
