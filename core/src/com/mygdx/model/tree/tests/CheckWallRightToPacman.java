package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.moving.DIRECTION;

public class CheckWallRightToPacman extends WorldTester {

	public CheckWallRightToPacman() {
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
		return new CheckWallRightToPacman();
	}

}
