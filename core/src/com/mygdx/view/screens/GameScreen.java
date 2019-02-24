package com.mygdx.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.model.PacmanGame;
import com.mygdx.model.World;
import com.mygdx.view.WorldRenderer;
import com.mygdx.view.textures.TextureFactory;

public class GameScreen implements Screen {
	private WorldRenderer worldRenderer;
	private OrthographicCamera camera;
	PacmanGame game;

	public GameScreen(PacmanGame game) {
		this.game = game;
		this.worldRenderer = new WorldRenderer(this.game.getWorld());
		
		this.camera = new OrthographicCamera();
	
	}
	
	public World getWorld() {
		return this.game.getWorld();
	}
	
	
	@Override
	public void show() {
		TextureFactory.reset();		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		worldRenderer.getSpriteBatch().setProjectionMatrix(camera.combined);
		worldRenderer.getShape().setProjectionMatrix(camera.combined);
		
		this.worldRenderer.render(delta);
		this.game.getWorld().play(delta);
		
		// Retour à l'écran de controle quand pacman meurt
		if(game.getWorld().getPacman().isDead()) {
			game.getWorld().initNextPacman();
			game.setControlScreen();
		}
	}

	@Override
	public void resize(int width, int height) {
		this.worldRenderer.setPpuX(width/(float)this.game.getWorld().getWidth());
		this.worldRenderer.setPpuY(height/(float)this.game.getWorld().getHeight());
		
		camera.setToOrtho(false, width, height);
		camera.position.set(width/2, height/2, 0);
		camera.update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		worldRenderer.getSpriteBatch().dispose();
	}
	
	public PacmanGame getGame() {
		return this.game;
	}

}
