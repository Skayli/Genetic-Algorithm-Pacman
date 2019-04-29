package com.mygdx.model.tree.tests.checkWallInDirection;

import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckWallRightToPacman extends WorldTester {

	public CheckWallRightToPacman() throws WorldTesterException {
		super();
	}

	@Override
	public boolean evaluateWorld() {
		return world.getPacman().getMazeElementTo(DIRECTION.RIGHT).isSolid();
	}

	@Override
	public String toString() {
		return "CheckWallRightToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckWallRightToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
