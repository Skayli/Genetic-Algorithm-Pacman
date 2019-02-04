package com.mygdx.model.elements.moving.ghosts;

import java.util.ArrayList;

import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.Barriere;
import com.mygdx.model.elements.blocks.BlockElement;
import com.mygdx.model.elements.blocks.GhostHouse;
import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.elements.moving.MovingElement;
import com.mygdx.model.elements.moving.Vect2D;

public abstract class Ghost extends MovingElement {
	
	protected GhostState state; // 0 : Normal ~ 1 : Escape ~ 2 : Blinking ~ 3 : Eaten
	protected double normalSpeed = 0.1;
	protected double escapingSpeed = 0.05;
	protected boolean justRespawned;
	protected float deltaDeath;
	
	public Ghost(World world, Vect2D position, DIRECTION direction) {
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
	
	protected void getOutOfHouse() {
		for(GameElement element : this.world) {
			if(this.isOverlaping(element)) {
				if(this.position.x < 13)
					this.direction = DIRECTION.RIGHT;
				else if(this.position.x > 14)
					this.direction = DIRECTION.LEFT;
				else if(this.position.y < 20)
					this.direction = DIRECTION.UP;
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

		
	protected void deplacementAleatoire() {
		if(this.isAligned()) {
	
			ArrayList<DIRECTION> possibleDirections = new ArrayList<DIRECTION>();
			
			for(DIRECTION dir : DIRECTION.values()) {
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
	
	public void deplacementMinXY() {
		
		boolean isInIntersection = true;
		//On définit ce qu'est une intersection
		if(this.isAligned()) {
			ArrayList<BlockElement> n = this.world.getMaze().getNeighborBlocksOf(this);
			
			ArrayList<BlockElement> voisinsDispo = new ArrayList<BlockElement>();
			for(BlockElement elt : n) {
				if(!elt.isSolid()) {
					voisinsDispo.add(elt);
				}
			}
			
			if(voisinsDispo.size() < 3) {
				
				for(BlockElement e : voisinsDispo) {
					if(e == this.getMazeElementTo(direction)) {
						isInIntersection = false;
					}
				}
				
			} 
		}
		
		if(this.isAligned()  && isInIntersection) {
									
			double diffX = this.position.x - world.getPacman().position.x;
			double diffY = this.position.y - world.getPacman().position.y;
			
			ArrayList<DIRECTION> possibleDirection = new ArrayList<DIRECTION>();
		
			//Direction possible a gauche ou a droite
			if(diffX > 0) {
				if(!this.getMazeElementTo(DIRECTION.LEFT).isSolid()) {
					possibleDirection.add(DIRECTION.LEFT);
				}
				
			} else if(diffX < 0) {
				if(!this.getMazeElementTo(DIRECTION.RIGHT).isSolid()) {
					possibleDirection.add(DIRECTION.RIGHT);
				}
				
			}
			
			//Direction possible en haut ou en bas
			if(diffY > 0) {
				if(!this.getMazeElementTo(DIRECTION.DOWN).isSolid()) {
					possibleDirection.add(DIRECTION.DOWN);
				}
				
			} else if(diffY < 0) {
				if(!this.getMazeElementTo(DIRECTION.UP).isSolid() ) {					
					possibleDirection.add(DIRECTION.UP);
				}
				
			}
			
			if(possibleDirection.size() > 0) {
				int randomIndex = (int) (Math.random() * possibleDirection.size());
				direction = possibleDirection.get(randomIndex);
				super.move();
			} else {
				this.deplacementAleatoire();
			}
		} else {
			super.move();
		}
		
	}
	
	public void deplacementShortestPath(BlockElement target) {
		DIRECTION newDir = shortestPathTo(target);
		if(this.isAligned()) {
			direction = newDir;
		} else {
			if(newDir == direction.opposite()) {
				direction = newDir;
			}
		}
	
		super.move();
	}
	
	private DIRECTION getDirectionFromTo(GameElement element, GameElement cible) {
		
		if(cible.position.x == world.getWidth()-1 && element.position.x < cible.position.x) {
			return direction;
		} else if(cible.position.x == 0 && element.position.x > cible.position.x) {
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
			return DIRECTION.LEFT;
		} else if(element.position.x < cible.position.x) {
			return DIRECTION.RIGHT;
		} else if(element.position.y > cible.position.y) {
			return DIRECTION.DOWN;
		} else {
			return DIRECTION.UP;
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
		this.direction = DIRECTION.DOWN;
		this.justRespawned = false;
		setStateToAlive();
	}

	public boolean isDead() {
		return this.state == GhostState.DEAD;
	}
		
	private DIRECTION shortestPathTo(BlockElement target) {
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

