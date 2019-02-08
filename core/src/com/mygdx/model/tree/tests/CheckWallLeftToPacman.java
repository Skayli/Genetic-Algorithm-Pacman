package com.mygdx.model.tree.tests;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.DIRECTION;

public class CheckWallLeftToPacman extends WorldTester {

	public CheckWallLeftToPacman(World world) {
		super(world);
	}

	@Override
	public boolean evaluateWorld() {
		return world.getPacman().getMazeElementTo(DIRECTION.LEFT).isSolid();
	}

}