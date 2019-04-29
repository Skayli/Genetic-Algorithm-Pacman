package com.mygdx.model.tree.tests;

import java.util.Random;
import java.util.Set;

import org.reflections.Reflections;

import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;

public abstract class WorldTester {
	
	public static World world;
	public static Random r = new Random();
	
	static Reflections reflections = new Reflections("com.mygdx.model.tree.tests");
	static Set<Class<? extends WorldTester>> subTypes = reflections.getSubTypesOf(WorldTester.class);
	
	public WorldTester() throws WorldTesterException {
		
	}
	
	public abstract boolean evaluateWorld();
	public abstract String toString();
	
	public static WorldTester getRandomTester() {		
		int item = r.nextInt(subTypes.size());
		int i = 0;
		
		WorldTester test = null;
		
		for(Class<?> c : subTypes) {
			if(i == item) {
				 try {
					test = (WorldTester) c.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
			i++;
		}
		
		return test;
	}
	
	protected boolean isPacmanCloseTo(GameElement elem) {
		double distX = world.getPacman().position.x - elem.position.x;
		double distY = world.getPacman().position.y - elem.position.y;
		
		return distX*distX + distY*distY < 96*96;
		
	}
	
	public abstract WorldTester clone();
}
