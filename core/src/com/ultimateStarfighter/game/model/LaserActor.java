package com.ultimateStarfighter.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.ultimateStarfighter.game.services.PreferencesHelper;

public class LaserActor extends Actor implements Disposable {
	private Texture lassers;
	private TextureRegion lasser;
	private float speed = 5f + 2 * PreferencesHelper.getDificulty();
	private Sound shotSound;
	private boolean isEnemyLaser;
	private final float laserVolume = PreferencesHelper.getVolume();

	public LaserActor(float x, float y) {
		lassers = new Texture("lasers.png");
		lasser = new TextureRegion(lassers, 0, 0, lassers.getWidth() / 2,
				lassers.getHeight() / 8);
		setX(x);
		setY(y);
		setWidth(lasser.getRegionWidth());
		setHeight(lasser.getRegionHeight());
		setScale(0.5f);
		setRotation(180);
		shotSound = Gdx.audio.newSound(Gdx.files
				.internal("sounds/laserShot.wav"));
		shotSound.play(laserVolume);
	}

	public LaserActor(float x, float y, boolean isEnemyLaser) {
		this.isEnemyLaser = isEnemyLaser;
		lassers = new Texture("lasers.png");
		lasser = new TextureRegion(lassers, 0, 0, lassers.getWidth() / 2,
				lassers.getHeight() / 8);
		setX(x);
		setY(y);
		setWidth(lasser.getRegionWidth());
		setHeight(lasser.getRegionHeight());
		setScale(0.5f);
	}

	@Override
	public void dispose() {
		lassers.dispose();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (!isEnemyLaser)
			batch.draw(lasser, getX() + getWidth() * getScaleX(), getY(),
					getOriginX(), getOriginY(), getWidth(), getHeight(),
					getScaleX(), getScaleY(), getRotation());
		else
			batch.draw(lasser, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (!isEnemyLaser) {
			if (isOnScreen())
				setX(getX() + speed);
			else
				this.remove();
		} else {
			if (isOnScreen())
				setX(getX() - speed);
			else
				this.remove();
		}
	}

	private boolean isOnScreen() {
		float posX = getX() + speed;
		return posX > 0 && posX < this.getStage().getWidth();
	}

}
