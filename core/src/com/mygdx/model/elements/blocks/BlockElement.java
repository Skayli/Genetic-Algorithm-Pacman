package com.mygdx.model.elements.blocks;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.moving.Vect2D;

public abstract class BlockElement extends GameElement {
	
	boolean shouldContainsPacgum;
	boolean isSolid;
	
	/**
	 * Constructor of blocks 
	 * @param world The world the bloc belongs to
	 * @param position Its position 
	 * @param isSolid Can movingObject pass through ?
	 * @param shouldContainsPacgum Should it have a pacgum ?
	 */
	public BlockElement(World world, Vect2D position, boolean isSolid, boolean shouldContainsPacgum) {
		super(world, position, 1, 1);
		
		this.isSolid = isSolid;
		this.shouldContainsPacgum = shouldContainsPacgum;
	}
	
	public boolean isSolid() {
		return this.isSolid;
	}
	
	public boolean shouldContainsPacgum() {
		return this.shouldContainsPacgum;
	}

}
