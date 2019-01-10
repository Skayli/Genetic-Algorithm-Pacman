package com.mygdx.model.elements.moving.pacman;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.audio.AudioFactory;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.blocks.Intersection;
import com.mygdx.model.elements.blocks.PacGum;
import com.mygdx.model.elements.blocks.SuperPacGum;
import com.mygdx.model.elements.moving.Direction;
import com.mygdx.model.elements.moving.MovingElement;
import com.mygdx.model.elements.moving.Vect2D;
import com.mygdx.model.elements.moving.ghosts.Ghost;

public class Pacman extends MovingElement {
	
	private Direction wantedDirection;
	private boolean isDead;
	
	private float deltaDead;
	
	public Pacman(World world, Vect2D position, Direction direction) {
		super(world, position, direction, 1, 1);
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
	
	public void eatPacGum(GameElement pacgum) {
		this.world.getPacGumList().remove(pacgum);
		
		AudioFactory.getInstance().playMunch();
		
		if(pacgum.getClass() == SuperPacGum.class) {
			this.world.superPacGumEaten();
		} else {
			world.updateScore(Settings.PACGUMVALUE);
		}
			
	}
	
	public boolean isEatingThisGhost(Ghost ghost) {
		if(ghost.canBeEaten()) {
			return this.isOverlaping(ghost);
		}
		
		return false;
	}

//	@Override
//	public void deplacer() {
//		boolean collisionDetected = false;
//		
//		for(GameElement element : this.world) {		
//			
//			//Collision
//			if(!collisionDetected) {
//				collisionDetected = super.detectCollisionWithBlockInCurrentDirection(element);
//			}
//			
//			//Intersection
//			if(super.detectSuperpostionWithIntersection(element)) {
//				boolean canChangeDirection = true;
//				for(GameElement block : this.world) {
//					if(super.detectCollisionWithBlock(block, this.wantedDirection)) {
//						canChangeDirection = false;
//					}
//				}
//				
//				if(canChangeDirection) {
//					direction = wantedDirection;
//				}	
//			}
//			
//			//Pacgum
//			if((element.getClass() == PacGum.class || element.getClass() == SuperPacGum.class) && this.getBody().overlaps(element.getBody())){
//				eatPacGum(element);
//			}
//		}
//		
//		if(!collisionDetected) {
//			super.moveElement();
//		} else {
//			direction = wantedDirection;
//		}
//	}
	
//	public void deplacer() {
//		
//		boolean canChange = true;
//		for(GameElement element : this.world) {			
//			
//			//Check collision dans la direction actuelle
//			if(BlockElement.class.isAssignableFrom(element.getClass())) {
//				
//				if(this.willOverlap(element, wantedDirection)) {
//					canChange = false;
//				
//				}
//
//			}
//		}
//		
//		if(canChange) {
//			direction = wantedDirection;
//		}
//		
//		boolean canMove = true;
//	
//		System.out.println("*****************************************");
//		for(GameElement elt : this.world.getMaze()) {
//			System.out.println(elt.getClass().getSimpleName());
//		}
//		
//		for(GameElement element : this.world) {			
//			
//			
//
//			//Check pacgum mangée
//			if((element.getClass() == PacGum.class || element.getClass() == SuperPacGum.class) && world.getPacman().hasReachCenter(element)){
//				world.getPacman().eatPacGum(element);
//			}
//			
//			//Check collision dans la direction actuelle
//			if(BlockElement.class.isAssignableFrom(element.getClass())) {
//				
//				if(this.willOverlap(element, direction)) {
//					canMove = false;
//				
//					switch(direction) {
//						case RIGHT: this.position.x = element.position.x - this.hitBox.getWidth(); break;
//						case LEFT: this.position.x = element.position.x + element.hitBox.getWidth(); break;
//						case UP: this.position.y = element.position.y - this.hitBox.getHeight(); break;
//						case DOWN : this.position.y = element.position.y + element.hitBox.getHeight();
//					}
//				}
//
//			}
//		}
//		
//		
//		if(canMove) {
//			super.moveElement();
//		}
//		
//
//	}
		
	
	public void deplacer() {
		System.out.println(position + " | " + direction + " | " + wantedDirection);
		
		GameElement target = null;
		
		switch(direction) {
			case RIGHT: target = world.getMaze().getBlockRight((int)position.x, (int)position.y); break;
			case LEFT: target = world.getMaze().getBlockLeft((int)position.x, (int)position.y); break;
			case UP: target = world.getMaze().getBlockUp((int)position.x, (int)position.y); break;
			case DOWN: target = world.getMaze().getBlockDown((int)position.x, (int)position.y); break;
		}
		
		System.out.println(world.getMaze().getBlockLeft((int)position.x, (int)position.y).position);
		if(BlockElement.class.isAssignableFrom(target.getClass())) {

		} else {
			super.moveTo(target);
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
