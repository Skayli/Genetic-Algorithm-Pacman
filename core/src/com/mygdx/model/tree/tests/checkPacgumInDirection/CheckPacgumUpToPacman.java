package com.mygdx.model.tree.tests.checkPacgumInDirection;

import com.mygdx.model.elements.PacGum;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckPacgumUpToPacman extends WorldTester {

	public CheckPacgumUpToPacman() throws WorldTesterException {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(PacGum pacgum : world.getPacGumList()) {
			if(pacgum.position.y > world.getPacman().position.x  && isPacmanCloseTo(pacgum)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckPacgumUpToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckPacgumUpToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
