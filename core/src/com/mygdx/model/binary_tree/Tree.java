package com.mygdx.model.binary_tree;

import java.io.PrintWriter;
import com.mygdx.model.binary_tree.node.AbstractNode;
import com.mygdx.model.elements.moving.Direction;

public class Tree {

	private AbstractNode root;
	
	public Tree() {
		this.root = null;
	}
	
	public Tree(AbstractNode root) {
		this.root = root;
	}

	public Direction getDirection() {
		return root.getValue();
	}
	
	public AbstractNode getRoot() {
		return root;
	}
	
	public void setRoot(AbstractNode newRoot) {
		this.root = newRoot;
	}

	public void save(String filename) {
		System.out.println("writting file");
		  
		try {
			PrintWriter writer;
			writer = new PrintWriter("../core/assets/" +"pacman-tree.txt", "UTF-8");
			
			root.printToFile(writer);
		
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
