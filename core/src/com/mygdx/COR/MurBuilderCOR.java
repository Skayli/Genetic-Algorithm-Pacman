package com.mygdx.COR;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Block;
import com.mygdx.model.GameElement;
import com.mygdx.model.World;

public class MurBuilderCOR extends  MazeCOR {
	
	public MurBuilderCOR(MazeCOR suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected GameElement build1(World world, int elementType, int x, int y) {
		if(elementType == super.IDMUR) {
			return new Block(new Vector2(x,y), world);
		}
		
		return null;
	}

}
