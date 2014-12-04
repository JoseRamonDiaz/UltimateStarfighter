package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.ultimateStarfighter.game.services.Services;

public class ShipActor extends Actor implements Disposable{
	public static final String RED_ENEMY_SHIP = "Enemy/Hue Shifted/eSpritesheet_40x30_hue1.png";
	public static final String PURPLE_ENEMY_SHIP = "Enemy/Hue Shifted/eSpritesheet_40x30_hue2.png";
	public static final String BLUE_ENEMY_SHIP = "Enemy/Hue Shifted/eSpritesheet_40x30_hue3.png";
	public static final String YELLOW_ENEMY_SHIP = "Enemy/Hue Shifted/eSpritesheet_40x30_hue4.png";
	private int x = 100, y = 100;
	private int numOfImgs = 4;
	private Animation shipAnimation;
	private float duration = 0;
	private String path = "Ship/Spritesheet_64x29.png";
	private float animSpeed = 0.07f;
	
	public ShipActor(){
		shipAnimation = new Animation(animSpeed, Services.getImgVector(path, numOfImgs));
	}
	
	public ShipActor(String path, int x, int y){
		this.path = path;
		this.x = x;
		this.y = y;
		numOfImgs = 6;
		animSpeed = 0.05f;
		shipAnimation = new Animation(this.animSpeed, Services.getImgVector(this.path, numOfImgs));
	}
	
	@Override
	public void dispose(){
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		TextureRegion frame = shipAnimation.getKeyFrame(duration, true);
		batch.draw(frame, x, y);
	}
	
	@Override
	public void act(float delta) {
		duration += delta;
		super.act(delta);
	}
}
