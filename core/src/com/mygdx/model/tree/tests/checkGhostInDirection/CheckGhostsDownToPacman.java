package com.mygdx.model.tree.tests.checkGhostInDirection;

import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.elements.moving.ghosts.GhostState;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckGhostsDownToPacman extends WorldTester {

	public CheckGhostsDownToPacman() throws WorldTesterException {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(Ghost ghost : world.getGhostsList()) {
			if(ghost.position.y > world.getPacman().position.y && isPacmanCloseTo(ghost) && ghost.getState() == GhostState.ALIVE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckGhostDownToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckGhostsDownToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
