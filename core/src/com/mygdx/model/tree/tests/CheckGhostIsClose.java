package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.moving.ghosts.Ghost;

public class CheckGhostIsClose extends WorldTester {

	@Override
	public boolean evaluateWorld() {
		for(Ghost ghost : world.getGhostsList()) {
			double distX = world.getPacman().position.x - ghost.position.x;
			double distY = world.getPacman().position.y - ghost.position.y;
			
			if(distX*distX + distY*distY < 64*64)
				return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "CheckGhostIsClose";
	}

}
