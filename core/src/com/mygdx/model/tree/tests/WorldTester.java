package com.mygdx.model.tree.tests;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Random;
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
	
	public static WorldTester getRandomTester() {
		Reflections reflections = new Reflections("com.mygdx.model.tree.tests");

		Set<Class<? extends WorldTester>> subTypes = reflections.getSubTypesOf(WorldTester.class);
		
		int item = new Random().nextInt(subTypes.size());
		int i = 0;
		
		System.out.println("test" + item);
		
		WorldTester test = null;
		
		for(Class<?> o : subTypes) {
			if(i == item) {
				 try {
					test = (WorldTester) o.newInstance();
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
		
		System.out.println(test);
		return test;
	}
	
}
