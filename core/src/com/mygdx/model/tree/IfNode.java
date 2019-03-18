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
	
	public void setLeftChild(Node n) {
		n.parent = this;
		this.leftChild = n;
	}
	
	public void setRightChild(Node n) {
		n.parent = this;
		this.rightChild = n;
	}


	@Override
	public String toString() { 
		return super.toString()+ " IfNode | Tester : " + tester + "]";
	}


	@Override
	public Node clone(Node parent) {
		IfNode clone = new IfNode(parent, tester);
		clone.leftChild = this.leftChild.clone(clone);
		clone.rightChild = this.rightChild.clone(clone);
		
		return clone;
	}
		
}
