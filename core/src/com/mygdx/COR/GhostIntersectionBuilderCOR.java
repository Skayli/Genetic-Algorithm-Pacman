package com.mygdx.COR;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.GhostIntersection;
import com.mygdx.model.elements.moving.Vect2D;

public class GhostIntersectionBuilderCOR extends MazeCOR {

	public GhostIntersectionBuilderCOR(MazeCOR suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected GameElement build1(World world, int elementType, int x, int y) {
		if(elementType == super.IDGHOSTINTERSECTION)
			return new GhostIntersection(world, new Vect2D(x,y));
		
		return null;
	}
	
}
