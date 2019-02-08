package com.mygdx.model.tree;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	
	public void generateRandomTree(int maxDepth) {
		if(maxDepth <= 0) {
			root = new TerminalNode(null);
		} else {
			root = Node.getRandomNode(null);
			root.generateRandomChildren(maxDepth);
		}
	}

	public void save(String filename) {
		System.out.println("writting file");
		
		ArrayList<Node> nodeList = new ArrayList<Node>();
		
		root.addToList(nodeList);
		
		Collections.sort(nodeList, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.getDepth() - o2.getDepth();
			}
		});
		
		try {
			PrintWriter writer;
			writer = new PrintWriter("../core/assets/" +"pacman-tree.txt", "UTF-8");
			
			for(Node n : nodeList) {
				n.printToFile(writer);
			}
		
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
