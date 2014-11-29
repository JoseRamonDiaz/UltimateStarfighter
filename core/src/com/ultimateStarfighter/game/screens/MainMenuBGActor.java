package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class MainMenuBGActor extends Actor implements Disposable{
	private Texture bg;
	private TextureRegion myBg;
	
	public MainMenuBGActor(){
		bg = new Texture("mainMenuBG.jpg");
		myBg = new TextureRegion(bg);
		//setSize(100, 100);
	}
	
	@Override
	public void dispose(){
		bg.dispose();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		//batch.draw(myBg, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		batch.draw(bg, 0, 0, getWidth(), getHeight());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	
	
}
