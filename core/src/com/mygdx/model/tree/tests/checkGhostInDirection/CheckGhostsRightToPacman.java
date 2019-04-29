package com.mygdx.model.tree.tests.checkGhostInDirection;

import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.elements.moving.ghosts.GhostState;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckGhostsRightToPacman extends WorldTester {

	public CheckGhostsRightToPacman() throws WorldTesterException {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(Ghost ghost : world.getGhostsList()) {
			if(ghost.position.x > world.getPacman().position.x && isPacmanCloseTo(ghost) && ghost.getState() == GhostState.ALIVE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckGhostRightToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckGhostsRightToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
