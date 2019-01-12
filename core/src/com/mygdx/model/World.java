package com.mygdx.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.SuperPacGum;
import com.mygdx.model.elements.blocks.Dark;
import com.mygdx.model.elements.moving.Direction;
import com.mygdx.model.elements.moving.Vect2D;
import com.mygdx.model.elements.moving.ghosts.Blinky;
import com.mygdx.model.elements.moving.ghosts.Clyde;
import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.elements.moving.ghosts.Inky;
import com.mygdx.model.elements.moving.ghosts.Pinky;
import com.mygdx.model.elements.moving.pacman.Pacman;
import com.mygdx.view.TextureFactory;

public class World implements Iterable<GameElement> {

	private Pacman pacman;
	
	private Maze maze;
	
	private ArrayList<PacGum> PG;
	private ArrayList<SuperPacGum>SPG;
	private SuperPacGum SP_BottomLeft; 
	private SuperPacGum SP_TopLeft;
	private SuperPacGum SP_BottomRight;
	private SuperPacGum SP_TopRight;
	
	private ArrayList<Ghost> ghosts;
	private Blinky blinky;
	private Inky inky;
	private Pinky pinky;
	private Clyde clyde;
	
	private boolean hasPacmanEatenPacGumRecently;
	private float deltaSinceSuperPacGumEaten;
	private int nbGhostEatenSinceSuperPacGumEaten;
	
	private int score;
	
	public World() {
		this.maze = new Maze(this);
		this.pacman = new Pacman(this, new Vect2D(14,7), Direction.RIGHT);
		
		/** Fantomes **/
		this.ghosts = new ArrayList<Ghost>();
		this.blinky = new Blinky(this, new Vect2D(12,16), Direction.DOWN);
		this.pinky = new Pinky(this, new Vect2D(13,16), Direction.DOWN);
		this.inky = new Inky(this, new Vect2D(14,16), Direction.DOWN);
		this.clyde = new Clyde(this, new Vect2D(15,16), Direction.DOWN);
		
		ghosts.add(blinky);
		ghosts.add(pinky);
		ghosts.add(inky);
		ghosts.add(clyde);
		
		
		/** Pacgums **/
		PG = new ArrayList<PacGum>();
		SPG = new ArrayList<SuperPacGum>();
		
		this.SP_BottomLeft = new SuperPacGum(this,new Vect2D(1,7));
		this.SP_TopLeft = new SuperPacGum(this,new Vect2D(26,7));
		this.SP_BottomRight = new SuperPacGum(this,new Vect2D(1,27));
		this.SP_TopRight = new SuperPacGum(this,new Vect2D(26,27));
		
		PG.add(SP_BottomLeft);
		PG.add(SP_TopLeft);
		PG.add(SP_BottomRight);
		PG.add(SP_TopRight);
		
		SPG.add(SP_BottomLeft);
		SPG.add(SP_TopLeft);
		SPG.add(SP_BottomRight);
		SPG.add(SP_TopRight);
		

		for(GameElement element : this) {
			if(!overlapsSuperPacGum(element) && element instanceof Dark ) { 
				PG.add(new PacGum(this, element.position, 1, 1));
			}
		}

		
		hasPacmanEatenPacGumRecently = false;
		deltaSinceSuperPacGumEaten = 0;
		nbGhostEatenSinceSuperPacGumEaten = 0;
		
		score = 0;
		
		TextureFactory.setWorld(this);
	}
	
	public void replaceElement() {
		for(Ghost ghost : ghosts) {
			ghost.setPositionToSpawn();
		}
		pacman.setPositionToSpawn();
	}
	
	public Maze getMaze() {
		return this.maze;
	}
	
	public int getHeight() {
		return this.maze.getHeight();
	}
	public int getWidth() {
		return this.maze.getWidth();
	}
	
	public Pacman getPacman() {
		return this.pacman;
	}
	
	public ArrayList<PacGum> getPacGumList() {
		return PG;
	}
	
	public ArrayList<SuperPacGum> getSPGList() {
		return SPG;
	}
	
	public ArrayList<Ghost> getGhostsList() {
		return ghosts;
	}
	
	public Blinky getBlinky() {
		return blinky;
	}
	
	public Pinky getPinky() {
		return pinky;
	}
	
	public Inky getInky() {
		return inky;
	}
	
	public Clyde getClyde() {
		return clyde;
	}
	
	public SuperPacGum getSP_BottomLeft() {
		return SP_BottomLeft;
	}
	
	public SuperPacGum getSP_BottomRight() {
		return SP_BottomRight;
	}
	
	public SuperPacGum getSP_TopLeft() {
		return SP_TopLeft;
	}
	
	public SuperPacGum getSP_TopRight() {
		return SP_TopRight;
	}
	
	public boolean hasPacmanEatenPacGumRecently() {
		return hasPacmanEatenPacGumRecently;
	}
	
	public void setPacmanhasEatenSuperPacGumRecently(boolean eaten) {
		this.hasPacmanEatenPacGumRecently = eaten;
	}
	
	public float getDeltaSinceSuperPacGumEaten() {
		return deltaSinceSuperPacGumEaten;
	}
	
	public void setDeltaSinceSuperPacGumEaten(float delta) {
		deltaSinceSuperPacGumEaten = delta;
	}
	
	@Override
	public Iterator<GameElement> iterator() {
		return new WorldIterator(this);
	}

	public void movePacmanAndGhosts() {
		this.pacman.deplacer();
		
//		this.blinky.deplacer();
//		this.clyde.deplacer();
//		this.inky.deplacer();
		this.pinky.deplacer();
		
	}
	
	private boolean overlapsSuperPacGum(GameElement element) {
//		if( (element.isOverlaping(SP_BottomLeft) ||
//				element.isOverlaping(SP_BottomRight) ||
//				element.isOverlaping(SP_TopLeft) ||
//				element.isOverlaping(SP_TopRight)) ) {
//			return true;
//		} else {
//			return false;
//		}
		
		return false;
	}
	
	public void superPacGumEaten() {
		resetNbGhostEatenSinceSuperPacGumEaten();
		updateScore(Settings.SUPERPACGUMVALUE);
//		for(Ghost ghost : this.ghosts) {
//			if(ghost.getState() != GhostState.DEAD && !(ghost.isInGhostHouse())) {
//				ghost.setStateToEscaping();
//			}
//		}
		setPacmanhasEatenSuperPacGumRecently(true);
		setDeltaSinceSuperPacGumEaten(0);
	}
	
	public int getScore() {
		return score;
	}
	
	public void updateScore(int value) {
		score += value;
	}
	
	public int getNbGhostEatenSinceSuperPacGumEaten() {
		return nbGhostEatenSinceSuperPacGumEaten;
	}
	
	public void incrementNbGhostEatenSinceSuperPacGumEaten() {
		if(nbGhostEatenSinceSuperPacGumEaten < 3) {
			nbGhostEatenSinceSuperPacGumEaten++;
		}
	}
	
	private void resetNbGhostEatenSinceSuperPacGumEaten() {
		nbGhostEatenSinceSuperPacGumEaten = 0;
	}
}
