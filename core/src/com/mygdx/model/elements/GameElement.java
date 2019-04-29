package com.mygdx.model.elements;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.Vect2D;

public abstract class GameElement {
	
	public HitBox hitBox;
	public Vect2D position;
	protected World world;
	
	public GameElement pere;
	
	public GameElement(World world, Vect2D position, double hitboxWidth, double hitboxHeight) {
		this.world = world;
		this.position = position;
		this.hitBox = new HitBox(hitboxWidth, hitboxHeight);
	}
	
	public boolean isOverlaping(GameElement element) {
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
	
}
