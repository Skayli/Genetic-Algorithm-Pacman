package com.mygdx.model.elements;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.Vect2D;

public class PacGum extends GameElement {
	
	public boolean isSuper;
	public int value;
	
	public PacGum(World world, Vect2D position, double hitboxWidth, double hitboxHeight) {
		super(world, position, hitboxWidth, hitboxHeight);
		this.isSuper = false;
		this.value = 10;
	}
	
}
