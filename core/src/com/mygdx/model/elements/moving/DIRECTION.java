package com.mygdx.model.elements.moving;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum DIRECTION {
	UP, DOWN, LEFT, RIGHT;
	
	  private static final List<DIRECTION> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	  private static final int SIZE = VALUES.size();
	  private static final Random RANDOM = new Random();

	  public static DIRECTION randomDirection()  {
		  return VALUES.get(RANDOM.nextInt(SIZE));
	  }

	public DIRECTION opposite() {
		switch(this) {
			case RIGHT: return LEFT;
			case LEFT: return RIGHT;
			case UP: return DOWN;
			default: return UP;
		}
	}

}
