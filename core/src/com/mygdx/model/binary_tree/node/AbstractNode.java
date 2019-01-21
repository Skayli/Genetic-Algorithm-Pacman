package com.mygdx.model.binary_tree.node;

import java.io.PrintWriter;
import java.util.ArrayList;
import com.mygdx.model.elements.moving.Direction;

public abstract class AbstractNode {
	
	protected static int number = 0;
	
	protected AbstractNode parent;
	protected Direction value;
	protected int numero;
	
	protected ArrayList<AbstractNode> children;
	
	// CONSTRUCTOR
	
	public AbstractNode() {
		numero = ++number;
		children = new ArrayList<AbstractNode>();
		this.parent = null;
	}

	// ABSTRACT FUNCTIONS
	
	public abstract Direction getValue();

	// GETTERS & SETTERS
	
	/** depth **/ 
	public int getNumero() {
		return this.numero;
	}
	
	public void addChild(AbstractNode newChild) {
		
		if(newChild != this && !children.contains(newChild)) {
			if(newChild.parent != null)  {
				newChild.parent.children.remove(newChild);
			}
			
			newChild.parent = this;
			children.add(newChild);
		}
	}
	
	public ArrayList<AbstractNode> getChildren() {
		return children;
	}
	
	public void printToFile(PrintWriter writer) {		
		writer.write(this.toString());
		writer.println();
		
		for(AbstractNode n : children) {
			n.printToFile(writer);
		}
					
	}
	
	public String toString() {
		return "[Abstract node | N°" + numero + " | Parent N° " + (parent != null ? parent.numero : "NULL") + "]";
	}
}
