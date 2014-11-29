package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ultimateStarfighter.game.UltimateStarfighterGame;

public class MainMenuScreen extends USFScreen {
	private Texture mainMenuBG;
	private Stage stage;
	private Actor bg;
	

	public MainMenuScreen(UltimateStarfighterGame game) {
		super(game);
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		bg = new MainMenuBGActor();
		
	}
	
}
