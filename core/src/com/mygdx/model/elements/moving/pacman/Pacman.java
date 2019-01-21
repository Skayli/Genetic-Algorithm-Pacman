package com.mygdx.model.elements.moving.pacman;

import com.badlogic.gdx.Input.Keys;
import com.mygdx.model.World;
import com.mygdx.model.binary_tree.Tree;
import com.mygdx.model.binary_tree.node.AbstractNode;
import com.mygdx.model.binary_tree.node.RandomNode;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.moving.Direction;
import com.mygdx.model.elements.moving.MovingElement;
import com.mygdx.model.elements.moving.Vect2D;
import com.mygdx.model.elements.moving.ghosts.Ghost;

public class Pacman extends MovingElement {
	
	private Tree brain;
	
	private Direction wantedDirection;
	private boolean isDead;
	
	public int score;
	
	public Pacman(World world, Vect2D position, Direction direction) {
		super(world, position, direction, 1, 1);
		
		this.wantedDirection = direction;
		this.speed = 0.125;
		this.score = 0;
		this.isDead = false;
		
		AbstractNode r, c1, c2, c3;
		
		r = new RandomNode();
		c1 = new RandomNode();
		c2 = new RandomNode();
		c3 = new RandomNode();
		
		c1.addChild(c2);
		r.addChild(c1);
		c2.addChild(c3);
		
		this.brain = new Tree(r);
		
		this.brain.save("arbre de décision de pacman 1");
		System.out.println(brain.getRoot());
	}

	/**
	 * Utilisée par le controller pour changer la direction de pacman
	 * @param keyCode -> la fleche indiquant la direction
	 */
	public void changeWantedDirection(int keyCode) {
		if(!world.usePacmanTree) {
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
	}
		
	public boolean isEatingThisGhost(Ghost ghost) {
		if(ghost.canBeEaten()) {
			return this.isOverlaping(ghost);
		}
		
		return false;
	}
		
	
	public void deplacer() {	
		BlockElement target = null;
		
		if(world.usePacmanTree) {
			wantedDirection = brain.getDirection();
		}
		
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
