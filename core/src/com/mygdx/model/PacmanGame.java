package com.mygdx.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.view.screens.ControlScreen;
import com.mygdx.view.screens.GameScreen;
import com.mygdx.view.screens.GenerationProgressionScreen;
import com.mygdx.view.screens.TitleScreen;

public class PacmanGame extends Game {
	FPSLogger fpsLogger;
	World world;
	
	private TitleScreen titleScreen;
	private GameScreen gameScreen;
	private ControlScreen controlScreen;
	public GenerationProgressionScreen generationProgressionScreen;
	
	@Override
	public void create () {		
		fpsLogger = new FPSLogger();
		world = new World();
		setTitleScreen();
	}

	@Override
	public void render () {
		super.render();		
		//fpsLogger.log();
	}
	
	@Override
	public void dispose () {
		
	}
	
	public void setTitleScreen() {
		setScreen(new TitleScreen(this));
	}
	
	public void setGameScreen() {
		setScreen(new GameScreen(this));
	}
	
	public void setControlScreen() {
		setScreen(new ControlScreen(this));
	}
	
	public void setGenerationProgressionScreen(int target) {
		setScreen(new GenerationProgressionScreen(this, target));
	}
	
	public void setCreditsScreen() {
		System.out.println("A creer : screen crédits");		
	}

	public World getWorld() {
		// TODO Auto-generated method stub
		return world;
	}


}
