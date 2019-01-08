package com.mygdx.model.elements.moving.ghosts;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;

public class Pinky extends Ghost {

	public Pinky(Vector2 position, World world, int direction) {
		super(position, world, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplacer() {
		if(!justRespawned) {
			if(super.isInGhostHouse() && !(this.state == GhostState.DEAD)) {
				
				super.getOutOfHouse();
				
			} else if(this.state == GhostState.ESCAPING || this.state == GhostState.BLINKING) {
				
				super.deplacementAleatoire();
				
			} else if(this.state == GhostState.DEAD) {
				
				super.deplacementSpawn();
				
				if(this.position.epsilonEquals(this.spawn)) {
					this.setStateToAlive();
					this.justRespawned = true;
					this.deltaDeath = 0;
					this.direction = Settings.DOWN;
				}
			} else {
				//super.deplacementAleatoire();
				super.deplacementMinimiseXY();
				if(super.eatPacman()) {
	//				System.out.println("Pinky mange pacman");
				}
			}
		}
	}
	
}
