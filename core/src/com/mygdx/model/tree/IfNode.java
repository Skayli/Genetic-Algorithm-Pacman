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
	
	public void setLeftChild(Node leftChild) {
		leftChild.parent = this;
		leftChild.depth = this.depth + 1;
		this.leftChild = leftChild;
	}
	
	public void setRightChild(Node rightChild) {
		rightChild.parent = this;
		rightChild.depth = this.depth + 1;
		this.rightChild = rightChild;
	}


	@Override
	public String toString() { 
		return super.toString()+ " IfNode | Tester : " + tester + "]";
	}


	@Override
	public Node clone(Node parent) {
		WorldTester cloneTester = (WorldTester) tester.clone();
		IfNode clone = new IfNode(parent, cloneTester);
		clone.setLeftChild(this.leftChild.clone(clone));
		clone.setRightChild(this.rightChild.clone(clone));
		clone.numero = this.numero;
		clone.depth = this.depth;
		
		return clone;
	}
		
}
