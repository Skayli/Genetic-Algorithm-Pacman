package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.PacGum;

public class CheckPacgumLeftToPacman extends WorldTester {

	public CheckPacgumLeftToPacman() {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(PacGum pacgum : world.getPacGumList()) {
			if(pacgum.position.x < world.getPacman().position.x && isPacmanCloseTo(pacgum)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckPacgumLeftToPacman";
	}

	@Override
	public WorldTester clone() {
		return new CheckPacgumLeftToPacman();
	}
	
	

}
