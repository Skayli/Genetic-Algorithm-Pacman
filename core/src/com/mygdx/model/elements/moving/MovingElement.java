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
	protected Vect2D spawn;
	
	public MovingElement(World world, Vect2D position, Direction direction, double hitboxWidth, double hitboxHeight) {
		super(world, position, hitboxWidth, hitboxHeight);
		this.direction = direction;
		this.speed = Settings.normalSpeed;
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
	 * V�rifie si l'�l�ment est en dehors du monde
	 * @return Element hors du monde
	 */
	public boolean isOutOfWorld() {
		return (position.getX() > world.getWidth()-1 ||
				position.getY() > world.getHeight()-1 ||
				position.getX() <= -1 ||
				position.getY() <= -1);			
	}
	
	/**
	 * Remet l'�l�ment dans le monde
	 */
	protected void putBackToWorld() {
		if(position.getX() > world.getWidth()) //Sortie � droite
			position.setX(-1);
		else if (position.getY() > world.getHeight()) //Sortie en haut
			position.setY(-1);
		else if (position.getX()  < 0)
			position.setX(world.getWidth()); //Sortie � gauche
		else if(position.getY() < 0)
			position.setY(world.getHeight()); //Sortie en bas
	}
	
//	protected boolean detectCollisionWithBlockInCurrentDirection(GameElement element) {
//		return detectCollisionWithBlock(element, this.direction);
//	}	
//	
//	protected boolean detectCollisionWithBlock(GameElement element, Direction direction) {
//		if(BlockElement.class.isAssignableFrom(element.getClass())) {
//			Rectangle bodyCopy = new Rectangle(this.body);
//			bodyCopy.setPosition(changePositionOfRectInDirection(bodyCopy, direction));
//			return bodyCopy.overlaps(element.getBody());
//		} else {
//			return false;
//		}
//	}
//	
//	protected boolean detectSuperpostionWithIntersection(GameElement element) {
//		if(element.getClass() == Intersection.class) {	
//			Vector2 elementCenter = new Vector2();
//			Vector2 movingElementCenter = new Vector2();
//			
//			element.body.getCenter(elementCenter);
//			this.body.getCenter(movingElementCenter);
//			
//			Rectangle elementRect = new Rectangle(elementCenter.x, elementCenter.y, 1f, 1f);
//			Rectangle movingElementRect = new Rectangle(movingElementCenter.x, movingElementCenter.y, 1f, 1f);
//			
//			return (movingElementRect.x == elementRect.x && movingElementRect.y == elementRect.y);
//		}
//		
//		return false;
//		
//	}
//
//	private Vector2 changePositionOfRectInDirection(Rectangle rectangle, Direction direction) {
//		Vector2 pos = new Vector2();
//		rectangle.getPosition(pos);
//		pos = moveElement(pos, direction);
//		
//		return pos;
//	}
	
//	protected boolean canChangeDirection(GameElement element) {
//	return this.detectCollisionWithBlockInCurrentDirection(element) || this.detectSuperpostionWithIntersection(element);
//}
	
	protected void moveElement() {
		if(this.isOutOfWorld()) {
			this.putBackToWorld();
		}
		
		switch(direction) {
			case LEFT : position.x -= speed;break;
			case RIGHT : position.x += speed;break;
			case UP : position.y += speed;break;
			case DOWN : position.y -= speed;break;
		}
		
	}
	
	public void replace() {
		this.position.x = spawn.x;
		this.position.y = spawn.y;
	}
}
