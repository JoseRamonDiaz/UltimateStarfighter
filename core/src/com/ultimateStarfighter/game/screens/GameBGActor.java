package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class GameBGActor extends Actor implements Disposable{
	private Texture bg;
	private float x, y;
	
	public GameBGActor(){
		bg = new Texture("Backgrounds/spacebg.gif");
		x = 0; y = 0;
	}
	
	@Override
	public void dispose(){
		bg.dispose();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bg, x, y);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	
}
