package com.mygdx.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.controllers.PacmanController;
import com.mygdx.view.GameScreen;
import com.mygdx.view.TitleScreen;

public class PacmanGame extends Game {
	FPSLogger fpsLogger;
	
	@Override
	public void create () {
		fpsLogger = new FPSLogger();
		setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		super.render();		
		fpsLogger.log();
	}
	
	@Override
	public void dispose () {
		
	}
}
