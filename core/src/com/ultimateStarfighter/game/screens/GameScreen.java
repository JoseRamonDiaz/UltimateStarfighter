package com.ultimateStarfighter.game.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ultimateStarfighter.game.UltimateStarfighterGame;
import com.ultimateStarfighter.game.control.Control;
import com.ultimateStarfighter.game.model.EnemyShipGenerator;
import com.ultimateStarfighter.game.model.GameBGActor;
import com.ultimateStarfighter.game.model.GameScrollBgActor;
import com.ultimateStarfighter.game.model.LaserActor;
import com.ultimateStarfighter.game.model.ShipActor;

public class GameScreen extends USFScreen{
	
	private Stage stage;
	private GameBGActor gameBgActor;
	private GameScrollBgActor gameScrollBgActor;
	private ShipActor shipActor;
	private Skin skin = new Skin(Gdx.files.internal("uiskin/uiskin.json")); 
	private Touchpad tp;
	private Control control = Control.getControl();
	private TextButton btnFire;
	private LaserActor laser;
	private long lastShotTime = 0;
	private int shotsPerSecond = 3, nanoSeconds = 1000000000;
	private Array<LaserActor> shottedLasers = new Array<LaserActor>();
	private Array<ShipActor> enemyShips = new Array<ShipActor>();
	private EnemyShipGenerator enemyGen;
	private Music gameLoopMusic;
	private int score = 0;
	private Label lblScore;
	private Random rand = new Random();
	private Array<LaserActor> enemyShottedLasers;
	private boolean gameOver = false;
	private TextButton btnContinue;
	
	public GameScreen(UltimateStarfighterGame game) {
		super(game);
		enemyShottedLasers = new Array<LaserActor>();
		stage = new Stage(new ScreenViewport());
		enemyGen = new EnemyShipGenerator((int)stage.getWidth(), (int)stage.getHeight());
		gameBgActor = new GameBGActor();
		gameBgActor.setSize(stage.getWidth(), stage.getHeight());
		gameScrollBgActor = new GameScrollBgActor();
		gameScrollBgActor.setSize(stage.getWidth(), stage.getHeight());
		shipActor = new ShipActor();
		Gdx.input.setInputProcessor(stage);
		stage.addActor(gameBgActor);
		stage.addActor(gameScrollBgActor);
		
		tp = new Touchpad(10,skin);
		stage.addActor(tp);
		tp.setBounds(15, 15, 100, 100);
		btnFire = new TextButton("Fire", skin);
		btnFire.setSize(100, 100);
		btnFire.setPosition(stage.getWidth() - btnFire.getWidth() - 15, 15);
		setBtnFireListener();
		stage.addActor(btnFire);
		gameLoopMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/gameLoop.mp3"));
		gameLoopMusic.setLooping(true);
		gameLoopMusic.play();
		lblScore = new Label("Score: " + score, skin);
		lblScore.setPosition(15, stage.getHeight() - lblScore.getHeight());
		stage.addActor(shipActor);
		stage.addActor(lblScore);
	}
	
	@Override
	public void render(float delta){
		if(!gameOver){
			stage.draw();
			stage.act();
			checkOverlaps();
			checkEnemyOverlaps();
			checkShipOverlaps();
			disposeShipsOutOfScreen();
			if(enemyGen.isNewShips()){
				addNewEnemyShips();
			}
			control.setPercentX(tp.getKnobPercentX());
			control.setPercentY(tp.getKnobPercentY());
			lblScore.setText("Score: " + score);
			enemyShots(delta);
			disposeOutOfScreenLasers();
		}else{
			stage.addActor(gameBgActor);
			Table table = new Table();
			
			Label lblGameOver = new Label("Game Over", skin);
			table.setFillParent(true);
			table.add(lblGameOver).fill().space(10).row();
			
			btnContinue = new TextButton("Continue", skin);
			setBtnContinueListener();
			table.add(btnContinue).fill().space(10).row();
			
			stage.addActor(table);
			stage.draw();
		}
	}

