package com.mygdx.view.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.elements.moving.ghosts.Pinky;

public class TexturePinky implements iTexturable {

	private Pinky pinky;
	//private Texture textureLeft, textureLeft2, textureRight, textureRight2, textureUp, textureUp2, textureDown, textureDown2;
	private double seuil; //le seuil est donné en milisecondes
	private double deltaT; //Le delta du render est en secondes !
	private Texture[][][] allTextures; //Etat - Direction - seuil
	
	public TexturePinky(Pinky pinky, double seuil) {
		this.pinky = pinky;
		
		allTextures = new Texture[4][4][2];
		
		allTextures[0][0][0] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyLeft.png"));
		allTextures[0][0][1] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyLeft-2.png"));
		allTextures[1][0][0] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping.png"));
		allTextures[1][0][1] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping-2.png"));
		allTextures[2][0][0] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking.png"));
		allTextures[2][0][1] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking-2.png"));
		allTextures[3][0][0] = new Texture(Gdx.files.internal("images/ghost/ghostDeadLeft.png"));
		allTextures[3][0][1] = new Texture(Gdx.files.internal("images/ghost/ghostDeadLeft.png"));
		
		
		allTextures[0][1][0] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyRight.png"));
		allTextures[0][1][1] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyRight-2.png"));
		allTextures[1][1][0] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping.png"));
		allTextures[1][1][1] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping-2.png"));
		allTextures[2][1][0] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking.png"));
		allTextures[2][1][1] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking-2.png"));
		allTextures[3][1][0] = new Texture(Gdx.files.internal("images/ghost/ghostDeadRight.png"));
		allTextures[3][1][1] = new Texture(Gdx.files.internal("images/ghost/ghostDeadRight.png"));
		
		allTextures[0][2][0] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyUp.png"));
		allTextures[0][2][1] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyUp-2.png"));
		allTextures[1][2][0] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping.png"));
		allTextures[1][2][1] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping-2.png"));
		allTextures[2][2][0] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking.png"));
		allTextures[2][2][1] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking-2.png"));
		allTextures[3][2][0] = new Texture(Gdx.files.internal("images/ghost/ghostDeadUp.png"));
		allTextures[3][2][1] = new Texture(Gdx.files.internal("images/ghost/ghostDeadUp.png"));
		
		allTextures[0][3][0] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyDown.png"));
		allTextures[0][3][1] = new Texture(Gdx.files.internal("images/ghost/pinky/pinkyDown-2.png"));
		allTextures[1][3][0] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping.png"));
		allTextures[1][3][1] = new Texture(Gdx.files.internal("images/ghost/ghostEscaping-2.png"));
		allTextures[2][3][0] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking.png"));
		allTextures[2][3][1] = new Texture(Gdx.files.internal("images/ghost/ghostBlinking-2.png"));
		allTextures[3][3][0] = new Texture(Gdx.files.internal("images/ghost/ghostDeadDown.png"));
		allTextures[3][3][1] = new Texture(Gdx.files.internal("images/ghost/ghostDeadDown.png"));
		
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
		return allTextures[pinky.getStateAsInt()][pinky.getDirectionAsInt()][bougeDrap()];
	}

	@Override
	public void resetDelta() {
		this.deltaT = 0;
	}

}

