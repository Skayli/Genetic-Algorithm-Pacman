package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.moving.ghosts.Ghost;

public class CheckGhostsRightToPacman extends WorldTester {

	public CheckGhostsRightToPacman() {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(Ghost ghost : world.getGhostsList()) {
			if(ghost.position.x > world.getPacman().position.x) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckGhostRightToPacman";
	}

}
