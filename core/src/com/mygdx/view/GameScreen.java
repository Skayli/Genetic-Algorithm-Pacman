package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.controllers.PacmanController;
import com.mygdx.model.PacmanGame;
import com.mygdx.model.World;

public class GameScreen implements Screen {
	private World world;
	private WorldRenderer worldRenderer;
	private OrthographicCamera camera;
	PacmanGame game;

	public GameScreen(PacmanGame game) {
		this.game = game;
		this.world = new World();
		this.worldRenderer = new WorldRenderer(this.world);
		
		PacmanController controller = new PacmanController(this);
		Gdx.input.setInputProcessor(controller);
		
		this.camera = new OrthographicCamera();
	
	}
	
	public World getWorld() {
		return this.world;
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
		
		if(this.world.getPacGumList().isEmpty()) {
			this.game.setScreen(new EndGameScreen(game, world));
		}
		this.worldRenderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		this.worldRenderer.setPpuX(width/(float)this.world.getWidth());
		this.worldRenderer.setPpuY(height/(float)this.world.getHeight());
		
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
