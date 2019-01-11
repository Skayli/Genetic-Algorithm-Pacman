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
		
	
	public void deplacer() {	
		GameElement target = null;
		
		if(position.x % 1 == 0 && position.y % 1 == 0) { // Pacman est aligné : il peut tourner si la case est libre.
			
			if(!BlockElement.class.isAssignableFrom(this.getMazeElementTo(wantedDirection).getClass())) {
				direction = wantedDirection;
			}
			
			target = this.getMazeElementTo(direction);
						
		} else { // Recherche de la case la plus proche dans la direction actuelle
			
			switch(direction) {
				case RIGHT: target = world.getMaze().get((int)position.y, (int)Math.ceil(position.x)); break;
				case LEFT: target = world.getMaze().get((int)position.y,(int)Math.floor(position.x)); break;
				case UP: target = world.getMaze().get((int)Math.ceil(position.y), (int)position.x); break;
				case DOWN: target = world.getMaze().get((int)Math.floor(position.y), (int)position.x); break;
			}
			
		}
		
		if(!BlockElement.class.isAssignableFrom(target.getClass())) {
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
