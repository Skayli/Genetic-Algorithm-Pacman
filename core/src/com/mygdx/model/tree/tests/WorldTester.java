package com.mygdx.model.tree.tests;

import com.mygdx.model.World;

public abstract class WorldTester {
	
	World world;
	
	public WorldTester(World world) {
		this.world = world;
	}
	
	public abstract boolean evaluateWorld();
	
}
