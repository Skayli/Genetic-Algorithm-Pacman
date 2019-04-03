package com.mygdx.model.tree;

import com.mygdx.model.elements.moving.DIRECTION;

public class TerminalNode extends Node {
	
	public DIRECTION direction;
	
	public TerminalNode(Node parent) {
		super(parent, true);
		
		this.direction = DIRECTION.randomDirection();
	}

	@Override
	public DIRECTION evaluateDirection() {
		return direction;
	}

	@Override
	public String toString() {
		return super.toString() + " TerminalNode | Direction : " + this.direction + "]";
	}

	@Override
	public Node clone(Node parent) {
		TerminalNode clone = new TerminalNode(parent);
		clone.direction = this.direction.copy();
		clone.numero = this.numero;
		clone.depth = this.depth;
		
		return clone;
	}

}
