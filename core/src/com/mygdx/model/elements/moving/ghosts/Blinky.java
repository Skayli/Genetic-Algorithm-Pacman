package com.mygdx.model.elements.moving.ghosts;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.elements.moving.Direction;
import com.mygdx.model.elements.moving.Vect2D;

public class Blinky extends Ghost{
	
	public Blinky(World world, Vect2D position, Direction direction) {
		super(world, position, direction);
	}
	
	public void deplacer() {
		super.deplacer();
	}

//	@Override
//	public void deplacer() {
//		if(!justRespawned) {
//			if(super.isInGhostHouse() && !(this.getState() == GhostState.DEAD)) {
//				
//				super.getOutOfHouse();
//				
//			} else if(this.state == GhostState.ESCAPING || this.getState() == GhostState.BLINKING) {
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
//				super.deplacementPCC();
//				if(super.eatPacman()) {
//					//System.out.println("blinky mange pacman");
//				}
//				
//			}
//		}
//	}
}
