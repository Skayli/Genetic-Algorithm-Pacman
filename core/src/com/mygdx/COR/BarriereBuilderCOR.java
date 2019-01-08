package com.mygdx.COR;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.Barriere;
import com.mygdx.model.elements.blocks.Block;
import com.mygdx.model.elements.blocks.Dark;
import com.mygdx.model.elements.blocks.GhostHouse;

public class BarriereBuilderCOR extends MazeCOR {

	public BarriereBuilderCOR(MazeCOR suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected GameElement build1(World world, int elementType, int x, int y) {
		if(elementType == super.IDBARRIERE)
			return new Barriere(new Vector2(x,y), world);
		return null;
	}

}
