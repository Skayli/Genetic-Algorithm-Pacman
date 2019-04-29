package com.mygdx.model.tree.tests.checkGhostInDirection;

import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.elements.moving.ghosts.GhostState;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckGhostLeftToPacman extends WorldTester {

	public CheckGhostLeftToPacman() throws WorldTesterException {
		super();
	}
	
	@Override
	public boolean evaluateWorld() {
		for(Ghost ghost : world.getGhostsList()) {
			if(ghost.position.x < world.getPacman().position.x && isPacmanCloseTo(ghost) && ghost.getState() == GhostState.ALIVE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CheckGhostLeftToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckGhostLeftToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
