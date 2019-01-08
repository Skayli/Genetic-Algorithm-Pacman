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
import com.mygdx.model.elements.blocks.GhostIntersection;
import com.mygdx.model.elements.moving.MovingElement;

public abstract class Ghost extends MovingElement {
	
	private int state; // 0 : Normal ~ 1 : Escape ~ 2 : Blinking ~ 3 : Eaten
	protected boolean justRespawned;
	protected float deltaDeath;
	
	public Ghost(Vector2 position, World world, int direction) {
		super(position, world, direction);
		state = Settings.NORMAL;
		justRespawned = false;
		deltaDeath = 0;
	}	
	
	public boolean eatPacman() {
		Vector2 pacmanCenter = new Vector2();
		Vector2 movingElementCenter = new Vector2();
		
		world.getPacman().body.getCenter(pacmanCenter);
		this.body.getCenter(movingElementCenter);
		
		Rectangle pacmanRect = new Rectangle(pacmanCenter.x, pacmanCenter.y, .5f, .5f);
		Rectangle movingElementRect = new Rectangle(movingElementCenter.x, movingElementCenter.y, .5f, .5f);
		
		return this.state == Settings.NORMAL && pacmanRect.overlaps(movingElementRect);
	}
	
	public void setStateToNormal() {
		state = Settings.NORMAL;
		speed = Settings.normalSpeed;
		adaptPosition();
	}
	
	public void setStateToEscaping() {
		state = Settings.ESCAPING;
		speed = Settings.normalSpeed/2;
	}
	
	public void setStateToBlinking() {
		state = Settings.BLINKING;
		speed = Settings.normalSpeed/2;
	}
	
	public void setStateDead() {
		state = Settings.DEAD;
		speed = Settings.normalSpeed;
		adaptPosition();
	}
	
