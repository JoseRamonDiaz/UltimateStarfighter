package com.ultimateStarfighter.game.model;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class EnemyShipGenerator {
	private Array<ShipActor> newShips;
	private float shipFrecPerSec = 1;
	private float nano = 1000000000;
	private boolean isNewShips;
	private long lastGenTime;
	Random rand;
	private Integer stageWidth, stageHeight;
	
	public EnemyShipGenerator(int stageWidth, int stageHeight){
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
//		newShips = new Array<ShipActor>();
		rand = new Random();
		lastGenTime = TimeUtils.nanoTime();
		generateShips();
	}
	
	public Array<ShipActor> getNewShips(){
		isNewShips = false;
		return newShips;
	}
	
	private void generateShips(){
		if(((TimeUtils.timeSinceNanos(lastGenTime)/nano)) > 1){
			newShips = new Array<ShipActor>();
			
			for(int i = 0 ; i < shipFrecPerSec; i++){
				newShips.add(getRandomEnemyShip());
			}
			
			lastGenTime = TimeUtils.nanoTime();
			isNewShips = true;
		}
	}
	
	private ShipActor getRandomEnemyShip() {
		String path = getPath();
		//Hay que cambiarlo para que aparezcan mas atras de la pantalla
		return new ShipActor(path, stageWidth + ShipActor.X_OFFSET, rand.nextInt(stageHeight));
	}
	
	private String getPath(){
		int shipColor = rand.nextInt(3);
		switch(shipColor){
		case 0:
			return ShipActor.BLUE_ENEMY_SHIP;
			
		case 1:
			return ShipActor.PURPLE_ENEMY_SHIP;
			
		case 2:
			return ShipActor.RED_ENEMY_SHIP;
			
		default:
			return ShipActor.YELLOW_ENEMY_SHIP;
		}
		
	}

	public boolean isNewShips(){
		generateShips();
		return isNewShips;
	}
}
