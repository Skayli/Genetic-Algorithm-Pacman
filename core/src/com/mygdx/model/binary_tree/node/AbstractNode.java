package com.mygdx.model.binary_tree.node;

import com.mygdx.model.elements.moving.Direction;

public abstract class AbstractNode {
	
	public Direction value;
	
	public AbstractNode left;
	public AbstractNode right;
	
	public AbstractNode() {
		this.left = null;
		this.right = null;
	}
	
	public abstract Direction getValue();
}
