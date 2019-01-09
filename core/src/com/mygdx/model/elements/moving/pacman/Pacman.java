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
import com.mygdx.model.elements.moving.Direction;
import com.mygdx.model.elements.moving.MovingElement;
import com.mygdx.model.elements.moving.ghosts.Ghost;

public class Pacman extends MovingElement {
	
	private Direction wantedDirection;
	private boolean isDead;
	
	private float deltaDead;
	
	public Pacman(Vector2 position, World world, Direction direction) {
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
			case Keys.LEFT : wantedDirection = Direction.LEFT; if(direction == Direction.RIGHT) direction = Direction.LEFT; break;
			case Keys.RIGHT : wantedDirection = Direction.RIGHT; if(direction == Direction.LEFT) direction = Direction.RIGHT; break;
			case Keys.UP : wantedDirection = Direction.UP; if(direction == Direction.DOWN) direction = Direction.UP; break;
			case Keys.DOWN : wantedDirection = Direction.DOWN; if(direction == Direction.UP) direction = Direction.DOWN; break;	
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
					direction = wantedDirection;
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
			direction = wantedDirection;
		}
	}
	
	public void replace() {
		super.replace();
		this.direction = Direction.RIGHT;
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
