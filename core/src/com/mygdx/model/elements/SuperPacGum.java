package com.mygdx.model.elements;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.Vect2D;

public class SuperPacGum extends PacGum {
	
	public SuperPacGum(World world, Vect2D position) {
		super(world, position, 1, 1);
		this.isSuper = true;
		this.value = 50;
	}

}
