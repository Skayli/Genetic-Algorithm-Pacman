package com.mygdx.model.tree;

import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;

public class IfNode extends Node {
	
	WorldTester tester;
	
	public IfNode(Node parent, WorldTester tester) {
		super(parent, false);
		
		this.tester = tester;
	}

	
	@Override
	public DIRECTION evaluateDirection() {
		if(tester.evaluateWorld()) {
			return leftChild.evaluateDirection();
		} else {
			return rightChild.evaluateDirection();
		}
	}


	@Override
	public String toString() { 
		return super.toString() + " | Tester : " + tester + "]";
	}
		
}
