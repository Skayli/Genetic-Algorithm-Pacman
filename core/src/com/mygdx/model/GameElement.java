package com.mygdx.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameElement {
	protected Vector2 position;
	protected World world;
	protected Rectangle body;
	
	protected GameElement pere;
	
	public Color color;
	
	public GameElement(Vector2 position, World world) {
		this.world = world;
		this.position = position;
		this.body = new Rectangle(position.x, position.y, this.getWidth(), this.getHeight());
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public int getWidth() {
		return 1;
	}
	
	public int getHeight() {
		return 1;
	}
	
	public Rectangle getBody() {
		return body;
	}
}
