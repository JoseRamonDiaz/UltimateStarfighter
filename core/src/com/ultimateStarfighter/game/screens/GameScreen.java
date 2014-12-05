package com.ultimateStarfighter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ultimateStarfighter.game.UltimateStarfighterGame;
import com.ultimateStarfighter.game.control.Control;
import com.ultimateStarfighter.game.model.GameBGActor;
import com.ultimateStarfighter.game.model.GameScrollBgActor;
import com.ultimateStarfighter.game.model.LaserActor;
import com.ultimateStarfighter.game.model.ShipActor;

public class GameScreen extends USFScreen{
	
	private Stage stage;
	private GameBGActor gameBgActor;
	private GameScrollBgActor gameScrollBgActor;
	private ShipActor shipActor;
	private ShipActor enemyShipActor;
	private Skin skin = new Skin(Gdx.files.internal("uiskin/uiskin.json")); 
	Touchpad tp;
	Control control = Control.getControl();
	private TextButton btnFire;
	private LaserActor laser;
	private long lastShotTime = 0;
	private int shotsPerSecond = 3, nanoSeconds = 1000000000;
	private Array<LaserActor> shottedLasers = new Array();
	private Array<ShipActor> shipActors = new Array();
	
	
	public GameScreen(UltimateStarfighterGame game) {
		super(game);
		stage = new Stage(new ScreenViewport());
		gameBgActor = new GameBGActor();
		gameBgActor.setSize(stage.getWidth(), stage.getHeight());
		gameScrollBgActor = new GameScrollBgActor();
		gameScrollBgActor.setSize(stage.getWidth(), stage.getHeight());
		shipActor = new ShipActor();
		enemyShipActor = new ShipActor(ShipActor.RED_ENEMY_SHIP, 200, 250);
		shipActors.add(enemyShipActor);
		Gdx.input.setInputProcessor(stage);
		stage.addActor(gameBgActor);
		stage.addActor(gameScrollBgActor);
		stage.addActor(shipActor);
		stage.addActor(enemyShipActor);
		shipActors.add(new ShipActor(ShipActor.BLUE_ENEMY_SHIP, 400, 100));
		stage.addActor(shipActors.peek());
		shipActors.add(new ShipActor(ShipActor.PURPLE_ENEMY_SHIP, 350, 280));
		stage.addActor(shipActors.peek());
		shipActors.add(new ShipActor(ShipActor.YELLOW_ENEMY_SHIP, 280, 300));
		stage.addActor(shipActors.peek());
		tp = new Touchpad(10,skin);
		stage.addActor(tp);
		tp.setBounds(15, 15, 100, 100);
		btnFire = new TextButton("Fire", skin);
		btnFire.setSize(100, 100);
		btnFire.setPosition(stage.getWidth() - btnFire.getWidth() - 15, 15);
		
		btnFire.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				shot();
			}
			
		});
		
		stage.addActor(btnFire);
		
	}

	@Override
	public void render(float delta){
		stage.draw();
		stage.act();
		checkOverlaps();
		control.setPercentX(tp.getKnobPercentX());
		control.setPercentY(tp.getKnobPercentY());
	}
	
	private void shot(){
		if(TimeUtils.timeSinceNanos(lastShotTime) > (nanoSeconds/shotsPerSecond)){
			lastShotTime = TimeUtils.nanoTime();
			laser = new LaserActor(shipActor.getX()+shipActor.getWidth(), shipActor.getY()+shipActor.getHeight()/1.3f);
			shottedLasers.add(laser);
			stage.addActor(laser);
		}
	}
	
	private void checkOverlaps(){
		//Movelo a la clase laseractor
		for(ShipActor enemyShip :shipActors){
			for(LaserActor laser : shottedLasers){
				Rectangle shipRect = new Rectangle(enemyShip.getX(), enemyShip.getY(), enemyShip.getWidth(), enemyShip.getHeight());
				Rectangle laserRect = new Rectangle(laser.getX() - laser.getWidth()*laser.getScaleX(), laser.getY() - 8, laser.getWidth(), laser.getHeight()*0.04f);
				if(Intersector.overlaps(shipRect, laserRect)){
					System.out.println("Overlap");
					exploteShip(enemyShip, laser);
				}
			}
		}
//		if(shottedLasers.size != 0 && shipActors.size != 0){
//		ShipActor enemyShip = shipActors.peek();
//		Rectangle shipRect = new Rectangle(enemyShip.getX(), enemyShip.getY(), enemyShip.getWidth(), enemyShip.getHeight());
//		
//		LaserActor laser = shottedLasers.peek();
//		Rectangle laserRect = new Rectangle(laser.getX() - laser.getWidth()*laser.getScaleX(), laser.getY() - 8, laser.getWidth(), laser.getHeight()*0.04f);
//		
//		if(Intersector.overlaps(shipRect, laserRect)){
//			System.out.println("Overlap");
//			exploteShip(enemyShip, laser);
//		}
//		}
	}
	
	private void exploteShip(ShipActor ship, LaserActor laser){
		ship.remove();
		shipActors.removeValue(ship, false);
		ship.dispose();
		
		laser.remove();
		shottedLasers.removeValue(laser, false);
		laser.dispose();
	}
	
}
