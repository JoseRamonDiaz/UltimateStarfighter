package com.ultimateStarfighter.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class GameBGActor extends Actor implements Disposable{
	private Texture bg;
	
	public GameBGActor(){
		bg = new Texture("Backgrounds/spacebg.gif");
		setX(0); setY(0);
	}
	
	@Override
	public void dispose(){
		bg.dispose();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bg, getX(), getY());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	
}
