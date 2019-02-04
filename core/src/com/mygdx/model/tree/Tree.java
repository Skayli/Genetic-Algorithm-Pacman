package com.mygdx.model.tree;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.DIRECTION;

public class Tree {

	private Node root;
	private final World world;
	
	// CONSTRUCTEURS
	public Tree(World world) {
		this.world = world;
		
		this.root = null;
	}
	
	public Tree(World world, Node root) {
		this.world = world;
		this.root = root;
	}

	/**
	 * Ask root and its chilren to evaluate the world
	 * @return A direction (UP, DOWN, LEFT, RIGHT)
	 */
	public DIRECTION getDirection() {
		return root.evaluateDirection(world);
	}
	
	public Node getRoot() {
		world.setDeltaSinceSuperPacGumEaten(10);
		return root;
	}
	
	public void setRoot(Node newRoot) {
		this.root = newRoot;
	}

//	public void save(String filename) {
//		System.out.println("writting file");
//		  
//		try {
//			PrintWriter writer;
//			writer = new PrintWriter("../core/assets/" +"pacman-tree.txt", "UTF-8");
//			
//			root.printToFile(writer);
//		
//			writer.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
	
	
	

}
