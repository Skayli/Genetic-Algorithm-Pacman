package com.mygdx.model.tree.tests.otherTests;

import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckPacmanIsBlocked extends WorldTester {

	public CheckPacmanIsBlocked() throws WorldTesterException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean evaluateWorld() {
		return world.isPacmanBlocked();
	}

	@Override
	public String toString() {
		return "CheckPacmanIsBlocked";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckPacmanIsBlocked();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
