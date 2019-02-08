package com.mygdx.model.tree.tests;

import com.mygdx.model.World;

public abstract class WorldTester {
	
	public static World world;
	
	public WorldTester() {
		
	}
	
	public abstract boolean evaluateWorld();
	public abstract String toString();
	
}
