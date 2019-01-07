package com.mygdx.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class MovingElement extends GameElement {
	
	protected int direction;
	protected double speed;
	protected Vector2 spawn;
	
	public MovingElement(Vector2 position, World world, int direction) {
		super(position, world);
		this.setDirection(direction);
		this.speed = Settings.normalSpeed;
		this.spawn = new Vector2(position);
	}

	protected abstract void deplacer();
	
	public int getDirection() {
		return this.direction;
	}
	
	public void setDirection(int newDirection) {
		if(newDirection < 4 || newDirection > -1)
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
	protected boolean isOutOfWorld() {
		return (position.x > world.getWidth()-1 ||
				position.y > world.getHeight()-1 ||
				position.x <= -1 ||
				position.y <= -1);			
	}
	
	/**
	 * Remet l'élément dans le monde
	 */
	protected void putBackToWorld() {
		if(position.x > world.getWidth()) //Sortie à droite
			position.x = -1;
		else if (position.y > world.getHeight()) //Sortie en haut
			position.y = -1;
		else if (position.x  < 0)
			position.x = world.getWidth(); //Sortie à gauche
		else if(position.y < 0)
			position.y = world.getHeight(); //Sortie en bas
	}
	
	protected boolean detectCollisionWithBlockInCurrentDirection(GameElement element) {
		return detectCollisionWithBlock(element, this.direction);
	}	
	
	protected boolean detectCollisionWithBlock(GameElement element, int direction) {
		if(BlockElement.class.isAssignableFrom(element.getClass())) {
			Rectangle bodyCopy = new Rectangle(this.body);
			bodyCopy.setPosition(changePositionOfRectInDirection(bodyCopy, direction));
			return bodyCopy.overlaps(element.getBody());
		} else {
			return false;
		}
	}
	
	protected boolean detectSuperpostionWithIntersection(GameElement element) {
		if(element.getClass() == Intersection.class) {	
			Vector2 elementCenter = new Vector2();
			Vector2 movingElementCenter = new Vector2();
			
			element.body.getCenter(elementCenter);
			this.body.getCenter(movingElementCenter);
			
			Rectangle elementRect = new Rectangle(elementCenter.x, elementCenter.y, 1f, 1f);
			Rectangle movingElementRect = new Rectangle(movingElementCenter.x, movingElementCenter.y, 1f, 1f);
			
			return (movingElementRect.x == elementRect.x && movingElementRect.y == elementRect.y);
		}
		
		return false;
		
	}

	private Vector2 changePositionOfRectInDirection(Rectangle rectangle, int direction) {
		Vector2 pos = new Vector2();
		rectangle.getPosition(pos);
		pos = changeVectorWithDirection(pos, direction);
		
		return pos;
	}
	
	private Vector2 changeVectorWithDirection(Vector2 pos, int direction) {
		switch(direction) {
			case Settings.LEFT : pos.x -= speed;break;
			case Settings.RIGHT : pos.x += speed;break;
			case Settings.UP : pos.y += speed;break;
			case Settings.DOWN : pos.y -= speed;break;
		}
		
		return pos;
	}
	
	protected void moveElement() {
		if(this.isOutOfWorld()) {
			this.putBackToWorld();
		}
		this.position = changeVectorWithDirection(this.position, this.direction);
		this.body.setPosition(this.position);
	}
	
	protected boolean canChangeDirection(GameElement element) {
		return this.detectCollisionWithBlockInCurrentDirection(element) || this.detectSuperpostionWithIntersection(element);
	}
	
	public void replace() {
		this.position = new Vector2(spawn);
		this.body.setPosition(new Vector2(position));
	}
}
