package com.mygdx.COR;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.blocks.Block;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.blocks.Dark;
import com.mygdx.model.elements.moving.Vect2D;

public class VideBuilderCOR extends MazeCOR {

	public VideBuilderCOR(MazeCOR suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected BlockElement build1(World world, int elementType, int x, int y) {
		if(elementType == super.IDVIDE)
			return new Dark(world, new Vect2D(x,y));
		return null;
	}

}
