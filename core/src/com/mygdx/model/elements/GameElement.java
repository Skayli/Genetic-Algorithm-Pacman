package com.mygdx.model.elements;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.Vect2D;

public abstract class GameElement {
	
	public HitBox hitBox;
	public Vect2D position;
	protected World world;
	
	public GameElement pere;
	
	public GameElement(World world, Vect2D position, double hitboxWidth, double hitboxHeight) {
		this.world = world;
		this.position = position;
		this.hitBox = new HitBox(hitboxWidth, hitboxHeight);
	}
	
}
