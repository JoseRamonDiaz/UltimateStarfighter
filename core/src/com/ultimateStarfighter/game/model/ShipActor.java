package com.ultimateStarfighter.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.ultimateStarfighter.game.control.Control;
import com.ultimateStarfighter.game.services.Services;

public class ShipActor extends Actor implements Disposable{
	public static final String RED_ENEMY_SHIP = "Enemy/Hue Shifted/eSpritesheet_40x30_hue1.png";
	public static final String PURPLE_ENEMY_SHIP = "Enemy/Hue Shifted/eSpritesheet_40x30_hue2.png";
	public static final String BLUE_ENEMY_SHIP = "Enemy/Hue Shifted/eSpritesheet_40x30_hue3.png";
	public static final String YELLOW_ENEMY_SHIP = "Enemy/Hue Shifted/eSpritesheet_40x30_hue4.png";
	private int numOfImgs = 4;
	private Animation shipAnimation;
	private float duration = 0;
	private String path = "Ship/Spritesheet_64x29.png";
	private float animSpeed = 0.07f;
	private boolean isEnemyShip;
	Control control = Control.getControl();
	private float speed = 2f;
	private TextureRegion frame ;
	private Sound shipEngineSound;
	public final static int X_OFFSET = -30;
	private float engineSoundVol = 0.9f;
	
	public ShipActor(){
		shipAnimation = new Animation(animSpeed, Services.getImgVector(path, numOfImgs));
		isEnemyShip = false;
		setX(100);
		setY(100);
		loadFrame();
		shipEngineSound = Gdx.audio.newSound(Gdx.files.internal("sounds/spaceShipEngine.wav"));

	}
	
	public ShipActor(String path, int x, int y){
		this.path = path;
		setX(x);
		setY(y);
		numOfImgs = 6;
		animSpeed = 0.05f;
		shipAnimation = new Animation(this.animSpeed, Services.getImgVector(this.path, numOfImgs));
		isEnemyShip = true;
		loadFrame();
	}
	
	@Override
	public void dispose(){
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		frame = shipAnimation.getKeyFrame(duration, true);
		batch.draw(frame, getX(), getY());
	}
	
	@Override
	public void act(float delta) {
		duration += delta;
		super.act(delta);
		if(!isEnemyShip){
			updateShipPos();
		}else{
			updateEnemyPos();
		}
	}
	
	private void updateEnemyPos() {
		if(getX() > X_OFFSET){
			setX(getX()-speed);
		}
	}

	private void updateShipPos(){
		float posX = getX() + (control.getPercentX() * speed);
		if(control.getPercentX()!= 0 || control.getPercentY() != 0){
			shipEngineSound.loop(engineSoundVol);
		}else{
			shipEngineSound.stop();
		}
		if(posX > 0 && posX < this.getStage().getWidth() - frame.getRegionWidth())
			setX(posX);
		float posY = getY() + (control.getPercentY() * speed);
		if(posY > 0 && posY < this.getStage().getHeight() - frame.getRegionHeight())
			setY(posY);
	}
	
	private void loadFrame(){
		frame = shipAnimation.getKeyFrame(duration, true);
		setWidth(frame.getRegionWidth());
		setHeight(frame.getRegionHeight());
	}
	
	public int getXOffset(){
		return X_OFFSET;
	}
}
