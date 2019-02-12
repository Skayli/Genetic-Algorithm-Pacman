package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.moving.ghosts.Ghost;

public class CheckGhostLeftToPacman extends WorldTester {

	public CheckGhostLeftToPacman() {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(Ghost ghost : world.getGhostsList()) {
			if(ghost.position.x < world.getPacman().position.x) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckGhostLeftToPacman";
	}

}
