package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.PacGum;

public class CheckPacgumIsClose extends WorldTester {

	@Override
	public boolean evaluateWorld() {
		for(PacGum p : world.getPacGumList()) {
			double distX = world.getPacman().position.x - p.position.x;
			double distY = world.getPacman().position.y - p.position.y;
			
			if(distX*distX + distY*distY < 64*64) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "CheckPacgumIsClose";
	}

}
