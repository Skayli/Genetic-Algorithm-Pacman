package com.mygdx.model.elements.moving.pacman;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.audio.AudioFactory;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.SuperPacGum;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.moving.Direction;
import com.mygdx.model.elements.moving.MovingElement;
import com.mygdx.model.elements.moving.Vect2D;
import com.mygdx.model.elements.moving.ghosts.Ghost;

public class Pacman extends MovingElement {
	
	private Direction wantedDirection;
	private boolean isDead;
	
	public int score;
	
	public Pacman(World world, Vect2D position, Direction direction) {
		super(world, position, direction, 1, 1);
		this.wantedDirection = direction;
		this.speed = 0.15;
		this.score = 0;
		this.isDead = false;
	}

	/**
	 * Utilisée par le controller pour changer la direction de pacman
	 * @param keyCode -> la fleche indiquant la direction
	 */
	public void changeWantedDirection(int keyCode) {
		switch(keyCode) {
			case Keys.LEFT : wantedDirection = Direction.LEFT;break;
			case Keys.RIGHT : wantedDirection = Direction.RIGHT;break;
			case Keys.UP : wantedDirection = Direction.UP;break;
			case Keys.DOWN : wantedDirection = Direction.DOWN;break;	
		}
		
		if(wantedDirection.opposite() == direction) {
			direction = wantedDirection;
		}
	}
		
	public boolean isEatingThisGhost(Ghost ghost) {
		if(ghost.canBeEaten()) {
			return this.isOverlaping(ghost);
		}
		
		return false;
	}
		
	
	public void deplacer() {	
		BlockElement target = null;
		
		if(this.isAligned()) { // Pacman est aligné : il peut tourner si la case est libre.
			
			if(!this.getMazeElementTo(wantedDirection).isSolid()) {
				direction = wantedDirection;
			}
			
			target = this.getMazeElementTo(direction);
						
		} else { // Recherche de la case la plus proche dans la direction actuelle
			target = this.getMazeElementTo(direction);
		}
		
		if(!target.isSolid())
			super.move();
		
	}
	
	public void setPositionToSpawn() {
		super.setPositionToSpawn();
		this.direction = Direction.RIGHT;
		this.wantedDirection = direction;
	}
	
	public void setDead(boolean dead) {
		this.isDead = dead;
	}
	
	public boolean isDead() {
		return this.isDead;
	}
	
}
