package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ultimateStarfighter.game.UltimateStarfighterGame;

public class GameScreen extends USFScreen{
	
	private Stage stage;
	private GameBGActor gameBgActor;
	private GameScrollBgActor gameScrollBgActor;
	private ShipActor shipActor;
	private ShipActor enemyShipActor;

	public GameScreen(UltimateStarfighterGame game) {
		super(game);
		stage = new Stage(new ScreenViewport());
		gameBgActor = new GameBGActor();
		gameBgActor.setSize(stage.getWidth(), stage.getHeight());
		gameScrollBgActor = new GameScrollBgActor();
		gameScrollBgActor.setSize(stage.getWidth(), stage.getHeight());
		shipActor = new ShipActor();
		enemyShipActor = new ShipActor(ShipActor.RED_ENEMY_SHIP, 200, 250);
		Gdx.input.setInputProcessor(stage);
		stage.addActor(gameBgActor);
		stage.addActor(gameScrollBgActor);
		stage.addActor(shipActor);
		stage.addActor(enemyShipActor);
		stage.addActor(new ShipActor(ShipActor.BLUE_ENEMY_SHIP, 400, 100));
		stage.addActor(new ShipActor(ShipActor.PURPLE_ENEMY_SHIP, 350, 280));
		stage.addActor(new ShipActor(ShipActor.YELLOW_ENEMY_SHIP, 280, 300));
	}

	@Override
	public void render(float delta){
		stage.draw();
		stage.act();
	}

}
