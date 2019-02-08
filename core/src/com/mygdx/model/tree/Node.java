package com.mygdx.model.tree;

import java.io.PrintWriter;
import java.util.ArrayList;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.DIRECTION;

public abstract class Node {
	
	protected static int numberOfInstances = 0;
	protected final static double chanceOfIfNode = 0.75;
	
	protected int numero;
	
	protected Node parent;
	private Node leftChild;
	private Node rightChild;
	
	protected boolean isTerminal;
	protected int depth;
	
		
	// CONSTRUCTOR	
	public Node(Node parent, boolean isTerminal) {
		numero = ++numberOfInstances;
		
		this.parent = parent;
		this.depth = (parent != null ? parent.depth + 1 : 0); //Parent is null => depth: 0 || Parent is not null => depth: parent.depth + 1
		this.isTerminal = isTerminal;
	}

	// ABSTRACT FUNCTIONS
	public abstract DIRECTION evaluateDirection(World world);

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
	
	/**
	 * Return randomly either an empty IfNode or TerminalNode
	 * @return An empty IfNode or TerminalNode 
	 */
	public void generateRandomChildren(int maxDepth) {
		if(depth == maxDepth-1) {
			leftChild = new TerminalNode(this);
			rightChild = new TerminalNode(this);
		} else {
			leftChild = getRandomNode(this);
			rightChild = getRandomNode(this);
			
			if(!leftChild.isTerminal)
				leftChild.generateRandomChildren(maxDepth);
			
			if(!rightChild.isTerminal)
				rightChild.generateRandomChildren(maxDepth);
		}
			
	}
	
	public static Node getRandomNode(Node parent) {
		double random = Math.random();
		
		if(random < Node.chanceOfIfNode)
			return new IfNode(parent, null);
		else
			return new TerminalNode(parent);
	}
	
	public void addToList(ArrayList<Node> list) {
		list.add(this);
		
		if(!isTerminal) {
			leftChild.addToList(list);
			rightChild.addToList(list);
		}
	}
	
	public void printToFile(PrintWriter writer) {		
		writer.write(this.toString());
		writer.println();
					
	}
	
	public String toString() {
		return "[Node | N°" + numero + " | Parent N° " + (parent != null ? parent.numero : "NULL") + " | IsTerminal : " + isTerminal + " | Depth : " + depth + "]";
	}
}
