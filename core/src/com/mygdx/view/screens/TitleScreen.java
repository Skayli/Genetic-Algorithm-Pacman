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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.model.PacmanGame;

public class TitleScreen extends CustomScreen {
	 

	
	public TitleScreen(PacmanGame game) {
		super(game);
	}

	@Override
    public void show() {
		Table table = new Table();
		table.setFillParent(true);
//		table.setDebug(true);
		stage.addActor(table);
		
		Skin skin = new Skin();
		skin.add("font", normalFont, BitmapFont.class);
		skin.add("font-over", normalFont, BitmapFont.class);
		skin.add("font-pressed", normalFont, BitmapFont.class);
		
		skin.addRegions(new TextureAtlas(Gdx.files.internal("skins/neon/skin/neon-ui.atlas")));
		skin.load(Gdx.files.internal("skins/neon/skin/neon-ui.json"));

		
		Label title = new Label("Pacman & algorithme génétique", skin);		
		TextButton simulationButton = new TextButton("Lancer la simulation", skin);
		TextButton creditsButton = new TextButton("Crédits", skin);
		TextButton exitButton = new TextButton("Quitter", skin);
		
		table.add(title).colspan(2);
		table.row().pad(10, 0, 10, 0);
		table.add(simulationButton).fill().uniformX();
		table.add(creditsButton).fill().uniformX();
		table.row().pad(15, 0, 0, 0);
		table.add(exitButton).fill().uniformX().colspan(2);		
		
		simulationButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.changeScreen(PacmanGame.GAME);
			}
		});
		
		creditsButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.changeScreen(PacmanGame.CREDITS);
			}
		});
		
		exitButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		
		
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
       stage.dispose();
    }
    
    private void createFonts() {
        FileHandle fontFile = Gdx.files.internal("data/Roboto-Bold.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 18;
        parameter.color = Color.WHITE;
        normalFont = generator.generateFont(parameter);
        generator.dispose();
    }
   
}
