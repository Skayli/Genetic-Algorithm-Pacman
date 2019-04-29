package com.mygdx.model.tree.tests.checkWallInDirection;

import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckWallDownToPacman extends WorldTester {

	public CheckWallDownToPacman() throws WorldTesterException {
		super();
	}

	@Override
	public boolean evaluateWorld() {
		return world.getPacman().getMazeElementTo(DIRECTION.DOWN).isSolid();
	}

	@Override
	public String toString() {
		return "CheckWallDownToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckWallDownToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
