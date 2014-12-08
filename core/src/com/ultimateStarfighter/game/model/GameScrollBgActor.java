package com.ultimateStarfighter.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScrollBgActor extends Actor implements Disposable{
	private float WIDTH = 640;
	private Texture bg;
	private float y;
	private float currentBgX = WIDTH;
	private long lastTimeBg;
	
	public GameScrollBgActor(){
		bg = new Texture("Backgrounds/starfield.png");
		y = 0;
		lastTimeBg = TimeUtils.nanoTime();
	}
	
	@Override
	public void dispose(){
		bg.dispose();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bg, currentBgX - WIDTH, y);
		batch.draw(bg, currentBgX, y);

	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(TimeUtils.nanoTime() - lastTimeBg > 10000000){
			currentBgX -= 1;
			lastTimeBg = TimeUtils.nanoTime();
		}
		if(currentBgX <= 0){
			currentBgX = WIDTH;
		}
	}
	
	
}
