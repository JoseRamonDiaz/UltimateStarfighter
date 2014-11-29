package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ultimateStarfighter.game.UltimateStarfighterGame;

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
							playButton.setText("Texto cambiado");	
			}
			
		});
		//playButton.setSize(MENU_WIDTH, playButton.getHeight());
//		playButton.setPosition((stage.getWidth() - playButton.getWidth()) /2, (stage.getHeight() - playButton.getHeight() )/2);
		
		TextButton optionButton = new TextButton("Options", skin);
		optionButton.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				table.addAction(Actions.parallel(Actions.moveBy(0, 600, 2f)));
			}
			
		});
//		optionButton.setSize(MENU_WIDTH, optionButton.getHeight());
//		optionButton.setPosition((stage.getWidth() - optionButton.getWidth()) /2, (stage.getHeight() - 3 * playButton.getHeight())/2);
		
		TextButton highscoreButton = new TextButton("Highscores", skin);
				
		
		table.setFillParent(true);
		int space = 10;
		table.add(playButton).fill().width(MENU_WIDTH).space(space).row();
		table.add(optionButton).fill().space(space).row();
		table.add(highscoreButton).fill().space(space).row();
//		table.debug();
		
		
		
		stage.addActor(mainMenuBGActor);
		stage.addActor(table);
//		stage.addActor(playButton);
//		stage.addActor(optionButton);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		stage.act();
		
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
		mainMenuBGActor.setSize(width, height);
	}
	
}
