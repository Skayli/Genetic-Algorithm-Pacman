package com.mygdx.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.model.Settings;
import com.mygdx.view.GameScreen;

public class PacmanController implements InputProcessor {

	private ArrayList<Integer> codes;
	private String car;
	private String msg;
	private GameScreen gameScreen;
	
	public PacmanController(GameScreen gameScreen) {
		codes = new ArrayList();
		car = "";
		this.gameScreen = gameScreen;
		
		update();
	}

	private void update() {
		msg = "";
		for(Object c : codes) {
			msg += c + " ";
		}
		
		msg += car;
		
	}
	
	public String getMsg() {
		return msg;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.LEFT || keycode == Keys.RIGHT || keycode == Keys.UP || keycode == Keys.DOWN) {
			gameScreen.getWorld().getPacman().changeWantedDirection(keycode);
		}
		
//		if(keycode == Keys.SPACE)
//			if(gameScreen.getWorld().getPacman().getSpeed() == 0) {
//				gameScreen.getWorld().getPacman().setSpeed(.15);
//			} else {
//				gameScreen.getWorld().getPacman().setSpeed(0);
//			}

		
		codes.add(new Integer(keycode));
		update();
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		codes.remove(new Integer(keycode));
		update();
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		car = Character.toString(character);
		update();
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
