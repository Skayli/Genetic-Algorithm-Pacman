package com.mygdx.model.elements.blocks;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.World;
import com.mygdx.model.elements.moving.Vect2D;

public class Block extends BlockElement {
	
	public Block(World world, Vect2D position) {
		super(world, position, true, false);
	}
}
