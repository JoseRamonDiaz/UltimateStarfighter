package com.ultimateStarfighter.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class LaserActor extends Actor implements Disposable{
private Texture lassers;
private TextureRegion lasser;
private float speed = 5f;
private Sound shotSound;
	
	public LaserActor(float x, float y){
		lassers = new Texture("lasers.png");
		lasser = new TextureRegion(lassers,0, 0, lassers.getWidth()/2, lassers.getHeight()/8);
		setX(x); setY(y);
		setWidth(lasser.getRegionWidth());
		setHeight(lasser.getRegionHeight());
		setScale(0.5f);
		setRotation(180);
		shotSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laserShot.wav"));
		shotSound.play();
	}
	
	@Override
	public void dispose(){
		lassers.dispose();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(lasser, getX() + getWidth()*getScaleX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(isOnScreen())
			setX(getX()+speed);
		else
			this.dispose();
	}
	
	private boolean isOnScreen(){
		float posX = getX() + speed;
		return posX > 0 || posX < this.getStage().getWidth();
	}
	
	
}
