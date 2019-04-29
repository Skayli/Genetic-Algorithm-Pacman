package com.mygdx.model.tree.tests.checkPacgumInDirection;

import com.mygdx.model.elements.PacGum;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckPacgumRightToPacman extends WorldTester {

	public CheckPacgumRightToPacman() throws WorldTesterException {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(PacGum pacgum : world.getPacGumList()) {
			if(pacgum.position.x > world.getPacman().position.x  && isPacmanCloseTo(pacgum)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckPacgumRightToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckPacgumRightToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
