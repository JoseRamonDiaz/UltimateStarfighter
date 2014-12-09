package com.ultimateStarfighter.game.screens;

import com.ultimateStarfighter.game.UltimateStarfighterGame;
import com.ultimateStarfighter.game.model.World;

public class GameScreen extends USFScreen {
	private World world;
	
	public GameScreen(UltimateStarfighterGame game){
		super(game);
		world = new World(game);
	}
	
	@Override
	public void render(float delta) {
		world.update(delta);
	}

	@Override
	public void hide() {
		world.hide();
	}

	@Override
	public void pause() {
		world.pause();
	}
}
