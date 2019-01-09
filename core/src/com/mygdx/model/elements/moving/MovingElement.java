package com.mygdx.model.elements.moving;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.blocks.Intersection;

public abstract class MovingElement extends GameElement {
	
	protected Direction direction;
	protected double speed;
	protected Vector2 spawn;
	
	public MovingElement(Vector2 position, World world, Direction direction) {
		super(position, world);
		this.direction = direction;
		this.speed = Settings.normalSpeed;
		this.spawn = new Vector2(position);
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
	
	protected boolean detectCollisionWithBlock(GameElement element, Direction direction) {
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

	private Vector2 changePositionOfRectInDirection(Rectangle rectangle, Direction direction) {
		Vector2 pos = new Vector2();
		rectangle.getPosition(pos);
		pos = changeVectorWithDirection(pos, direction);
		
		return pos;
	}
	
	private Vector2 changeVectorWithDirection(Vector2 pos, Direction direction) {
		switch(direction) {
			case LEFT : pos.x -= speed;break;
			case RIGHT : pos.x += speed;break;
			case UP : pos.y += speed;break;
			case DOWN : pos.y -= speed;break;
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
