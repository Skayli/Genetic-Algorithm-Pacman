package com.mygdx.model.tree.tests.checkPacgumInDirection;

import com.mygdx.model.elements.PacGum;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckPacgumDownToPacman extends WorldTester {

	public CheckPacgumDownToPacman() throws WorldTesterException {
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
		try {
			return new CheckPacgumDownToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
