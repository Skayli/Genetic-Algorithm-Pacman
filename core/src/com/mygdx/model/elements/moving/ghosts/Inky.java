package com.mygdx.model.elements.moving.ghosts;

import com.mygdx.model.World;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.elements.moving.Vect2D;

public class Inky extends Ghost {

	public Inky(World world, Vect2D position, DIRECTION direction) {
		super(world, position, direction);
	}
	
	public void deplacer() {
		if(isInGhostHouse() && !(this.state == GhostState.DEAD)) { // Sortir de la maison des fantomes
			getOutOfHouse();				
		} else if(this.state == GhostState.DEAD) { // Fantome mort -> retourner au spawn
			if(this.position.isEquals(this.spawn)) {
				this.setStateToAlive();
				getOutOfHouse();
			} else {
				BlockElement spawnBlock = this.world.getMaze().get((int)this.spawn.y, (int)this.spawn.x);
				deplacementShortestPath(spawnBlock);
			}
					
		} else if(this.canBeEaten()){ // Fantome fuyant
			super.deplacementAleatoire(randomInky);
		} else {
			double i = randomInky.nextDouble();
			if(i < .5) {
				super.deplacementAleatoire(randomInky);
			} else {
				super.deplacementMinXY(randomInky);
			}
			
		}
	}
	
}
