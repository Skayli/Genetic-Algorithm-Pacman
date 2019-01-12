package com.mygdx.model.elements.moving.ghosts;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.Barriere;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.blocks.GhostHouse;
import com.mygdx.model.elements.moving.Direction;
import com.mygdx.model.elements.moving.MovingElement;
import com.mygdx.model.elements.moving.Vect2D;

public abstract class Ghost extends MovingElement {
	
	protected GhostState state; // 0 : Normal ~ 1 : Escape ~ 2 : Blinking ~ 3 : Eaten
	protected boolean justRespawned;
	protected float deltaDeath;
	
	public Ghost(World world, Vect2D position, Direction direction) {
		super(world, position, direction, 1, 1);
		state = GhostState.ALIVE;
		speed = 0.11;
		justRespawned = false;
		deltaDeath = 0;
	}	
	
	public boolean eatPacman() {		
		return this.state == GhostState.ALIVE && this.isOverlaping(world.getPacman());
	}
	
	public void setStateToAlive() {
		state = GhostState.ALIVE;
		speed = Settings.normalSpeed;
//		adaptPosition();
	}
	
	public void setStateToEscaping() {
		state = GhostState.ESCAPING;
		speed = Settings.normalSpeed/2;
	}
	
	public void setStateToBlinking() {
		state = GhostState.BLINKING;
		speed = Settings.normalSpeed/2;
	}
	
	public void setStateDead() {
//		state = Settings.DEAD;
		state = GhostState.DEAD;
		speed = Settings.normalSpeed;
//		adaptPosition();
	}
	
	public void switchEscapingBlinking() {
		/*
		if(state == Settings.ESCAPING)
			state = Settings.BLINKING;
		else 
			state = Settings.ESCAPING;
		*/
		
		if(state == GhostState.ESCAPING)
			state = GhostState.BLINKING;
		else
			state = GhostState.ESCAPING;
	}
	
	public int getStateAsInt() {
		switch(state) {
			case ESCAPING: return 1; 
			case BLINKING: return 2; 
			case DEAD: return 3; 
			default: return 0;
		}
	}
	public GhostState getState() {
		return state;
	}
	
	public void setState(GhostState state) {
		this.state = state;
	}
	
	public boolean canBeEaten() {
		return state == GhostState.ESCAPING || state == GhostState.BLINKING;
	}
	
	public void deplacer() {
		if(!justRespawned) {
			if(isInGhostHouse() && !(this.state == GhostState.DEAD)) {
				getOutOfHouse();				
			}
			
		}

	}
		
