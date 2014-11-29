package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainMenuBGActor extends Actor{
	private Texture bg;
	
	public MainMenuBGActor(){
		bg = new Texture("mainMenuBG.jpg");
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bg, getY(), getX());
	}
	
}
