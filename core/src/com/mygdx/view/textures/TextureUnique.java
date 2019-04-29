package com.mygdx.view.textures;

import com.badlogic.gdx.graphics.Texture;

public class TextureUnique implements iTexturable {

	private Texture texture;
	private int deltaT;
	
	public TextureUnique(Texture texture) {
		this.texture = texture;
	}

	@Override
	public Texture getTexture() {
		return texture;
	}
	
	@Override
	public void resetDelta() {
		this.deltaT = 0;
	}
}
