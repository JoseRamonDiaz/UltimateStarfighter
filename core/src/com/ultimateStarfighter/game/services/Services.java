package com.ultimateStarfighter.game.services;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Services {
	
	public static TextureRegion[] getImgVector(String path, int numOfImgs){
		Texture texture = new Texture(path);
		TextureRegion shipRegion = new TextureRegion(texture, texture.getWidth(), texture.getHeight());
		TextureRegion[][] temp = shipRegion.split(texture.getWidth(), texture.getHeight()/numOfImgs);
		TextureRegion[] textureFrames = new TextureRegion[numOfImgs];
		for(int i = 0; i < numOfImgs; i++){
			textureFrames[i] = temp[i][0];
		}
		return textureFrames;
	}
}
