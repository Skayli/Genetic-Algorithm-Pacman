package com.mygdx.model.tree;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;

public class IfNode extends Node {
	
	WorldTester tester;
	
	public IfNode(Node parent, WorldTester tester) {
		super(parent, false);
		
		this.tester = tester;
	}
	
//	/**
//	 * Add the new child to the list of children
//	 * @param newChild
//	 */
//	public void addChild(Node newChild) {
//		
//		if(newChild != this && !children.contains(newChild)) {
//			if(newChild.parent != null)  {
//				newChild.parent.children.remove(newChild);
//			}
//			
//			newChild.parent = this;
//			children.add(newChild);
//		}
//	}
	
	@Override
	public DIRECTION evaluateDirection(World world) {
		return DIRECTION.DOWN;
	}
		
}
