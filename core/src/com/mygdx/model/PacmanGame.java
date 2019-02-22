package com.mygdx.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.view.screens.GameScreen;
import com.mygdx.view.screens.GenerationProgressionScreen;
import com.mygdx.view.screens.TitleScreen;

public class PacmanGame extends Game {
	FPSLogger fpsLogger;
	World world;
	
	private TitleScreen titleScreen;
	private GameScreen gameScreen;
	
	public final static int TITLE = 0;
	public final static int GAME = 1;
	public final static int CREDITS = 2;
	
	@Override
	public void create () {		
		fpsLogger = new FPSLogger();
		world = new World();
		
		titleScreen = new TitleScreen(this);
		
		setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		super.render();		
		//fpsLogger.log();
	}
	
	@Override
	public void dispose () {
		
	}
	
	public void changeScreen(int screen) {
		switch(screen) {
			case TITLE:
				setScreen(titleScreen);
				break;
			case GAME:
				if(gameScreen == null) gameScreen = new GameScreen(this);
				setScreen(gameScreen);
				break;
			case CREDITS:
				System.out.println("FAIRE L'écran de credits");
				break;
		}
	}

	public World getWorld() {
		// TODO Auto-generated method stub
		return world;
	}
}