	public void switchEscapingBlinking() {
		if(state == Settings.ESCAPING)
			state = Settings.BLINKING;
		else 
			state = Settings.ESCAPING;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public boolean canBeEaten() {
		return state == Settings.ESCAPING || state == Settings.BLINKING;
	}
	
	private boolean detectAnyCollisionInDirection(int direction) {
		boolean collision = false;
		for(GameElement element : this.world) {
			if(!collision && super.detectCollisionWithBlock(element, direction)) {
				collision = true;
			}
		}
		
		return collision;
	}
	
	private boolean detectSuperpositionWithGhostHouse(GameElement element) { 
		if(element.getClass() == GhostHouse.class || element.getClass() == Barriere.class) {	
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
	
	protected void deplacementAleatoire() {
		int newDirection = direction;
		for(GameElement element : this.world) {
			if(super.canChangeDirection(element)) {
				newDirection = (int)(Math.random() * 4);
			}
		}
		
		if(!detectAnyCollisionInDirection(newDirection)) {
			direction = newDirection;
			super.moveElement();
		}
	}
	
	protected void deplacementMinimiseXY() {
		int newDirectionX = direction;
		int newDirectionY = direction;
		float diffX = this.getPosition().x - world.getPacman().getPosition().x;
		float diffY = this.getPosition().y - world.getPacman().getPosition().y;
		
		
		for(GameElement element : this.world) {
			if(super.canChangeDirection(element)) {
				if(diffX > 0) {
					newDirectionX = Settings.LEFT;
				} else {
					newDirectionX = Settings.RIGHT;
				}
				
				if(diffY > 0) {
						newDirectionY = Settings.DOWN;
				} else {
						newDirectionY = Settings.UP;
				}
				
				ArrayList<Integer> deplacementsPossibles = new ArrayList<Integer>();
				
				if(diffX != 0 && !detectAnyCollisionInDirection(newDirectionX)) {
					deplacementsPossibles.add(newDirectionX);
				}
				
				if(diffY != 0 && !this.detectAnyCollisionInDirection(newDirectionY)) {
					deplacementsPossibles.add(newDirectionY);
				}
				
				switch(deplacementsPossibles.size()) {
				case 0 : direction = (int) (Math.random() * 4);break;
				case 1 : direction = deplacementsPossibles.get(0);break;
				case 2 : direction = deplacementsPossibles.get((int) (Math.random() * 2));break;
				}
			}
		}		

		
		if(!detectAnyCollisionInDirection(direction))
			this.moveElement();
	}
	
	protected void deplacementPCC() {
		for(GameElement element : this.world) {
			if(super.canChangeDirection(element)) {
				direction = getPCCToPacmanFrom(this);
			}
		}
		
		super.moveElement();
	}
	
	protected void deplacementSpawn() {
		for(GameElement element : this.world) {
			if(canChangeDirectionGhost(element))
				direction = getPCCToSpawn(this);
		}
		
		super.moveElement();
	}
	
	protected int getPCCToSpawn(Ghost ghost) {
		GameElement spawnElement = world.getMaze().get((int)this.spawn.y, (int)this.spawn.x);
		return getPCCFromTo(ghost, spawnElement);
	}
	
	//Reajuste la position après un changement de vitesse pour être réaligné
	private void adaptPosition() {
		if(this.position.x % speed != 0 || this.position.y % speed != 0) {
			switch(this.direction) {
			case Settings.LEFT : this.position.x -= this.speed/2; break;
			case Settings.RIGHT : this.position.x += this.speed/2;break;
			case Settings.UP : this.position.y += this.speed/2;break;
			case Settings.DOWN : this.position.y -= this.speed/2;break;
			}
			this.body.setPosition(this.position);
		}
	}
	
	protected void getOutOfHouse() {
		for(GameElement element : this.world) {
			if(this.detectSuperpositionWithGhostHouse(element)) {
				if(this.position.x < 13)
					this.direction = Settings.RIGHT;
				else if(this.position.x > 14)
					this.direction = Settings.LEFT;
				else if(this.position.y < 20)
					this.direction = Settings.UP;
			}
		}
		moveElement();
	}
	
	public boolean isInGhostHouse() {
		int maxX = 0, maxY = 0, minX = world.getWidth(), minY = world.getHeight();
		for(GameElement element : this.world) {
			if(element.getClass() == GhostHouse.class || element.getClass() == Barriere.class) {
				maxX = Math.max(maxX, (int)element.getPosition().x);
				maxY = Math.max(maxY, (int)element.getPosition().y);
				minX = Math.min(minX, (int)element.getPosition().x);
				minY = Math.min(minY, (int)element.getPosition().y);
			}
		}
		return ((position.x > minX-1 && position.x < maxX+1) && (position.y > minY-1 & position.y < maxY+1));
	}
	
	private int getPCCToPacmanFrom(Ghost ghost) {
		if(!world.getPacman().isOutOfWorld())
			return getPCCFromTo(ghost, world.getPacman());
		else
			return (int)(Math.random()*4);
	}
	
	private int getPCCFromTo(GameElement depart, GameElement cible) {
		
		for(GameElement element: this.world) {
			element.pere = null;
			element.color = Color.WHITE;
		}
		
		GameElement caseDepart = world.getMaze().get((int)depart.getPosition().y, (int)depart.getPosition().x);
		GameElement caseCible = world.getMaze().get((int)cible.getPosition().y, (int) cible.getPosition().x);
		
		ArrayList<GameElement> listeCasesParcourues = new ArrayList<GameElement>();
		ArrayList<GameElement> listeCasesAVisiter = new ArrayList<GameElement>();
		
		listeCasesAVisiter.add(caseDepart);

		while(!listeCasesAVisiter.isEmpty()) {
			GameElement caseVisitee = listeCasesAVisiter.get(0);
			
			if(Settings.DEBUGALGOPCC)
				caseVisitee.color = Color.GOLD;
			
			listeCasesAVisiter.remove(caseVisitee);
			listeCasesParcourues.add(caseVisitee);
			
			if(caseVisitee == caseCible) {
				while(caseVisitee.pere != null && caseVisitee.pere != caseDepart) {
					if(Settings.DEBUGALGOPCC)
						caseVisitee.color = Color.BLUE;
					
					caseVisitee = caseVisitee.pere;
				}
				return getDirectionFromTo(caseDepart, caseVisitee);
			} else {
				getListeVoisinsVisitablesInto(caseVisitee, listeCasesAVisiter, listeCasesParcourues);
			}
		}	
		
		return -1;
		
	}
	
	private void getListeVoisinsVisitablesInto(GameElement element, ArrayList<GameElement> listeAjout, ArrayList<GameElement> listeCompairaison) {
		GameElement blocGauche;
		GameElement blocDroit;
		GameElement blocSup;
		GameElement blocInf;
		
		if(element.getPosition().x > 0) {
			blocGauche = world.getMaze().get((int)element.getPosition().y, (int)(element.getPosition().x-1));
		} else {
			blocGauche = world.getMaze().get((int)element.getPosition().y, (int)(world.getWidth()-1));
		}		
		if(!BlockElement.class.isAssignableFrom(blocGauche.getClass()) && !listeCompairaison.contains(blocGauche)) {
			listeAjout.add(blocGauche);
			blocGauche.pere = element;
		}

		if(element.getPosition().x < world.getWidth()-1) {
			blocDroit = world.getMaze().get((int)element.getPosition().y, (int)(element.getPosition().x+1));
		} else {
			blocDroit = world.getMaze().get((int)element.getPosition().y, 0);
		}
		
		if(!BlockElement.class.isAssignableFrom(blocDroit.getClass()) && !listeCompairaison.contains(blocDroit)) {
			listeAjout.add(blocDroit);
			blocDroit.pere = element;
		}
		
		if(element.getPosition().y > 0) {
			blocInf = world.getMaze().get((int)(element.getPosition().y-1), (int)element.getPosition().x);
		} else {
			blocInf = world.getMaze().get(world.getHeight()-1, (int)element.getPosition().x);
		}
		
		if(!BlockElement.class.isAssignableFrom(blocInf.getClass()) && !listeCompairaison.contains(blocInf)) {
			listeAjout.add(blocInf);
			blocInf.pere = element;
		}
		
		if(element.getPosition().y <= world.getWidth()) {
			blocSup = world.getMaze().get((int)(element.getPosition().y+1), (int)element.getPosition().x);	
		} else {
			blocSup = world.getMaze().get(0, (int)element.getPosition().x);
		}
		
		if(!BlockElement.class.isAssignableFrom(blocSup.getClass()) && !listeCompairaison.contains(blocSup)) {
			listeAjout.add(blocSup);
			blocSup.pere = element;
		}
	}
	
	private int getDirectionFromTo(GameElement element, GameElement cible) {
		if(element.getPosition().x > cible.getPosition().x) {
			return Settings.LEFT;
		} else if(element.getPosition().x < cible.getPosition().x) {
			return Settings.RIGHT;
		} else if(element.getPosition().y > cible.getPosition().y) {
			return Settings.DOWN;
		} else {
			return Settings.UP;
		}
	}
	
	private boolean canChangeDirectionGhost(GameElement element) {
		return super.canChangeDirection(element) || this.detectSuperpositionWithGhostIntersection(element);
	}
	
	private boolean detectSuperpositionWithGhostIntersection(GameElement element) {
		if(element.getClass() == GhostIntersection.class) {	
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
	
	public void replace() {
		super.replace();
		this.direction = Settings.DOWN;
		this.justRespawned = false;
		setStateToNormal();
	}
}

