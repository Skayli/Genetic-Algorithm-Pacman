package com.mygdx.model.elements.moving.pacman;

import com.mygdx.model.World;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.elements.moving.MovingElement;
import com.mygdx.model.elements.moving.Vect2D;
import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.tree.Tree;

public class Pacman extends MovingElement {
	
	private Tree brain;
	
	private DIRECTION wantedDirection;
	private boolean isDead;
	
	public int score;
	
	public Pacman(World world) {
		super(world, new Vect2D(14,7), DIRECTION.RIGHT, 1, 1);
		
		this.wantedDirection = direction;
		this.speed = 0.125;
		this.score = 0;
		this.isDead = false;
						
		this.brain = new Tree();		
		
//		brain.generateRandomTree(2);
//		this.brain.saveToFile();

	}

	public boolean isEatingThisGhost(Ghost ghost) {
		if(ghost.canBeEaten()) {
			return this.isOverlaping(ghost);
		}
		
		return false;
	}
		
	
	public void deplacer() {	
		BlockElement target = null;
		
		wantedDirection = brain.getDirection();
		
		
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
		this.direction = DIRECTION.RIGHT;
		this.wantedDirection = direction;
	}
	
	public void setDead(boolean dead) {
		this.isDead = dead;
	}
	
	public boolean isDead() {
		return this.isDead;
	}
	
	public Tree getTree() {
		return brain;
	}
	
}
