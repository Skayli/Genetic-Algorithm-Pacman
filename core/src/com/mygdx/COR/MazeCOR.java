package com.mygdx.COR;

import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.BlockElement;

public abstract class MazeCOR {

	protected final int IDMUR = 0;
	protected final int IDVIDE = 1;
	protected final int IDINTERSECTION = 2;
	protected final int IDBARRIERE = 3;
	protected final int IDGHOSTHOUSE = 4;
	protected final int IDGHOSTINTERSECTION = 5;
	
	private MazeCOR suivant;
	
	public MazeCOR(MazeCOR suivant) {
		this.suivant = suivant;
	}
	
	public BlockElement build(World world, int elementType, int x, int y) {
		BlockElement element;
		
		element = this.build1(world, elementType, x, y);
		
		if(element != null) {
			return element;
		} else {
			if(this.suivant != null) {
				return this.suivant.build(world, elementType, x, y);
			} else {
				return null;
			}
		}
	}
	
	public MazeCOR getCOR() {
		return suivant;
	}
	
	protected abstract BlockElement build1(World world, int elementType, int x, int y);	
}
