package com.mygdx.model.tree.tests.checkPacgumJustInDirection;

import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.model.tree.tests.WorldTesterException;

public class CheckPacgumJustLeft extends WorldTester {

	public CheckPacgumJustLeft() throws WorldTesterException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean evaluateWorld() {
		BlockElement block = world.getPacman().getMazeElementTo(DIRECTION.LEFT);
		
		for(PacGum p : world.getPacGumList()) {
			if(p.position == block.position)
				return true;
		}
		
		return false;		
	}

	@Override
	public String toString() {
		return "CheckPacgumJustLeft";
	}

	@Override
	public WorldTester clone() {
		try {
			return new CheckPacgumJustLeft();
		} catch (WorldTesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
