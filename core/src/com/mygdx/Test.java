package com.mygdx;

import com.mygdx.model.tree.CustomFileWriter;
import com.mygdx.model.tree.IfNode;
import com.mygdx.model.tree.Node;

public class Test {

	public static void main(String[] args) {
		Node n1 = Node.generateRandomTree(0);
		Node n2 = Node.generateRandomTree(0);
		
		CustomFileWriter.getInstance().printToFile("Tree1");
		n1.saveToFile();
		
		CustomFileWriter.getInstance().printToFile("Tree2");
		n2.saveToFile();
		
		Node f1, f2;
		
		CustomFileWriter.getInstance().printToFile("--------------------------------------------------");
		
		Node [] children = Node.croisement(n1,n2);
		
		f1 = children[0];
		f2 = children[1];
		
		CustomFileWriter.getInstance().printToFile("--------------------------------------------------");
		
		CustomFileWriter.getInstance().printToFile("Tree1");
		f1.saveToFile();
		
		CustomFileWriter.getInstance().printToFile("Tree2");
		f2.saveToFile();
		
	}
	

}
