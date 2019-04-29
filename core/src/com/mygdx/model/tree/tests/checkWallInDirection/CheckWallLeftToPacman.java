package com.mygdx.model.tree.tests.checkWallInDirection;

import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckWallLeftToPacman extends WorldTester {

	public CheckWallLeftToPacman() throws WorldTesterException {
		super();
	}

	@Override
	public boolean evaluateWorld() {
		return world.getPacman().getMazeElementTo(DIRECTION.LEFT).isSolid();
	}

	@Override
	public String toString() {
		return "CheckWallLeftToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckWallLeftToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
