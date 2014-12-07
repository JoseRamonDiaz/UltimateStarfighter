package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ultimateStarfighter.game.UltimateStarfighterGame;
import com.ultimateStarfighter.game.model.MainMenuBGActor;

public class MainMenuScreen extends USFScreen { 
	public static final int MENU_WIDTH = 100;
	private Stage stage;
	private MainMenuBGActor mainMenuBGActor;
	private Skin skin; 

	public MainMenuScreen(UltimateStarfighterGame game) {
		super(game);
		final Table table = new Table();
		stage = new Stage(new ScreenViewport());
		skin = new Skin(Gdx.files.internal("uiskin/uiskin.json")); 
		
		Gdx.input.setInputProcessor(stage);
		mainMenuBGActor = new MainMenuBGActor();
		mainMenuBGActor.setSize(stage.getWidth(), stage.getHeight());
		
		final TextButton playButton = new TextButton("Play", skin);
		playButton.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				changeToGameScreen();
			}

		});
		
		TextButton optionButton = new TextButton("Options", skin);
		optionButton.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				changeToOptionScreen();
			}
			
		});
		
		TextButton highscoreButton = new TextButton("Highscores", skin);
		
		TextButton btnExit = new TextButton("Exit", skin);
		btnExit.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				closeGame();
			}
			
		});
				
		
		table.setFillParent(true);
		int space = 10;
		table.add(playButton).fill().width(MENU_WIDTH).space(space).row();
		table.add(optionButton).fill().space(space).row();
		table.add(highscoreButton).fill().space(space).row();
		table.add(btnExit).fill().space(space).row();
//		table.debug();
		stage.addActor(mainMenuBGActor);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}
	
	private void closeGame() {
		game.dispose();
		Gdx.app.exit();
	}

	@Override
	public void render(float delta){
		stage.draw();
		stage.act();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
		mainMenuBGActor.setSize(width, height);
	}
	
	private void changeToOptionScreen(){
		game.setScreen(new OptionScreen(game));
		this.dispose();
	}
	
	private void changeToGameScreen() {
		game.setScreen(new GameScreen(game));
		this.dispose();
	}
	
}
