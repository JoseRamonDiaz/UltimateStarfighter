package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ultimateStarfighter.game.UltimateStarfighterGame;
import com.ultimateStarfighter.game.model.MainMenuBGActor;
import com.ultimateStarfighter.game.services.PreferencesHelper;

public class OptionScreen extends USFScreen {

	private Stage stage;
	private MainMenuBGActor mainMenuBGActor;
	private Skin skin;

	private String[] dificulties = { "Easy", "Medium", "Hard" };

	public OptionScreen(UltimateStarfighterGame game) {
		super(game);
		final Table table = new Table();
		stage = new Stage(new ScreenViewport());
		skin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));

		Gdx.input.setInputProcessor(stage);
		mainMenuBGActor = new MainMenuBGActor();
		mainMenuBGActor.setSize(stage.getWidth(), stage.getHeight());

		Label lblDificulty = new Label("Dificulty", skin);
		Label lblVol = new Label("Volume", skin);

		final SelectBox<String> dificultySelectBox = new SelectBox<String>(skin);
		Array<String> dificultyItems = new Array<String>();
		dificultyItems.addAll(dificulties);
		dificultySelectBox.setItems(dificultyItems);

		final Slider volSlider = new Slider(0, 1, 0.1f, false, skin);

		final TextButton btnBack = new TextButton("Back", skin);
		btnBack.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				savePreferences(volSlider.getValue(),
						dificultySelectBox.getSelectedIndex());
				changeToMainMenuScreen();
			}

		});

		final TextButton btnDefault = new TextButton("Default", skin);
		btnDefault.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				dificultySelectBox
						.setSelectedIndex(PreferencesHelper.DEFAULT_DIFICULTY);
				volSlider.setValue(PreferencesHelper.DEFAULT_VOLUME);
			}

		});

		loadPreferences(volSlider, dificultySelectBox);

		table.setFillParent(true);
		table.add(lblDificulty).fill().space(10);
		table.add(dificultySelectBox).fill().space(10).row();
		table.add(lblVol).fill().space(10);
		table.add(volSlider).fill().space(10).row();
		table.add(btnDefault).fill().space(10).colspan(2).row();
		table.add(btnBack).fill().space(10).colspan(2).row();

		stage.addActor(mainMenuBGActor);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	private void changeToMainMenuScreen() {
		game.setScreen(new MainMenuScreen(game));
		this.dispose();
	}

	private void savePreferences(float volume, int dificulty) {

		PreferencesHelper.setVolume(volume);
		PreferencesHelper.setDificulty(dificulty);
	}

	private void loadPreferences(Slider volSlider,
			SelectBox<String> dificultySelectBox) {

		volSlider.setValue(PreferencesHelper.getVolume());
		dificultySelectBox.setSelectedIndex(PreferencesHelper.getDificulty());
	}

	@Override
	public void render(float delta) {
		stage.draw();
		stage.act();
	}

}
