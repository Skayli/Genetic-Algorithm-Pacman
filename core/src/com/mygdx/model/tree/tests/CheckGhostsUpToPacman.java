package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.moving.ghosts.Ghost;

public class CheckGhostsUpToPacman extends WorldTester {

	public CheckGhostsUpToPacman() {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(Ghost ghost : world.getGhostsList()) {
			if(ghost.position.y > world.getPacman().position.y && isPacmanCloseTo(ghost)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckGhostUpToPacman";
	}

	@Override
	public WorldTester clone() {
		return new CheckGhostsUpToPacman();
	}

}
