package com.mygdx.model.elements.moving.pacman;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.audio.AudioFactory;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.PacGum;
import com.mygdx.model.elements.blocks.SuperPacGum;
import com.mygdx.model.elements.moving.MovingElement;
import com.mygdx.model.elements.moving.ghosts.Ghost;

public class Pacman extends MovingElement {
	
	private int wantedDirection;
	private boolean isDead;
	
	private float deltaDead;
	
	public Pacman(Vector2 position, World world, int direction) {
		super(position, world, direction);
		this.wantedDirection = direction;
		this.isDead = false;
		this.deltaDead = 0;
	}

	/**
	 * Utilisée par le controller pour changer la direction de pacman
	 * @param keyCode -> la fleche indiquant la direction
	 */
	public void changeWantedDirection(int keyCode) {
		switch(keyCode) {
			case Keys.LEFT : wantedDirection = Settings.LEFT; if(direction == Settings.RIGHT) direction = Settings.LEFT; break;
			case Keys.RIGHT : wantedDirection = Settings.RIGHT; if(direction == Settings.LEFT) direction = Settings.RIGHT; break;
			case Keys.UP : wantedDirection = Settings.UP; if(direction == Settings.DOWN) direction = Settings.UP; break;
			case Keys.DOWN : wantedDirection = Settings.DOWN; if(direction == Settings.UP) direction = Settings.DOWN; break;	
		}
	}
	
	private void eatPacGum(GameElement pacgum) {
		this.world.getPacGumList().remove(pacgum);
		
		AudioFactory.getInstance().playMunch();
		
		if(pacgum.getClass() == SuperPacGum.class) {
			this.world.superPacGumEaten();
		} else {
			world.updateScore(Settings.PACGUMVALUE);
		}
			
	}
	
	public boolean eatGhost(Ghost ghost) {
		return this.getBody().overlaps(ghost.getBody()) && ghost.canBeEaten(); 
	}

	@Override
	public void deplacer() {
		boolean collisionDetected = false;
		
		for(GameElement element : this.world) {		
			
			//Collision
			if(!collisionDetected) {
				collisionDetected = super.detectCollisionWithBlockInCurrentDirection(element);
			}
			
			//Intersection
			if(super.detectSuperpostionWithIntersection(element)) {
				boolean canChangeDirection = true;
				for(GameElement block : this.world) {
					if(super.detectCollisionWithBlock(block, this.wantedDirection)) {
						canChangeDirection = false;
					}
				}
				
				if(canChangeDirection) {
					super.setDirection(this.wantedDirection);
				}	
			}
			
			//Pacgum
			if((element.getClass() == PacGum.class || element.getClass() == SuperPacGum.class) && this.getBody().overlaps(element.getBody())){
				eatPacGum(element);
			}
		}
		
		if(!collisionDetected) {
			super.moveElement();
		} else {
			super.setDirection(this.wantedDirection);
		}
	}
	
	public void replace() {
		super.replace();
		this.direction = Settings.RIGHT;
		this.wantedDirection = direction;
	}
	
	public void setDead(boolean dead) {
		this.isDead = dead;
	}
	
	public boolean isDead() {
		return this.isDead;
	}
	
	public float getDeltaDead() {
		return this.deltaDead;
	}
	
	public void incrementDeltaDead(float delta) {
		deltaDead+=delta;
	}
	
	public void resetDealtaDead() {
		this.deltaDead = 0;
	}
}
