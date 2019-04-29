//package com.mygdx.model.tree;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Random;
//
//import com.mygdx.model.elements.moving.DIRECTION;
//
//public class Tree {
//
//	Node root;
//	
//	// CONSTRUCTEURS
//	public Tree() {
//		this.generateRandomTree(0);
//	}
//	
//	public Tree(Node root) {
//		this.root = root;
//	}
//
//	/**
//	 * Ask root and its chilren to evaluate the world
//	 * @return A direction (UP, DOWN, LEFT, RIGHT)
//	 */
//	public DIRECTION getDirection() {
//		return root.evaluateDirection();
//	}
//	
//	public Node getRoot() {
//		return root;
//	}
//	
//	public void setRoot(Node newRoot) {
//		this.root = newRoot;
//	}
//	
//	public void generateRandomTree(int maxDepth) {
//		if(maxDepth <= 0) {
//			root = new TerminalNode(null);
//		} else {
//			root = Node.getRandomNode(null);
//			root.generateRandomChildren(maxDepth);
//		}
//		
//		Node.numberOfInstances = 0;
//	}
//
//	/**
//	 * Enregistre tous les nodes de l'arbres dans un fichier
//	 * Le nom est généré par le singleton de CustomFileWriter
//	 */
//	public void saveToFile() {		
//		ArrayList<Node> nodeList = new ArrayList<Node>();
//		
//		root.addToList(nodeList);
//		
//		Collections.sort(nodeList, new Comparator<Node>() {
//			@Override
//			public int compare(Node o1, Node o2) {
//				return o1.getDepth() - o2.getDepth();
//			}
//		});
//		
//		for(Node n : nodeList) {
//			CustomFileWriter.getInstance().printToFile(n);
//		}
//				
//	}
//	
//	public Node getRandomNode() {
//		ArrayList<Node> nodeList = new ArrayList<Node>();
//		root.addToList(nodeList);
//		
//		Random random = new Random();
//		
//		return nodeList.get(random.nextInt(nodeList.size()));
//	}
//	
//	public Tree clone() {
//		Tree clone = new Tree();
//		clone.root = root.clone(null);
//		
//		return clone;
//	}
//
//	public void mutate(int depthMutation) {
//		Tree newTree = new Tree();
//		newTree.generateRandomTree(depthMutation);
//		
//		Node node = this.getRandomNode();
//		if(node.parent != null) {
//			if(node.parent.leftChild == node) {
//				node.parent.leftChild = newTree.root;
//			} else {
//				node.parent.rightChild = newTree.root;
//			}
//		} else {
//			this.root = newTree.root;
//		}
//	}
//
//}
