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

	/**
	 * Enregistre tous les nodes de l'arbres dans un fichier
	 * Le nom est généré par le singleton de CustomFileWriter
	 */
	public void saveToFile() {
		System.out.println("writting file");
		
		ArrayList<Node> nodeList = new ArrayList<Node>();
		
		root.addToList(nodeList);
		
		Collections.sort(nodeList, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.getDepth() - o2.getDepth();
			}
		});
		
		for(Node n : nodeList) {
			CustomFileWriter.getInstance().printToFile(n);
		}
				
	}

}
