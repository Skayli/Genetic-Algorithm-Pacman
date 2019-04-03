package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.moving.DIRECTION;

public class CheckWallLeftToPacman extends WorldTester {

	public CheckWallLeftToPacman() {
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
		return new CheckWallLeftToPacman();
	}

}
