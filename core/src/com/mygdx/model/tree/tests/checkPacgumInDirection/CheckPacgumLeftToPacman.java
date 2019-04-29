package com.mygdx.model.tree.tests.checkPacgumInDirection;

import com.mygdx.model.elements.PacGum;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckPacgumLeftToPacman extends WorldTester {

	public CheckPacgumLeftToPacman() throws WorldTesterException {
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
		try {
			return new CheckPacgumLeftToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
