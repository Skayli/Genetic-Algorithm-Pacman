package com.mygdx.model.tree.tests.checkGhostInDirection;

import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.elements.moving.ghosts.GhostState;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckGhostsUpToPacman extends WorldTester {

	public CheckGhostsUpToPacman() throws WorldTesterException {
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
		return "CheckGhostUpToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckGhostsUpToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
