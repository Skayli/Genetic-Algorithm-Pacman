package com.mygdx.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.view.GameScreen;
import com.mygdx.view.TitleScreen;

public class TitleScreenController implements InputProcessor{

	TitleScreen screen;
	
	public TitleScreenController(TitleScreen titleScreen) {
		this.screen = titleScreen;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.ENTER)
			screen.getGame().setScreen(new GameScreen(screen.getGame()));
		
		if(keycode == Keys.ESCAPE)
			Gdx.app.exit();
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
