package com.mygdx.model.tree;

import java.io.PrintWriter;
import java.util.ArrayList;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.DIRECTION;

public abstract class Node {
	
	protected static int numberOfInstances = 0;
	
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
	
//	public void printToFile(PrintWriter writer) {		
//		writer.write(this.toString());
//		writer.println();
//		
//		for(Node n : children) {
//			n.printToFile(writer);
//		}
//					
//	}
	
//	public String toString() {
//		return "[Abstract node | N°" + numero + " | Parent N° " + (parent != null ? parent.numero : "NULL") + "]";
//	}
}
