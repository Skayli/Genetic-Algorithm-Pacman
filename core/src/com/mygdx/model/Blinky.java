package com.mygdx.model;

import com.badlogic.gdx.math.Vector2;

public class Blinky extends Ghost{
	
	public Blinky(Vector2 position, World world, int direction) {
		super(position, world, direction);
	}

	@Override
	protected void deplacer() {
		if(!justRespawned) {
			if(super.isInGhostHouse() && !(this.getState() == Settings.DEAD)) {
				
				super.getOutOfHouse();
				
			} else if(this.getState() == Settings.ESCAPING || this.getState() == Settings.BLINKING) {
				
				super.deplacementAleatoire();
				
			} else if(this.getState() == Settings.DEAD) {
				
				super.deplacementSpawn();
				
				if(this.position.epsilonEquals(this.spawn)) {
					this.setStateToNormal();
					this.justRespawned = true;
					this.deltaDeath = 0;
					this.direction = Settings.DOWN;
				}
				
			} else {
				super.deplacementPCC();
				if(super.eatPacman()) {
					//System.out.println("blinky mange pacman");
				}
				
			}
		}
	}

	public int getState() {
		return super.getState();
	}
}