	private void setBtnContinueListener() {
		btnContinue.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				stage.dispose();
				gameLoopMusic.stop();
				game.setScreen(new MainMenuScreen(game));
			}
			
		});
	}

	private void setBtnFireListener() {
		btnFire.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shot();
			}
		});
	}

	private void addNewEnemyShips() {
		//Solo se puede obtener una vez cada segundo las naves por la sincronizacion
		Array<ShipActor> newShips = enemyGen.getNewShips();
		enemyShips.addAll(newShips);
		for(ShipActor enemy: newShips){
			stage.addActor(enemy);
		}
	}

	private void shot(){
		if(TimeUtils.timeSinceNanos(lastShotTime) > (nanoSeconds/shotsPerSecond)){
			lastShotTime = TimeUtils.nanoTime();
			laser = new LaserActor(shipActor.getX()+shipActor.getWidth(), shipActor.getY()+shipActor.getHeight()/1.3f);
			shottedLasers.add(laser);
			stage.addActor(laser);
		}
	}
	
	private void enemyShots(float delta){
//		enemyShotsTimer += delta;
////		System.out.println("delta: " + enemyShotsTimer);
//		if(enemyShotsTimer > 3){
//			enemyShotsTimer = 0;
			for(ShipActor enemyShip : enemyShips){
				if(rand.nextInt(50) == 25){
					if(rand.nextBoolean()){
						LaserActor laser = new LaserActor(enemyShip.getX() - enemyShip.getWidth(), enemyShip.getY() + enemyShip.getHeight()/3f, true);
						enemyShottedLasers.add(laser);
						stage.addActor(laser);
					}
				}
			}
//		}
	}
	
	private void checkOverlaps(){
		for(ShipActor enemyShip :enemyShips){
			for(LaserActor laser : shottedLasers){
				Rectangle shipRect = new Rectangle(enemyShip.getX(), enemyShip.getY(), enemyShip.getWidth(), enemyShip.getHeight());
				Rectangle laserRect = new Rectangle(laser.getX() - laser.getWidth()*laser.getScaleX(), laser.getY() - 8, laser.getWidth(), laser.getHeight()*0.04f);
				if(Intersector.overlaps(shipRect, laserRect)){
					exploteShip(enemyShip, laser);
				}
			}
		}
	}
	
	private void checkEnemyOverlaps(){
		for(LaserActor laser : enemyShottedLasers){
			Rectangle shipRect = new Rectangle(shipActor.getX(), shipActor.getY(), shipActor.getWidth(), shipActor.getHeight());
			Rectangle laserRect = new Rectangle(laser.getX(), laser.getY(), laser.getWidth(), laser.getHeight()/3);
			if(Intersector.overlaps(shipRect, laserRect)){
				gameOver = true;
				stage.clear();
			}
		}
	}
	
	private void checkShipOverlaps(){
		for(ShipActor enemyShip : enemyShips){
			Rectangle shipRect = new Rectangle(shipActor.getX(), shipActor.getY(), shipActor.getWidth(), shipActor.getHeight());
			Rectangle enemyRect = new Rectangle(enemyShip.getX(), enemyShip.getY(), enemyShip.getWidth(), enemyShip.getHeight());
			if(Intersector.overlaps(shipRect, enemyRect)){
				gameOver = true;
				stage.clear();
			}
		}
	}
	
	private void exploteShip(ShipActor ship, LaserActor laser){
		score += 100;
		
		ship.remove();
		enemyShips.removeValue(ship, false);
		ship.dispose();
		
		laser.remove();
		shottedLasers.removeValue(laser, false);
		laser.dispose();
	}
	
	private void disposeShipsOutOfScreen(){
		for(ShipActor enemyShip :enemyShips){
			if(enemyShip.getX() < enemyShip.getXOffset()+1){
				disposeShip(enemyShip);
			}
		}
	}
	
	private void disposeOutOfScreenLasers(){
		for(LaserActor laser : shottedLasers){
			if(laser.getX() >= stage.getWidth() - 1){
				laser.remove();
				shottedLasers.removeValue(laser, false);
				laser.dispose();
			}
		}
		
		for(LaserActor laser : enemyShottedLasers){
			
			if(laser.getX() <= -5 ){
				laser.remove();
				enemyShottedLasers.removeValue(laser, false);
				laser.dispose();
			}
		}
	}
	
	private void disposeShip(ShipActor ship){
		ship.remove();
		enemyShips.removeValue(ship, false);
		ship.dispose();
	}
	
}
