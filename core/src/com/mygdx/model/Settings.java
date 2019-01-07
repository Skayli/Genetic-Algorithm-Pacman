package com.mygdx.model;

public final class Settings {

	//Debug
	public final static boolean DEBUGCOLLISION = false;
	public final static boolean DEBUGTEXT = false;
	public final static int seuilActualisationDebug = 1;
	
	//Vitesse
	public final static float normalSpeed = .125f;
	
	//Direction
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int DOWN = 3;
	
	//Seuil d'actualisation des textures
	public final static int SEUILPACMAN = 500;
	public final static int SEUILGHOSTS = 250;
	public final static int SEUILSP = 500;
	
	//Etat des fantomes
	public final static int NORMAL = 0;
	public final static int ESCAPING = 1;
	public final static int BLINKING = 2;
	public final static int DEAD = 3;
	
	public final static int DURATIONBEFOREBLINKING = 7;
	public final static int DURATIONSUPERPACGUM = 10;
	public final static double DURATIONBLINK = 0.250;
	
	public final static int PACGUMVALUE = 20;
	public final static int SUPERPACGUMVALUE = 50;
	
	public final static int[] GHOSTVALUE = new int[]{200,400,800,1600};
	
	public final static int DISPLAYPOINTSABOVEGHOST = 2;
	public static final boolean DEBUGALGOPCC = true;
	
	public static final int SEUILDEATHGHOST = 5;
	public static final int PACMANDEATHDURATION = 3;
}
