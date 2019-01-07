package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.controllers.EndGameScreenController;
import com.mygdx.controllers.TitleScreenController;
import com.mygdx.model.PacmanGame;

public class TitleScreen extends GameScreen {
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public TitleScreen(PacmanGame game) {
		super(game);
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		TitleScreenController controller = new TitleScreenController(this);
		Gdx.input.setInputProcessor(controller);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		
		batch.enableBlending();
		
		batch.begin();
		
		BitmapFont font = new BitmapFont();
		font.setColor(Color.WHITE);
		String title = "Pacman (version minimaliste)";
		String touche = "Appuyez sur ENTRER pour commencer une partie";
		String credits = "Réalisé par Quentin NADE & Matthieu HEMMERLIN - L3 - 2017/2018";
		
		GlyphLayout gl = new GlyphLayout();
		
		gl.setText(font, title);
		font.draw(batch, gl, (Gdx.graphics.getWidth() - gl.width)/2,(Gdx.graphics.getHeight() + gl.height*2)/2);
		
		gl.setText(font, touche);
		font.draw(batch, gl, (Gdx.graphics.getWidth() - gl.width)/2,(Gdx.graphics.getHeight() - gl.height*5)/2);
		
		gl.setText(font, credits);
		font.draw(batch, gl, (Gdx.graphics.getWidth() - gl.width)/2,gl.height*2);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	
}
