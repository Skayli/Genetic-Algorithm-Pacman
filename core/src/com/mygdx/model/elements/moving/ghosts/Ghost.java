package com.mygdx.model.elements.moving.ghosts;

import java.util.ArrayList;

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
	protected double normalSpeed = 0.11;
	protected double escapingSpeed = 0.06;
	protected boolean justRespawned;
	protected float deltaDeath;
	
	public Ghost(World world, Vect2D position, Direction direction) {
		super(world, position, direction, 1, 1);
		state = GhostState.ALIVE;
		speed = normalSpeed;
		justRespawned = false;
		deltaDeath = 0;
	}	
	
	public boolean eatPacman() {		
		return this.state == GhostState.ALIVE && this.isOverlaping(world.getPacman());
	}
	
	public void setStateToAlive() {
		state = GhostState.ALIVE;
		speed = normalSpeed;
	}
	
	public void setStateToEscaping() {
		state = GhostState.ESCAPING;
		speed = escapingSpeed;
	}
	
	public void setStateToBlinking() {
		state = GhostState.BLINKING;
	}
	
	public void setStateDead() {
		state = GhostState.DEAD;
		speed = normalSpeed;
	}
	
	public void switchEscapingBlinking() {
		
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
	
	protected void deplacer() {
		
	}
		
	protected void deplacementAleatoire() {
		
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
			
		}
		
		super.move();
		
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

		this.move();
		
	}

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

	private Direction getDirectionFromTo(GameElement element, GameElement cible) {
		System.out.println(this.position+"-"+world.getWidth());
		
		if(cible.position.x == world.getWidth()-1 && element.position.x < cible.position.x) {
			System.out.println("---------------------------------");
			return direction;
		} else if(cible.position.x == 0 && element.position.x > cible.position.x) {
			System.out.println("*********************************");
			return direction;
		}
		
//		else if(cible.position.y >= world.getHeight() && element.position.y < cible.position.y) { //GOING UP
//			System.out.println("uPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
//			return direction;
//		} else if(cible.position.y < 1 && element.position.y > cible.position.y) { //GOING DOWN
//			System.out.println("DOWWWWWWWWWWWWWWWWWWWWWWWWWN");
//			return direction;
//		}
			
		else if(element.position.x > cible.position.x) {
			return Direction.LEFT;
		} else if(element.position.x < cible.position.x) {
			return Direction.RIGHT;
		} else if(element.position.y > cible.position.y) {
			return Direction.DOWN;
		} else {
			return Direction.UP;
		}
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
	
	public void setPositionToSpawn() {
		super.setPositionToSpawn();
		this.direction = Direction.DOWN;
		this.justRespawned = false;
		setStateToAlive();
	}

	public boolean isDead() {
		return this.state == GhostState.DEAD;
	}

	public void deplacementShortestPath(BlockElement target) {
			Direction newDir = shortestPathTo(target);
			if(this.isAligned()) {
				direction = newDir;
				System.out.println(direction);
			} else { //
				if(newDir == direction.opposite()) {
					direction = newDir;
				}
			}
		
		
		super.move();
	}
		
	private Direction shortestPathTo(BlockElement target) {
		for(GameElement block: this.world.getMaze()) {
			block.pere = null;
		}
	
		GameElement caseDepart = world.getMaze().get((int)this.position.y, (int)this.position.x); 
	
		ArrayList<GameElement> listeCasesParcourues = new ArrayList<GameElement>();
		ArrayList<GameElement> listeCasesAVisiter = new ArrayList<GameElement>();
	
		listeCasesAVisiter.add(caseDepart);

		while(!listeCasesAVisiter.isEmpty()) {
			GameElement caseVisitee = listeCasesAVisiter.get(0);
		
			listeCasesAVisiter.remove(caseVisitee);
			listeCasesParcourues.add(caseVisitee);
		
			if(caseVisitee == target) {
				
				while(caseVisitee.pere != null && caseVisitee.pere != caseDepart) {	
					caseVisitee = caseVisitee.pere;
				}
					
				return this.getDirectionFromTo(this, caseVisitee);
				
			} else {
				for(BlockElement element : world.getMaze().getNeighborBlocksOf(caseVisitee)) {
					if( (!element.isSolid() || element.isBarriere()) && !listeCasesParcourues.contains(element) ) {
						element.pere = caseVisitee;
						listeCasesAVisiter.add(element);
					}
					
				}
			}
		}	
	
		return null;
	}
}

