package com.ultimateStarfighter.game.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesHelper {
	public static final float DEFAULT_VOLUME = 0.6f;
	public static final int DEFAULT_DIFICULTY = 1;

	private static final Preferences preferences = Gdx.app
			.getPreferences("usf_preferences");

	public static void setVolume(float volume) {
		if (volume > 1) {
			volume = 1f;
		} else if (volume < 0) {
			volume = 0f;
		}

		preferences.putFloat("volume", volume);
		preferences.flush();
	}

	public static float getVolume() {
		return preferences.getFloat("volume", DEFAULT_VOLUME);
	}

	public static void setDificulty(int dificulty) {
		preferences.putInteger("dificulty", dificulty);
		preferences.flush();
	}

	public static int getDificulty() {
		return preferences.getInteger("dificulty", DEFAULT_DIFICULTY);
	}

	public static void setHighScore(int score) {
		preferences.putInteger("highScore", score);
		preferences.flush();
	}

	public static int getHighScore() {
		return preferences.getInteger("highScore", 0);
	}
}
