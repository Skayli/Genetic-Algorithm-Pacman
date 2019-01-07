package com.mygdx.model;

import com.badlogic.gdx.math.Vector2;

public class Inky extends Ghost {

	public Inky(Vector2 position, World world, int direction) {
		super(position, world, direction);
		// TODO Auto-generated constructor stub
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
				int aleat = (int)(Math.random() * 2);
				switch(aleat) {
				case 0 : super.deplacementAleatoire();break;
				case 1 : super.deplacementMinimiseXY();break;
				}
				if(super.eatPacman()) {
	//				System.out.println("Inky mange pacman");
				}
			}
		}
	}

	public int getState() {
		return super.getState();
	}
}
