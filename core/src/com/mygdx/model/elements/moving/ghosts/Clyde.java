package com.mygdx.model.elements.moving.ghosts;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.elements.moving.Direction;

public class Clyde extends Ghost {

	public Clyde(Vector2 position, World world, Direction direction) {
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
					this.direction = Direction.DOWN;
				}
			} else {
				super.deplacementAleatoire();
				if(super.eatPacman()) {
	//				System.out.println("Clyde mange pacman");
				}
			}
		}
	}
	

}
