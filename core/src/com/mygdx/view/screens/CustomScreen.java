package com.mygdx.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.model.PacmanGame;

public abstract class CustomScreen implements Screen {
	
	protected PacmanGame game;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	protected Stage stage;
	protected Table table;
	
	protected BitmapFont normalFont;
	protected BitmapFont titleFont;
	
	protected Skin skin;
	protected Skin skin2;
	
	public CustomScreen(PacmanGame game) {
		createFonts();
		this.game = game;
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		skin.add("font", normalFont, BitmapFont.class);
		skin.add("font-over", normalFont, BitmapFont.class);
		skin.add("font-pressed", normalFont, BitmapFont.class);
		skin.addRegions(new TextureAtlas(Gdx.files.internal("skins/neon/skin/neon-ui.atlas")));
		skin.load(Gdx.files.internal("skins/neon/skin/neon-ui.json"));
		
		skin2 = new Skin();
		skin2.add("font", titleFont, BitmapFont.class);
		skin2.add("font-over", titleFont, BitmapFont.class);
		skin2.add("font-pressed", titleFont, BitmapFont.class);
		skin2.addRegions(new TextureAtlas(Gdx.files.internal("skins/neon/skin/neon-ui.atlas")));
		skin2.load(Gdx.files.internal("skins/neon/skin/neon-ui.json"));
		
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		createElements();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);        
        
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    	stage.getViewport().update(width,  height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    	System.out.println("dispose");
       stage.dispose();
    }
    
    private void createFonts() {
        FileHandle fontFile = Gdx.files.internal("data/Roboto-Bold.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 18;
        parameter.color = Color.WHITE;
        normalFont = generator.generateFont(parameter);
        parameter.size = 36;
        titleFont = generator.generateFont(parameter);
        generator.dispose();
    }
    
    public abstract void createElements();

}
