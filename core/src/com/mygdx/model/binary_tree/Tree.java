package com.mygdx.model.binary_tree;

import com.mygdx.model.binary_tree.node.AbstractNode;
import com.mygdx.model.elements.moving.Direction;

public class Tree {

	public AbstractNode root;
	
	public Tree() {
		this.root = null;
	}

	public Direction getDirection() {
		return root.getValue();
	}
	
	

}
