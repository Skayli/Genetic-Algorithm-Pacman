package com.mygdx.model.tree.tests.checkWallInDirection;

import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckWallUpToPacman extends WorldTester {

	public CheckWallUpToPacman() throws WorldTesterException {
		super();
	}

	@Override
	public boolean evaluateWorld() {
		return world.getPacman().getMazeElementTo(DIRECTION.UP).isSolid();
	}

	@Override
	public String toString() {
		return "CheckWallUpToPacman";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckWallUpToPacman();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}