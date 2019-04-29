package com.mygdx.model.elements.blocks;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.moving.Vect2D;

public class Barriere extends BlockElement {

	public Barriere(World world, Vect2D position) {
		super(world, position, true, true);
	} 

}
