package com.mygdx.model;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.model.elements.GameElement;

public class WorldIterator implements Iterator<GameElement>{

	private World world;
	private Iterator<GameElement> mazeIterator;
	int i;
	int pacgumIndex;
	int ghostIndex;
	
	public WorldIterator(World world) {
		this.world = world;
		this.mazeIterator = this.world.getMaze().iterator();
		this.i = 0; /* 0 = maze, 1 = pacman, 2 = pacgums, 3 = ghosts*/
		pacgumIndex = 0;
		ghostIndex = 0;
	}
	@Override
	public boolean hasNext() {
		return (this.i < 3);
	}

	@Override
	public GameElement next() {
		if(i == 0) {
			if(!this.mazeIterator.hasNext()) {
				i = 1; //On passe aux pacgums
				if(pacgumIndex >= this.world.getPacGumList().size()) {
					i++;
				}
			}
		}
		else if(i == 1) {
			if(pacgumIndex >= this.world.getPacGumList().size()) {
				i++;
			}
		}else if(i == 2){
			if(ghostIndex >= this.world.getGhostsList().size()) {
				i++;
			}
		} else {
			i++;
		}
		

		
		switch(this.i) {
			case 0 : return this.mazeIterator.next();
			case 1 : return this.world.getPacGumList().get(pacgumIndex++);
			case 2 : return this.world.getGhostsList().get(ghostIndex++);
			case 3 : return this.world.getPacman();
			default : throw new IndexOutOfBoundsException(this.getClass() + " : fonction next() -> i est trop grand"); //Clean
		}
	}

	@Override
	public void remove() {
				
	}

}
