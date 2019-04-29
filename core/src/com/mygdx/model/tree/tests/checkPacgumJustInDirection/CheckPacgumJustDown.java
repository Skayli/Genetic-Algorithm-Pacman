package com.mygdx.model.tree.tests.checkPacgumJustInDirection;

import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckPacgumJustDown extends WorldTester {

	public CheckPacgumJustDown() throws WorldTesterException {
		super();
	}

	@Override
	public boolean evaluateWorld() {
		BlockElement block = world.getPacman().getMazeElementTo(DIRECTION.DOWN);
		
		for(PacGum p : world.getPacGumList()) {
			if(p.position == block.position)
				return true;
		}
		
		return false;		
	}

	@Override
	public String toString() {
		return "CheckPacgumJustDown";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckPacgumJustDown();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
