package com.mygdx.COR;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.Block;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.moving.Vect2D;

public class MurBuilderCOR extends  MazeCOR {
	
	public MurBuilderCOR(MazeCOR suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected BlockElement build1(World world, int elementType, int x, int y) {
		if(elementType == super.IDMUR) {
			return new Block(world, new Vect2D(x,y));
		}
		
		return null;
	}

}
