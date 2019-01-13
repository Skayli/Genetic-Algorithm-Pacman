package com.mygdx.model.elements.moving;

public enum Direction {
	UP, DOWN, LEFT, RIGHT;

	public Direction opposite() {
		switch(this) {
			case RIGHT: return LEFT;
			case LEFT: return RIGHT;
			case UP: return DOWN;
			default: return UP;
		}
	}
}