	protected void deplacementAleatoire() {
		BlockElement target = null;
		
		if(this.isAligned()) {
			
			ArrayList<Direction> possibleDirections = new ArrayList<Direction>();
			
			for(Direction dir : Direction.values()) {
				if(!this.getMazeElementTo(dir).isSolid()) {
					possibleDirections.add(dir);
				}
			}
			
			if(possibleDirections.size() > 2 || this.getMazeElementTo(direction).isSolid()) {
				int randomIndex = (int) (Math.random() * possibleDirections.size());
				this.direction = possibleDirections.get(randomIndex);
			}
			
			target = this.getMazeElementTo(direction);
			
			
		} else {
			
			target = this.getMazeElementTo(direction);
			
		}
		
		super.moveTo(target);
		
		
	}
	
//	protected void deplacementMinimiseXY() {
//		Direction newDirectionX = direction;
//		Direction newDirectionY = direction;
//		float diffX = this.getPosition().x - world.getPacman().getPosition().x;
//		float diffY = this.getPosition().y - world.getPacman().getPosition().y;
//		
//		
//		for(GameElement element : this.world) {
//			if(super.canChangeDirection(element)) {
//				if(diffX > 0) {
//					newDirectionX = Direction.LEFT;
//				} else {
//					newDirectionX = Direction.RIGHT;
//				}
//				
//				if(diffY > 0) {
//						newDirectionY = Direction.DOWN;
//				} else {
//						newDirectionY = Direction.UP;
//				}
//				
//				ArrayList<Direction> deplacementsPossibles = new ArrayList<Direction>();
//				
//				if(diffX != 0 && !detectAnyCollisionInDirection(newDirectionX)) {
//					deplacementsPossibles.add(newDirectionX);
//				}
//				
//				if(diffY != 0 && !this.detectAnyCollisionInDirection(newDirectionY)) {
//					deplacementsPossibles.add(newDirectionY);
//				}
//				
//				switch(deplacementsPossibles.size()) {
//				case 0 : direction = Direction.values()[(int) (Math.random() * 4)];break;
//				case 1 : direction = deplacementsPossibles.get(0);break;
//				case 2 : direction = deplacementsPossibles.get((int) (Math.random() * 2));break;
//				}
//			}
//		}		
//
//		
//		if(!detectAnyCollisionInDirection(direction))
//			this.moveElement();
//	}
//	
//	protected void deplacementPCC() {
//		for(GameElement element : this.world) {
//			if(super.canChangeDirection(element)) {
//				direction = getPCCToPacmanFrom(this);
//			}
//		}
//		
//		super.moveElement();
//	}
//	
//	protected void deplacementSpawn() {
//		for(GameElement element : this.world) {
//			if(canChangeDirectionGhost(element))
//				direction = getPCCToSpawn(this);
//		}
//		
//		super.moveElement();
//	}
//	
//	protected Direction getPCCToSpawn(Ghost ghost) {
//		GameElement spawnElement = world.getMaze().get((int)this.spawn.y, (int)this.spawn.x);
//		return getPCCFromTo(ghost, spawnElement);
//	}
//	
//	//Reajuste la position après un changement de vitesse pour être réaligné
//	private void adaptPosition() {
//		if(this.position.x % speed != 0 || this.position.y % speed != 0) {
//			switch(direction) {
//			case LEFT : this.position.x -= this.speed/2; break;
//			case RIGHT : this.position.x += this.speed/2;break;
//			case UP : this.position.y += this.speed/2;break;
//			case DOWN : this.position.y -= this.speed/2;break;
//			}
//			this.body.setPosition(this.position);
//		}
//	}
//	
	protected void getOutOfHouse() {
		for(GameElement element : this.world) {
			if(this.isOverlaping(element)) {
				if(this.position.x < 13)
					this.direction = Direction.RIGHT;
				else if(this.position.x > 14)
					this.direction = Direction.LEFT;
				else if(this.position.y < 20)
					this.direction = Direction.UP;
			}
		}
		
		BlockElement elt = this.getMazeElementTo(direction);
		
		this.moveTo(elt);
		
	}
//	
	public boolean isInGhostHouse() {
		int maxX = 0, maxY = 0, minX = world.getWidth(), minY = world.getHeight();
		for(GameElement element : this.world) {
			if(element.getClass() == GhostHouse.class || element.getClass() == Barriere.class) {
				maxX = Math.max(maxX, (int)element.position.x);
				maxY = Math.max(maxY, (int)element.position.y);
				minX = Math.min(minX, (int)element.position.x);
				minY = Math.min(minY, (int)element.position.y);
			}
		}
		return ((position.x > minX-1 && position.x < maxX+1) && (position.y > minY-1 & position.y < maxY+1));
	}
//	
//	private Direction getPCCToPacmanFrom(Ghost ghost) {
//		if(!world.getPacman().isOutOfWorld())
//			return getPCCFromTo(ghost, world.getPacman());
//		else
//			return Direction.values()[(int)(Math.random()*4)];
//	}
//	
//	private Direction getPCCFromTo(GameElement depart, GameElement cible) {
//		
//		for(GameElement element: this.world) {
//			element.pere = null;
//		}
//		
//		GameElement caseDepart = world.getMaze().get((int)depart.getPosition().y, (int)depart.getPosition().x);
//		GameElement caseCible = world.getMaze().get((int)cible.getPosition().y, (int) cible.getPosition().x);
//		
//		ArrayList<GameElement> listeCasesParcourues = new ArrayList<GameElement>();
//		ArrayList<GameElement> listeCasesAVisiter = new ArrayList<GameElement>();
//		
//		listeCasesAVisiter.add(caseDepart);
//
//		while(!listeCasesAVisiter.isEmpty()) {
//			GameElement caseVisitee = listeCasesAVisiter.get(0);
//			
//			listeCasesAVisiter.remove(caseVisitee);
//			listeCasesParcourues.add(caseVisitee);
//			
//			if(caseVisitee == caseCible) {
//				while(caseVisitee.pere != null && caseVisitee.pere != caseDepart) {
//					caseVisitee = caseVisitee.pere;
//				}
//				return getDirectionFromTo(caseDepart, caseVisitee);
//			} else {
//				getListeVoisinsVisitablesInto(caseVisitee, listeCasesAVisiter, listeCasesParcourues);
//			}
//		}	
//		
//		return null;
//		
//	}
//	
//	private void getListeVoisinsVisitablesInto(GameElement element, ArrayList<GameElement> listeAjout, ArrayList<GameElement> listeCompairaison) {
//		GameElement blocGauche;
//		GameElement blocDroit;
//		GameElement blocSup;
//		GameElement blocInf;
//		
//		if(element.getPosition().x > 0) {
//			blocGauche = world.getMaze().get((int)element.getPosition().y, (int)(element.getPosition().x-1));
//		} else {
//			blocGauche = world.getMaze().get((int)element.getPosition().y, (int)(world.getWidth()-1));
//		}		
//		if(!BlockElement.class.isAssignableFrom(blocGauche.getClass()) && !listeCompairaison.contains(blocGauche)) {
//			listeAjout.add(blocGauche);
//			blocGauche.pere = element;
//		}
//
//		if(element.getPosition().x < world.getWidth()-1) {
//			blocDroit = world.getMaze().get((int)element.getPosition().y, (int)(element.getPosition().x+1));
//		} else {
//			blocDroit = world.getMaze().get((int)element.getPosition().y, 0);
//		}
//		
//		if(!BlockElement.class.isAssignableFrom(blocDroit.getClass()) && !listeCompairaison.contains(blocDroit)) {
//			listeAjout.add(blocDroit);
//			blocDroit.pere = element;
//		}
//		
//		if(element.getPosition().y > 0) {
//			blocInf = world.getMaze().get((int)(element.getPosition().y-1), (int)element.getPosition().x);
//		} else {
//			blocInf = world.getMaze().get(world.getHeight()-1, (int)element.getPosition().x);
//		}
//		
//		if(!BlockElement.class.isAssignableFrom(blocInf.getClass()) && !listeCompairaison.contains(blocInf)) {
//			listeAjout.add(blocInf);
//			blocInf.pere = element;
//		}
//		
//		if(element.getPosition().y <= world.getWidth()) {
//			blocSup = world.getMaze().get((int)(element.getPosition().y+1), (int)element.getPosition().x);	
//		} else {
//			blocSup = world.getMaze().get(0, (int)element.getPosition().x);
//		}
//		
//		if(!BlockElement.class.isAssignableFrom(blocSup.getClass()) && !listeCompairaison.contains(blocSup)) {
//			listeAjout.add(blocSup);
//			blocSup.pere = element;
//		}
//	}
//	
//	private Direction getDirectionFromTo(GameElement element, GameElement cible) {
//		if(element.getPosition().x > cible.getPosition().x) {
//			return Direction.LEFT;
//		} else if(element.getPosition().x < cible.getPosition().x) {
//			return Direction.RIGHT;
//		} else if(element.getPosition().y > cible.getPosition().y) {
//			return Direction.DOWN;
//		} else {
//			return Direction.UP;
//		}
//	}
//	
//	private boolean canChangeDirectionGhost(GameElement element) {
//		return super.canChangeDirection(element) || this.detectSuperpositionWithGhostIntersection(element);
//	}
//	
//	private boolean detectSuperpositionWithGhostIntersection(GameElement element) {
//		if(element.getClass() == GhostIntersection.class) {	
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
//	}
	
	public boolean justRespawned() {
		return justRespawned;
	}
	
	public void setJustRespawned(boolean yesOrNo) {
		justRespawned = yesOrNo;
	}
	
	public float getDeltaDeath() {
		return deltaDeath;
	}
	
	public void incrementDeltaDeath(float delta) {
		deltaDeath += delta;
	}
	
	public void setPositionToSpawn() {
		super.setPositionToSpawn();
		this.direction = Direction.DOWN;
		this.justRespawned = false;
		setStateToAlive();
	}
}

