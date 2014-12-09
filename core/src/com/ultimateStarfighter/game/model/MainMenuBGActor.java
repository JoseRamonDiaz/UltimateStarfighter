package com.ultimateStarfighter.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class MainMenuBGActor extends Actor implements Disposable {
	private Texture bg;

	public MainMenuBGActor() {
		bg = new Texture("mainMenuBG.jpg");
	}

	@Override
	public void dispose() {
		bg.dispose();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bg, 0, 0, getWidth(), getHeight());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

}
