package com.mygdx.model.elements.moving.ghosts;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.moving.Direction;
import com.mygdx.model.elements.moving.Vect2D;

public class Pinky extends Ghost {

	public Pinky(World world, Vect2D position, Direction direction) {
		super(world, position, direction);
	}

	public void deplacer() {
		if(this.state != GhostState.WAITING) {
			if(isInGhostHouse() && !(this.state == GhostState.DEAD)) {
				getOutOfHouse();				
			} else if(this.state == GhostState.DEAD) {
				System.out.println("Position de pinky: " + this.position);
				System.out.println(this.spawn);
				if(this.position.isEquals(this.spawn)) {
					this.setStateToAlive();
					getOutOfHouse();
				} else {
					BlockElement spawnBlock = this.world.getMaze().get((int)this.spawn.y, (int)this.spawn.x);
					deplacementShortestPath(spawnBlock);
				}
						
			} else {
				super.deplacementAleatoire();
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
//				
//			}
//			else if(this.state == GhostState.ESCAPING || this.state == GhostState.BLINKING) {
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
//			} else {
//				//super.deplacementAleatoire();
//				super.deplacementMinimiseXY();
//				if(super.eatPacman()) {
//	//				System.out.println("Pinky mange pacman");
//				}
//			}
//		}
//	}
	
}
