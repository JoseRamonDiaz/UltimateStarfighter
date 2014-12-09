package com.ultimateStarfighter.game.model;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.ultimateStarfighter.game.services.PreferencesHelper;

public class EnemyShipGenerator {
	private Array<ShipActor> newShips;
	private float shipFrecPerSec = 1;
	private float nano = 100000000;
	private boolean isNewShips;
	private long lastGenTime;
	Random rand;
	private Integer stageWidth, stageHeight;
	private float generateDelayTime = 10f - 2.5f * PreferencesHelper
			.getDificulty();

	public EnemyShipGenerator(int stageWidth, int stageHeight) {
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
		rand = new Random();
		lastGenTime = TimeUtils.nanoTime();
		generateShips();
	}

	public Array<ShipActor> getNewShips() {
		isNewShips = false;
		return newShips;
	}

	private void generateShips() {
		if (((TimeUtils.timeSinceNanos(lastGenTime) / nano)) > generateDelayTime) {
			newShips = new Array<ShipActor>();

			for (int i = 0; i < shipFrecPerSec; i++) {
				newShips.add(getRandomEnemyShip());
			}

			lastGenTime = TimeUtils.nanoTime();
			isNewShips = true;
		}
	}

	private ShipActor getRandomEnemyShip() {
		String path = getPath();
		return new ShipActor(path, stageWidth + ShipActor.X_OFFSET,
				rand.nextInt(stageHeight));
	}

	private String getPath() {
		int shipColor = rand.nextInt(3);
		switch (shipColor) {
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

	public boolean isNewShips() {
		generateShips();
		return isNewShips;
	}
}
