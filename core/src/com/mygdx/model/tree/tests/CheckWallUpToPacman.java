package com.mygdx.model.tree.tests;

import com.mygdx.model.elements.moving.DIRECTION;

public class CheckWallUpToPacman extends WorldTester {

	public CheckWallUpToPacman() {
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
		return new CheckWallUpToPacman();
	}

}