package com.mygdx.model.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.tree.tests.WorldTester;

public abstract class Node {
	
	protected static int numberOfInstances = 0;
	protected final static double chanceOfIfNode = 1;
	
	protected int numero;
	
	public Node parent;
	public Node leftChild;
	public Node rightChild;
	
	public boolean isTerminal;
	protected int depth;
	
		
	// CONSTRUCTOR	
	public Node(Node parent, boolean isTerminal) {
		this.numero = 0;
		this.parent = parent;
		this.isTerminal = isTerminal;
		this.depth = (parent == null ? 0 : parent.getDepth() + 1);
	}

	// ABSTRACT FUNCTIONS
	/**
	 * Evaluate the world through multiple nodes
	 * @return A direction
	 */
	public abstract DIRECTION evaluateDirection();

	// GETTERS & SETTERS
	public int getNumero() {
		return this.numero;
	}
	
	public int getDepth() {
		return this.depth;
	}
	
	public Node getLeftChild() {
		return leftChild;
	}
	
	public Node getRightChild() {
		return rightChild;
	}
	
	public IfNode getParent() {
		return (IfNode) parent;
	}
	
	public boolean isLeftChild() {
		return parent != null && parent.getLeftChild() == this;
	}
	
	public boolean isRightChild() {
		return parent != null && parent.getRightChild() == this;
	}
	
	public boolean isRoot() {
		return parent == null;
	}
	
	public Node getRoot() {
		if(!this.isRoot())
			return this.getParent();
		
		return this;
	}
	
	/**
	 * Genere un arbre aléatoire jusqu'a une profondeur donnée
	 * @param maxDepth profondeur 
	 * @return la racine de l'arbre
	 */
	public static Node generateRandomTree(int maxDepth) {
		Node root;
		
		if(maxDepth <= 0) {
			root = new TerminalNode(null);
		} else {
			root = Node.createRandomNode(null);
			if(!root.isTerminal)
				root.generateRandomChildren(maxDepth);
		}
		
		root.applyNumerotation();
		return root;
	}
	
	/**
	 * Generate random children until maxDepth is reached
	 * If the node is a TerminalNode, you don't generate children 
	 */
	private void generateRandomChildren(int maxDepth) {
		if(depth == maxDepth-1) {
			leftChild = new TerminalNode(this);
			rightChild = new TerminalNode(this);
		} else {
			leftChild = Node.createRandomNode(this);
			rightChild = Node.createRandomNode(this);
			
			if(!leftChild.isTerminal)
				leftChild.generateRandomChildren(maxDepth);
			
			if(!rightChild.isTerminal)
				rightChild.generateRandomChildren(maxDepth);
		}
			
	}
	
	/**
	 * Generate a random node (Terminal or If) 
	 * @param parent The parent of the node
	 * @return A random Node
	 */
	public static Node createRandomNode(Node parent) {
		double random = Math.random();
		
		if(random < Node.chanceOfIfNode)
			return new IfNode(parent, WorldTester.getRandomTester());
		else
			return new TerminalNode(parent);
	}
	
	/**
	 * Populate an arrayList of treeNodes from this and all its children
	 * @param list
	 */
	public void addToList(ArrayList<Node> list) {
		list.add(this);
		
		if(!isTerminal) {
			leftChild.addToList(list);
			rightChild.addToList(list);
		}
	}
	
	/**
	 * 
	 * @return un noeud aléatoire de l'arbre
	 */
	public Node getRandomNodeFromTree() {
		Node root = this.getRoot();
		ArrayList<Node> l = new ArrayList<Node>();
		
		root.addToList(l);
		
		Random r = new Random();
		
		return l.get(r.nextInt(l.size()));
	}
	
	/**
	 * Enregistre tous les nodes de l'arbres dans un fichier
	 * Le nom est généré par le singleton de CustomFileWriter
	 */
	public void saveToFile() {		
		this.applyNumerotation();
		
		ArrayList<Node> nodeList = new ArrayList<Node>();
		
		this.addToList(nodeList);
		
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
	
	public String toString() {
		String leftOrRightChild = (parent != null ? (parent.leftChild == this ? "L" : "R") : "ø"); 
		return "[Node " + numero + " | Parent : " + (parent != null ? parent.numero : "ø") + " | Child : " + leftOrRightChild +  " | Depth : " + depth + " |";
	}
	
	public abstract Node clone(Node parent);
	
	/**
	 * Apply a mutation of depth to the tree
	 * Create a random Tree of max length depth
	 * replace a random selected node by the random tree
	 * @param depth
	 * @return a new tree with a mutation
	 */
	public static Node appplyMutation(Node node, int depth) {
		Node clone = node.getRoot().clone(null);
		Node randomTree = Node.generateRandomTree(depth);
		Node randomNode = clone.getRandomNodeFromTree();
		
//		CustomFileWriter.getInstance().printToFile("--------------------------------");
//		CustomFileWriter.getInstance().printToFile("MUTATION APPLIQUEE");
//		randomTree.saveToFile();
//		CustomFileWriter.getInstance().printToFile("*********************************");
//		CustomFileWriter.getInstance().printToFile("NODE MODIFIE");
//		CustomFileWriter.getInstance().printToFile(randomNode);
		
		if(randomNode.isRoot()) {
			return randomTree;
		} else {
			if(randomNode.isLeftChild()) {
				randomNode.getParent().setLeftChild(randomTree);
			} else {
				randomNode.getParent().setRightChild(randomTree);
			}
		}
		
		return clone;
	}
	
	private void applyNumerotation() {
		Node root = this.getRoot();
		numberOfInstances = 0;
		root.depth = 0;
		root.num();
		
	}
	
	private void num() {		
		this.numero = ++numberOfInstances;				
		
		if(!this.isTerminal) {
			leftChild.depth = this.depth + 1;
			rightChild.depth = this.depth + 1;
			leftChild.num();
			rightChild.num();
		}
	}
	
	public static Node[] croisement(Node tree1, Node tree2) {
		Node copy1 = tree1.clone(null);
		Node copy2 = tree2.clone(null);
		
		Node select1 = copy1.getRandomNodeFromTree();
		Node parentSelect1 = select1.getParent();
		Node temp = select1.clone( (parentSelect1 == null ? null : parentSelect1.clone(null)) );
		
		Node select2 = copy2.getRandomNodeFromTree();
		Node parentSelect2 = select2.getParent();
		
//		CustomFileWriter.getInstance().printToFile("Node Tree1");
//		CustomFileWriter.getInstance().printToFile(select1);
//		
//		CustomFileWriter.getInstance().printToFile("*************************************");
//		
//		CustomFileWriter.getInstance().printToFile("Node Tree2");
//		CustomFileWriter.getInstance().printToFile(select2);
		
		select1.parent = parentSelect2;
		if(!select2.isRoot()) {
			if(select2.isLeftChild()) {
				((IfNode) parentSelect2).setLeftChild(select1);
			} else {
				((IfNode) parentSelect2).setRightChild(select1);
			}
		} else {
			copy2 = select1;
		}
		
		select2.parent = parentSelect1;
		if(!temp.isRoot()) {
			if(temp.isLeftChild()) {
				((IfNode) parentSelect1).setLeftChild(select2);
			} else {
				((IfNode) parentSelect1).setRightChild(select2);
			}
		} else {
			copy1 = select2;
		}
		
		Node[] children = {copy1, copy2};
		
		return children;
	}

}
