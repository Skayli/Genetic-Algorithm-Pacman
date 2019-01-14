package com.mygdx.model.binary_tree.node;

import java.util.Random;

import com.mygdx.model.elements.moving.Direction;

public class RandomNode extends AbstractNode {
	
	public RandomNode() {
		this.value = Direction.RIGHT;
	}
	
	public Direction getValue() {
		int n = (int) (Math.random() * Direction.values().length);
		return Direction.values()[n];
	}
	
	
}
