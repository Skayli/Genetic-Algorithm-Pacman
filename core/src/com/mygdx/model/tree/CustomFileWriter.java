package com.mygdx.model.tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mygdx.model.World;
import com.mygdx.model.elements.moving.pacman.Pacman;

public class CustomFileWriter {
	
	private static CustomFileWriter instance;
	
	private String treeDescFileName;

	private CustomFileWriter() {
		treeDescFileName = "pacman-tree -- " + new SimpleDateFormat("dd-MM-yyyy -- HH-mm-ss'.txt'").format(new Date());
	}
	
	public static CustomFileWriter getInstance() {
		if(instance == null)
			instance = new CustomFileWriter();
		
		return instance;
	}
	
	public void printToFile(Object o) {
		File file;
		FileWriter fr;
		BufferedWriter br;
		PrintWriter pr;
		
		try {				
			file = new File("../core/assets/trees/" + treeDescFileName);
			file.createNewFile();
			
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			pr = new PrintWriter(br);
			
			pr.println(o);
			
			pr.close();
			br.close();
			fr.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void printGenerationStats(World world, ArrayList<Pacman> population) {
	
		int scoreMax = -1;
		int scoreMin = 50000;
		int moyenne = 0;
		
		for(Pacman p : population) {
			if(p.score > scoreMax)
				scoreMax = p.score;
			
			if(p.score < scoreMin)
				scoreMin = p.score;
			
			moyenne += p.score;
		}
		
		moyenne /= world.getNbAgentPerGeneration();
		
		File file;
		FileWriter fr;
		BufferedWriter br;
		PrintWriter pr;
		
		try {				
			file = new File("../core/assets/trees/" + treeDescFileName);
			file.createNewFile();
			
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			pr = new PrintWriter(br);
			
			pr.println("************************");
			pr.println("Génération " + world.getCurrentGenerationNumber());
			pr.println("Score max : " + scoreMax);
			pr.println("Score min : " + scoreMin);
			pr.println("Score moy   : " + moyenne);
			pr.println("Taux de mutation : " + world.mutationRate);
			
			pr.close();
			br.close();
			fr.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
