package com.mygdx.model.tree.tests;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.reflections.Reflections;

import com.mygdx.model.World;

public abstract class WorldTester {
	
	public static World world;
	
	public WorldTester() {
		
	}
	
	public abstract boolean evaluateWorld();
	public abstract String toString();
	
	public static void toast() {
		Reflections reflections = new Reflections("com.mygdx.model.tree.tests");

		Set<Class<? extends WorldTester>> subTypes = reflections.getSubTypesOf(WorldTester.class);
		
		for(Class<?> s : subTypes) {
			System.out.println(s.getSimpleName());
		}
	}
	
}
