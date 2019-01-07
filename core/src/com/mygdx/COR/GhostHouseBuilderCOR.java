package com.mygdx.COR;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.GameElement;
import com.mygdx.model.GhostHouse;
import com.mygdx.model.World;

public class GhostHouseBuilderCOR extends MazeCOR {

	public GhostHouseBuilderCOR(MazeCOR suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected GameElement build1(World world, int elementType, int x, int y) {
		if(elementType == super.IDGHOSTHOUSE) {
			return new GhostHouse(new Vector2(x,y), world);
		}
		
		return null;
	}

}
