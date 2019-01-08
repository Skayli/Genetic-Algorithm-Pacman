package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.Settings;
import com.mygdx.model.elements.moving.ghosts.Pinky;

public class TexturePinky implements iTexturable {

	private Pinky pinky;
	//private Texture textureLeft, textureLeft2, textureRight, textureRight2, textureUp, textureUp2, textureDown, textureDown2;
	private double seuil; //le seuil est donn� en milisecondes
	private double deltaT; //Le delta du render est en secondes !
	private Texture[][][] allTextures; //Etat - Direction - seuil
	
	public TexturePinky(Pinky pinky, double seuil) {
		this.pinky = pinky;
		
		allTextures = new Texture[4][4][2];
		
		allTextures[0][Settings.LEFT][0] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyLeft.png"));
		allTextures[0][Settings.LEFT][1] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyLeft-2.png"));
		allTextures[1][Settings.LEFT][0] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping.png"));
		allTextures[1][Settings.LEFT][1] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping-2.png"));
		allTextures[2][Settings.LEFT][0] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking.png"));
		allTextures[2][Settings.LEFT][1] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking-2.png"));
		allTextures[3][Settings.LEFT][0] = new Texture(Gdx.files.internal("images/ghost/ghostDeadLeft.png"));
		allTextures[3][Settings.LEFT][1] = new Texture(Gdx.files.internal("images/ghost/ghostDeadLeft.png"));
		
		
		allTextures[0][Settings.RIGHT][0] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyRight.png"));
		allTextures[0][Settings.RIGHT][1] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyRight-2.png"));
		allTextures[1][Settings.RIGHT][0] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping.png"));
		allTextures[1][Settings.RIGHT][1] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping-2.png"));
		allTextures[2][Settings.RIGHT][0] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking.png"));
		allTextures[2][Settings.RIGHT][1] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking-2.png"));
		allTextures[3][Settings.RIGHT][0] = new Texture(Gdx.files.internal("images/ghost/ghostDeadRight.png"));
		allTextures[3][Settings.RIGHT][1] = new Texture(Gdx.files.internal("images/ghost/ghostDeadRight.png"));
		
		allTextures[0][Settings.UP][0] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyUp.png"));
		allTextures[0][Settings.UP][1] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyUp-2.png"));
		allTextures[1][Settings.UP][0] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping.png"));
		allTextures[1][Settings.UP][1] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping-2.png"));
		allTextures[2][Settings.UP][0] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking.png"));
		allTextures[2][Settings.UP][1] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking-2.png"));
		allTextures[3][Settings.UP][0] = new Texture(Gdx.files.internal("images/ghost/ghostDeadUp.png"));
		allTextures[3][Settings.UP][1] = new Texture(Gdx.files.internal("images/ghost/ghostDeadUp.png"));
		
		allTextures[0][Settings.DOWN][0] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyDown.png"));
		allTextures[0][Settings.DOWN][1] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyDown-2.png"));
		allTextures[1][Settings.DOWN][0] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping.png"));
		allTextures[1][Settings.DOWN][1] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping-2.png"));
		allTextures[2][Settings.DOWN][0] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking.png"));
		allTextures[2][Settings.DOWN][1] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking-2.png"));
		allTextures[3][Settings.DOWN][0] = new Texture(Gdx.files.internal("images/ghost/ghostDeadDown.png"));
		allTextures[3][Settings.DOWN][1] = new Texture(Gdx.files.internal("images/ghost/ghostDeadDown.png"));
		
		this.seuil = seuil / 1000;
	}
	
	public void render(double delta) {
		deltaT += delta;
		if(deltaT > seuil)
			deltaT = 0.0;
	}
	
	int bougeDrap() {
		int textureAMettre;
		if(deltaT < seuil*.5)
			textureAMettre = 0;
		else 
			textureAMettre = 1;
		
		return textureAMettre;
	}
	
	@Override
	public Texture getTexture() {
		return allTextures[pinky.getStateAsInt()][pinky.getDirection()][bougeDrap()];
	}

	@Override
	public void resetDelta() {
		this.deltaT = 0;
	}

}

