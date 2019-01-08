package com.mygdx.COR;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.Intersection;

public class IntersectionBuilderCOR extends MazeCOR {

	public IntersectionBuilderCOR(MazeCOR suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected GameElement build1(World world, int elementType, int x, int y) {
		if(elementType == super.IDINTERSECTION)
			return new Intersection(new Vector2(x,y), world);
		return null;
	}
	
}
