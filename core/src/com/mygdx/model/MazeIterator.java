package com.mygdx.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.BlockElement;

public class MazeIterator implements Iterator<GameElement> {

	private Maze maze;
	int i, j;
	
	public MazeIterator(Maze maze) {
		this.maze = maze;
		i = j = 0;
	}

	@Override
	public boolean hasNext() {
		return ( (i < this.maze.getHeight()) && (j < this.maze.getWidth()) );
	}

	@Override
	public BlockElement next() {
		if(!this.hasNext()) throw new NoSuchElementException("No more game elements");
		BlockElement gameElement;
		do {
			gameElement = (BlockElement) this.maze.get(i, j);
			j = (j + 1) % this.maze.getWidth();
			if(j == 0)
				i++;
		} while(gameElement == null && this.hasNext());
		
		return gameElement;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
