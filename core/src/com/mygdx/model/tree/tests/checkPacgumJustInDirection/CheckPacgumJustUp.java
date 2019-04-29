package com.mygdx.model.tree.tests.checkPacgumJustInDirection;

import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckPacgumJustUp extends WorldTester {

	public CheckPacgumJustUp() throws WorldTesterException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean evaluateWorld() {
		BlockElement block = world.getPacman().getMazeElementTo(DIRECTION.UP);
		
		for(PacGum p : world.getPacGumList()) {
			if(p.position == block.position)
				return true;
		}
		
		return false;		
	}

	@Override
	public String toString() {
		return "CheckPacgumJustUp";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckPacgumJustUp();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
