package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.moving.DIRECTION;

public class CheckWallDownToPacman extends WorldTester {

	public CheckWallDownToPacman() {
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

}
