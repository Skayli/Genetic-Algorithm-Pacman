package com.mygdx.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.model.PacmanGame;

public class GenerationProgressionScreen extends CustomScreen {
	
	Label generation;
	Label agent;
	
	int targetGeneration;
	
	public GenerationProgressionScreen(PacmanGame game, int target) {
		super(game);
		this.targetGeneration = target;
	}
	
	public void show() {
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        while(!game.getWorld().getPacman().isDead() ) {
        	for(int i = 0; i < 10000; i++) {
        		game.getWorld().play(delta);
        		
        		if(game.getWorld().getPacman().isDead() || game.getWorld().getPacGumList().size() == 0) {
        			break;
        		}
        	}
        }
        
        game.getWorld().initNextPacman();
        
        if(game.getWorld().getCurrentGenerationNumber() == targetGeneration) {
        	game.setControlScreen();
        } else {
        
		    String currentGen = String.valueOf(game.getWorld().getCurrentGenerationNumber()+1);
		    String currentAgent = String.valueOf(game.getWorld().getCurrentAgentNumber()+1);
		    
		    generation.setText("Génération N°" + currentGen);
		    agent.setText("Pacman N°" + currentAgent);
		    
		    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		    stage.draw();
        }
 
	}

	@Override
	public void createElements() {
		Label title = new Label("Progression de la génération des pacmans", skin);
		generation = new Label("Generation N° ", skin);
		agent = new Label("Pacman N°", skin);
			
		table.add(title);
		table.row().pad(20, 0, 10, 0);
		table.add(generation);
		table.row().pad(0, 0, 0, 0);
		table.add(agent);
		
	}
}
