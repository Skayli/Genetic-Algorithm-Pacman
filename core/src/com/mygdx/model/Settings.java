package com.mygdx.model;

public final class Settings {
	
	//Vitesse
	public final static double normalSpeed = .13;
	
	//Direction
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int DOWN = 3;
	
	//Seuil d'actualisation des textures
	public final static int SEUILPACMAN = 500;
	public final static int SEUILGHOSTS = 250;
	public final static int SEUILSUPERPACGUM = 500;
	
	public final static int DURATIONBEFOREBLINKING = 7;
	public final static int DURATIONSUPERPACGUM = 10;
	public final static double DURATIONBLINK = 0.250;
	
	public final static int PACGUMVALUE = 20;
	public final static int SUPERPACGUMVALUE = 50;
	
	public final static int[] GHOSTVALUE = new int[]{200,400,800,1600}; //Valeur des fantomes au fur et à mesure qu'ils sont mangés
	
	public final static int DISPLAYPOINTSABOVEGHOST = 2; //Durée d'affichage des points au dessus des fantomes
	public static final boolean DEBUGALGOPCC = false;
	
	public static final int SEUILDEATHGHOST = 5;
	public static final int PACMANDEATHDURATION = 3;
}
