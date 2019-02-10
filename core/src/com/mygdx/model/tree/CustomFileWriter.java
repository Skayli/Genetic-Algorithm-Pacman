package com.mygdx.model.tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mygdx.model.World;

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
}
