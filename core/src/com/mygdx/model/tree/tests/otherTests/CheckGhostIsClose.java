package com.mygdx.model.tree.tests.otherTests;

import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckGhostIsClose extends WorldTester {

	public CheckGhostIsClose() throws WorldTesterException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean evaluateWorld() {
		for(Ghost g : world.getGhostsList()) {
			if(isPacmanCloseTo(g))
				return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "CheckGhostIsClose";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckGhostIsClose();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
