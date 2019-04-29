package com.mygdx.model.elements.blocks;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.moving.Vect2D;

public abstract class BlockElement extends GameElement {

	boolean isSolid;
	boolean isBarriere;
	
	/**
	 * Constructor of blocks 
	 * @param world The world the bloc belongs to
	 * @param position Its position 
	 * @param isSolid Can movingObject pass through ?
	 */
	public BlockElement(World world, Vect2D position, boolean isSolid, boolean isBarriere) {
		super(world, position, 1, 1);
		
		this.isSolid = isSolid;
		this.isBarriere = isBarriere;
	}
	
	public boolean isSolid() {
		return this.isSolid;
	}

	public boolean isBarriere() {
		return this.isBarriere;
	}

}
