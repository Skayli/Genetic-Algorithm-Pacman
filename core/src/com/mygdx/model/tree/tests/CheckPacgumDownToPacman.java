package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.PacGum;

public class CheckPacgumDownToPacman extends WorldTester {

	public CheckPacgumDownToPacman() {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(PacGum pacgum : world.getPacGumList()) {
			if(pacgum.position.y < world.getPacman().position.y && isPacmanCloseTo(pacgum)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckPacgumDownToPacman";
	}

	@Override
	public WorldTester clone() {
		return new CheckPacgumDownToPacman();
	}

}
