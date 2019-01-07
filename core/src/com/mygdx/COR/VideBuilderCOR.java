package com.mygdx.COR;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Block;
import com.mygdx.model.Dark;
import com.mygdx.model.GameElement;
import com.mygdx.model.PacGum;
import com.mygdx.model.World;

public class VideBuilderCOR extends MazeCOR {

	public VideBuilderCOR(MazeCOR suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected GameElement build1(World world, int elementType, int x, int y) {
		if(elementType == super.IDVIDE)
			return new Dark(new Vector2(x,y), world);
		return null;
	}

}
