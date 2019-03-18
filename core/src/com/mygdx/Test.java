package com.mygdx;

import com.mygdx.model.elements.moving.pacman.Pacman;
import com.mygdx.model.tree.CustomFileWriter;
import com.mygdx.model.tree.Node;

public class Test {

	public static void main(String[] args) {		
		Pacman p1 = new Pacman(null);
		Pacman p2 = new Pacman(null);
		
		p1.setBrain(Node.generateRandomTree(1));
		p2.setBrain(Node.generateRandomTree(1));
		
		CustomFileWriter.getInstance().printToFile("Pacman 1 brain");
		p1.getBrain().saveToFile();
		CustomFileWriter.getInstance().printToFile("Pacman 2 brain");
		p2.getBrain().saveToFile();
		
		Pacman[] children = croisementPacman(p1, p2);
		
		CustomFileWriter.getInstance().printToFile("Pacman 3 brain");
		children[0].getBrain().saveToFile();
		CustomFileWriter.getInstance().printToFile("Pacman 4 brain");
		children[1].getBrain().saveToFile();
	}
	
	public static Pacman[] croisementPacman(Pacman parent1, Pacman parent2) {
		Pacman fils1 = parent1.clone();
		Pacman fils2 = parent2.clone();
		
		Node nodef1 = fils1.getBrain().getRandomNodeFromTree();
		Node nodef2 = fils2.getBrain().getRandomNodeFromTree();
		
		if(nodef1.isRoot()) {
			fils1.setBrain(nodef2.clone(null));
			
		} else {
			if(nodef1.isLeftChild()) {
				nodef1.getParent().setLeftChild(nodef2);
			} else {
				nodef1.getParent().setRightChild(nodef2);
			}
		}
		
		if(nodef2.isRoot()) {
			fils2.setBrain(nodef1.clone(null));
			
		} else {
			if(nodef2.isLeftChild()) {
				nodef2.getParent().setLeftChild(nodef1);
			} else {
				nodef2.getParent().setRightChild(nodef1);
			}
		}
		
		fils1.getBrain().applyNumerotation();
		fils2.getBrain().applyNumerotation();
		
		Pacman[] children = {fils1, fils2};
		
		return children;
	}

}
