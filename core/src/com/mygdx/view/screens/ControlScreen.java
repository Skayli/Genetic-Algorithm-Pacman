package com.mygdx.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.model.PacmanGame;

public class ControlScreen extends CustomScreen {
	
	Label currentGenAgentLabel;

	public ControlScreen(PacmanGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	public void show() {
		
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        currentGenAgentLabel.setText("Génération " + (game.getWorld().getCurrentGenerationNumber()+1) + " |  Pacman " + (game.getWorld().getCurrentAgentNumber()+1));
		    
	    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
	    stage.draw(); 
	}
	
	private boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    if (str.isEmpty()) {
	        return false;
	    }
	    int length = str.length();
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}

	@Override
	public void createElements() {
		Label titleLabel = new Label("Panneau de contrôle", skin2);
		
		currentGenAgentLabel = new Label("", skin);
		
		Label targetGenLabel = new Label("Jouer jusqu'a la génération", skin);
		final TextField targetGenTextField = new TextField("", skin);
		targetGenTextField.setFocusTraversal(false);
		
		TextButton playButton = new TextButton("Jouer", skin);
			
		table.add(titleLabel).colspan(2);
		table.row().pad(30, 0, 10, 0);
		table.add(currentGenAgentLabel).colspan(2);
		table.row().pad(10,0,10,0);
		table.add(targetGenLabel).pad(0,0,0,10);
		table.add(targetGenTextField);
		table.row().pad(10, 0, 0, 0);
		table.add(playButton).fill().uniformX().colspan(2);
		
		// Applique un filtre pour accepter seulement les chiffres
		targetGenTextField.setTextFieldFilter(new TextFieldFilter() {
			char[] accepted = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
			public boolean acceptChar(TextField textField, char c) {
			    for (char a : accepted)
			        if (a == c) return true;
			    return false;
			}
		});
		
		targetGenTextField.setTextFieldListener(new TextFieldListener() {
			@Override
			public void keyTyped(TextField textField, char c) {
				if(c == '\r') {
					String s = targetGenTextField.getText();
					
					if(isInteger(s)) {
						int target = Integer.parseInt(s);
						target--;
						
						if(target > game.getWorld().getCurrentGenerationNumber()) {
							targetGenTextField.setText("");
							game.setGenerationProgressionScreen(target);
						}
					}
				}
			}
		});
		
		playButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setGameScreen();
			}
		});
		
	}
}
