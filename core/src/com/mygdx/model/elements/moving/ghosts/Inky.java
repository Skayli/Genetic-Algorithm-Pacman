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
			super.deplacementAleatoire();
		} else {
			double i = Math.random();
			if(i < .5) {
				super.deplacementAleatoire();
			} else {
				super.deplacementMinXY();
			}
		}
	}

//	@Override
//	public void deplacer() {
//		if(!justRespawned) {
//			if(super.isInGhostHouse() && !(this.state == GhostState.DEAD)) {
//				
//				super.getOutOfHouse();
//				
//			} else if(this.state == GhostState.ESCAPING || this.state == GhostState.BLINKING) {
//				
//				super.deplacementAleatoire();
//				
//			} else if(this.state == GhostState.DEAD) {
//				
//				super.deplacementSpawn();
//				
//				if(this.position.epsilonEquals(this.spawn)) {
//					this.setStateToAlive();
//					this.justRespawned = true;
//					this.deltaDeath = 0;
//					this.direction = Direction.DOWN;
//				}
//			
//			} else {
//				int aleat = (int)(Math.random() * 2);
//				switch(aleat) {
//				case 0 : super.deplacementAleatoire();break;
//				case 1 : super.deplacementMinimiseXY();break;
//				}
//				if(super.eatPacman()) {
//	//				System.out.println("Inky mange pacman");
//				}
//			}
//		}
//	}
	
}
