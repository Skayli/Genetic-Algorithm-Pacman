package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.PacGum;

public class CheckPacgumUpToPacman extends WorldTester {

	public CheckPacgumUpToPacman() {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(PacGum pacgum : world.getPacGumList()) {
			if(pacgum.position.y > world.getPacman().position.x) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckPacgumUpToPacman";
	}

}
