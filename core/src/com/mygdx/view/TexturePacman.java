package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.model.Settings;
import com.mygdx.model.elements.moving.pacman.Pacman;

public class TexturePacman implements iTexturable {

	private Pacman pacman;
	//private Texture textureLeft, textureLeft2, textureRight, textureRight2, textureUp, textureUp2, textureDown, textureDown2;
	private double seuil; //le seuil est donné en milisecondes
	private double deltaT; //Le delta du render est en secondes !
	private Texture[][] allTextures;
	
	public TexturePacman(Pacman pacman, double seuil) {
		this.pacman  = pacman;
		
		allTextures = new Texture[4][3];
		
		allTextures[Settings.LEFT][0] = new Texture(Gdx.files.internal("images/pacman/pacmanLeft.png"));
		allTextures[Settings.LEFT][1] = new Texture(Gdx.files.internal("images/pacman/pacmanLeft-2.png"));
		allTextures[Settings.LEFT][2] = new Texture(Gdx.files.internal("images/pacman/pacman-3.png"));
		
		
		allTextures[Settings.RIGHT][0] = new Texture(Gdx.files.internal("images/pacman/pacmanRight.png"));
		allTextures[Settings.RIGHT][1] = new Texture(Gdx.files.internal("images/pacman/pacmanRight-2.png"));
		allTextures[Settings.RIGHT][2] = new Texture(Gdx.files.internal("images/pacman/pacman-3.png"));
		
		allTextures[Settings.UP][0] = new Texture(Gdx.files.internal("images/pacman/pacmanUp.png"));
		allTextures[Settings.UP][1] = new Texture(Gdx.files.internal("images/pacman/pacmanUp-2.png"));
		allTextures[Settings.UP][2] = new Texture(Gdx.files.internal("images/pacman/pacman-3.png"));

		allTextures[Settings.DOWN][0] = new Texture(Gdx.files.internal("images/pacman/pacmanDown.png"));
		allTextures[Settings.DOWN][1] = new Texture(Gdx.files.internal("images/pacman/pacmanDown-2.png"));
		allTextures[Settings.DOWN][2] = new Texture(Gdx.files.internal("images/pacman/pacman-3.png"));
		
		this.seuil = seuil / 1000;
	}
	
	public void render(double delta) {
		deltaT += delta;
		if(deltaT > seuil)
			deltaT = 0.0;
	}
	
	int ouvertureBouche() {
		int textureAMettre;
		if(deltaT < seuil*.25)
			textureAMettre = 0;
		else if (deltaT < seuil*.5)
			textureAMettre = 1;
		else if (deltaT < seuil*.75)
			textureAMettre = 0;
		else
			textureAMettre = 2;
		
		return textureAMettre;
	}
	
	@Override
	public Texture getTexture() {
		return allTextures[pacman.getDirection()][ouvertureBouche()];
	}

	@Override
	public void resetDelta() {
		this.deltaT = 0;
	}

}
