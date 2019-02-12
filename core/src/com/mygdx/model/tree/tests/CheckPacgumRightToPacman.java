package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.PacGum;

public class CheckPacgumRightToPacman extends WorldTester {

	public CheckPacgumRightToPacman() {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(PacGum pacgum : world.getPacGumList()) {
			if(pacgum.position.x > world.getPacman().position.x) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckPacgumRightToPacman";
	}

}
