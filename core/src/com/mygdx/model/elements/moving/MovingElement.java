package com.mygdx.model.elements.moving;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.BlockElement;

public abstract class MovingElement extends GameElement {
	
	protected Direction direction;
	protected double speed;
	protected Vect2D spawn;
	
	public MovingElement(World world, Vect2D position, Direction direction, double hitboxWidth, double hitboxHeight) {
		super(world, position, hitboxWidth, hitboxHeight);
		this.direction = direction;
		this.spawn = new Vect2D(position);
	}

	protected abstract void deplacer();
	
	public int getDirectionAsInt() {
		
		switch(direction) {
			case RIGHT: return 1;
			case UP: return 2;
			case DOWN: return 3;
			default: return 0;
		}		
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void setDirection(Direction newDirection) {
			this.direction = newDirection;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(double newSpeed) {
		this.speed = newSpeed;
	}
	
	/**
	 * Vérifie si l'élément est en dehors du monde
	 * @return Element hors du monde
	 */
	public boolean isOutOfWorld() {
		return (position.getX() > world.getWidth()-1 ||
				position.getY() > world.getHeight()-1 ||
				position.getX() <= -1 ||
				position.getY() <= -1);			
	}
	
	/**
	 * Remet l'élément dans le monde
	 */
	protected void putBackToWorld() {
		if(position.getX() > world.getWidth()) //Sortie à droite
			position.setX(-1);
		else if (position.getY() > world.getHeight()) //Sortie en haut
			position.setY(-1);
		else if (position.getX()  < 0)
			position.setX(world.getWidth()); //Sortie à gauche
		else if(position.getY() < 0)
			position.setY(world.getHeight()); //Sortie en bas
	}
	
	protected boolean isAligned() {
		return position.x % 1 == 0 && position.y % 1 == 0;
	}
		
	public boolean willOverlap(GameElement element, Direction wanted) {
		if(this == element)
			return false;		
		
		Vect2D nextPosition = new Vect2D(this.position);
		
		switch(wanted) {
			case LEFT : nextPosition.x -= speed;break;
			case RIGHT : nextPosition.x += speed;break;
			case UP : nextPosition.y += speed;break;
			case DOWN : nextPosition.y -= speed;break;
		}
		
		if (
				nextPosition.x >= (element.position.x + element.hitBox.getWidth())
			||	(nextPosition.x + hitBox.getWidth()) <= element.position.x
			||	nextPosition.y >= (element.position.y + element.hitBox.getHeight())
			||	(nextPosition.y + hitBox.getHeight()) <= element.position.y
		) {
			return false;
		} else {
			return true;
		}
					
	}
	
	public boolean hasReachCenter(GameElement element) {	
		if(this == element)
			return false;
		
		if (
				position.x >= (element.position.x + element.hitBox.getWidth()/2)
			||	(position.x + hitBox.getWidth()/2) <= element.position.x
			||	position.y >= (element.position.y + element.hitBox.getHeight()/2)
			||	(position.y + hitBox.getHeight()/2) <= element.position.y
		) {
			return false;
		} else {
			return true;
		}
					
	}
		
	protected void move() {
		BlockElement element = getMazeElementTo(direction);
		
		switch(direction) {
			case LEFT : position.x -= Math.min(this.position.x-element.position.x, this.speed);break;
			case RIGHT : position.x += Math.min(element.position.x-this.position.x , this.speed);break;
			case UP : position.y += Math.min(element.position.y-this.position.y , this.speed);break;
			case DOWN : position.y -= Math.min(this.position.y-element.position.y , this.speed);break;
		}
	}
	
	protected BlockElement getMazeElementTo(Direction direction) {
		if(this.isAligned()) {
			switch(direction) {
				case RIGHT: return world.getMaze().getBlockRight((int)position.x, (int)position.y); 
				case LEFT: return world.getMaze().getBlockLeft((int)position.x, (int)position.y);
				case UP: return world.getMaze().getBlockUp((int)position.x, (int)position.y);
				default: return world.getMaze().getBlockDown((int)position.x, (int)position.y);
			}
		} else {
			return this.getClosestMazeElementTo(direction);
		}
	}
	
	private BlockElement getClosestMazeElementTo(Direction direction) {	
		switch(direction) {
			case RIGHT: return world.getMaze().get((int)position.y, (int)Math.ceil(position.x));
			case LEFT: return world.getMaze().get((int)position.y,(int)Math.floor(position.x));
			case UP: return world.getMaze().get((int)Math.ceil(position.y), (int)position.x);
			default: return world.getMaze().get((int)Math.floor(position.y), (int)position.x); 
		}
	}
	
	public void setPositionToSpawn() {
		this.position.x = spawn.x;
		this.position.y = spawn.y;
	}
}
