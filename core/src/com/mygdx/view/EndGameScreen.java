package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.mygdx.controllers.EndGameScreenController;
import com.mygdx.model.AudioFactory;
import com.mygdx.model.GameElement;
import com.mygdx.model.PacmanGame;
import com.mygdx.model.World;

public class EndGameScreen extends GameScreen {

	PacmanGame game;
	World world;
	
	SpriteBatch batch;
	OrthographicCamera camera;
	
	public EndGameScreen(PacmanGame game, World world) {
		super(game);
		this.world = world;
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		EndGameScreenController controller = new EndGameScreenController(this);
		Gdx.input.setInputProcessor(controller);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		AudioFactory.getInstance().stopMusic("siren");
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.enableBlending();
		batch.begin();
		BitmapFont font = new BitmapFont();

		
		String fontText = "Score atteint";
		String score = String.valueOf(this.world.getScore());
		String touches = "Appuyez sur ENTRER pour rejouer ou ECHAP pour revenir au menu principal";
		
		GlyphLayout gl = new GlyphLayout();
	
		gl.setText(font, fontText);
		font.draw(batch, gl, (Gdx.graphics.getWidth() - gl.width)/2 , (Gdx.graphics.getHeight() + gl.height*3)/2);
		
		gl.setText(font, score);
		font.draw(batch, gl, (Gdx.graphics.getWidth() - gl.width)/2 , Gdx.graphics.getHeight()/2);
		
		gl.setText(font, touches);
		font.draw(batch, gl, (Gdx.graphics.getWidth()- gl.width)/2 , gl.height*2);
			
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
