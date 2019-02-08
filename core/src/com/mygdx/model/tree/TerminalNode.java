package com.mygdx.model.tree;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.DIRECTION;

public class TerminalNode extends Node {
	
	private DIRECTION direction;
	
	public TerminalNode(Node parent) {
		super(parent, true);
		
		this.direction = DIRECTION.randomDirection();
	}

	@Override
	public DIRECTION evaluateDirection(World world) {
		return direction;
	}

}