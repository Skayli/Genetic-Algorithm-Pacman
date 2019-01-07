package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.SuperPacGum;
import com.mygdx.model.PacGum;
import com.mygdx.model.Settings;
import com.mygdx.model.SuperPacGum;

public class TextureSuperPacGum implements iTexturable{
	private SuperPacGum superPacGum;
	//private Texture textureLeft, textureLeft2, textureRight, textureRight2, textureUp, textureUp2, textureDown, textureDown2;
	private double seuil; //le seuil est donné en milisecondes
	private double deltaT; //Le delta du render est en secondes !
	private Texture[] allTextures;
	
	public TextureSuperPacGum(SuperPacGum superPacGum, double seuil) {
		this.superPacGum = superPacGum;
		
		allTextures = new Texture[3];
		
		allTextures[0] = new Texture(Gdx.files.internal("images/superpellet-3.png"));
		allTextures[1] = new Texture(Gdx.files.internal("images/superpellet-2.png"));
		allTextures[2] = new Texture(Gdx.files.internal("images/superpellet.png"));
		
		this.seuil = seuil / 1000;
	}

	public void render(double delta) {
		deltaT += delta;
		if(deltaT > seuil)
			deltaT = 0.0;
	}
	
	int changeSize() {
		int textureAMettre;
		if(deltaT < seuil*.25)
			textureAMettre = 0;
		else if (deltaT < seuil*.5)
			textureAMettre = 1;
		else if(deltaT < seuil*.75)
			textureAMettre = 2;
		else
			textureAMettre = 1;
		
		return textureAMettre;
	}
	
	@Override
	public Texture getTexture() {
		return allTextures[changeSize()];
	}

	@Override
	public void resetDelta() {
		this.deltaT = 0;
	}

}
