package com.mygdx.model.tree;

import java.util.ArrayList;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.DIRECTION;

public class IfNode extends Node {
	
	public IfNode(Node parent) {
		super(parent, false);
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
