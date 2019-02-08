package com.mygdx.model.tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.DIRECTION;

public class Tree {

	Node root;
	
	// CONSTRUCTEURS
	public Tree() {
		this.root = null;
	}
	
	public Tree(Node root) {
		this.root = root;
	}

	/**
	 * Ask root and its chilren to evaluate the world
	 * @return A direction (UP, DOWN, LEFT, RIGHT)
	 */
	public DIRECTION getDirection() {
		return root.evaluateDirection();
	}
	
	public Node getRoot() {
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

	
	public void saveToFile(String filename) {
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
			File file;
			FileWriter fr;
			BufferedWriter br;
			PrintWriter pr;
			
			file = new File("../core/assets/trees/" + World.treeDescFileName);
			file.createNewFile();
			
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			pr = new PrintWriter(br);
			
			for(Node n : nodeList) {
				n.printToFile(pr);
			}
		
			pr.close();
			br.close();
			fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
