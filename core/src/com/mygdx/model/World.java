package com.mygdx.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.SuperPacGum;
import com.mygdx.model.elements.blocks.Dark;
import com.mygdx.model.elements.moving.DIRECTION;
import com.mygdx.model.elements.moving.Vect2D;
import com.mygdx.model.elements.moving.ghosts.Blinky;
import com.mygdx.model.elements.moving.ghosts.Clyde;
import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.elements.moving.ghosts.GhostState;
import com.mygdx.model.elements.moving.ghosts.Inky;
import com.mygdx.model.elements.moving.ghosts.Pinky;
import com.mygdx.model.elements.moving.pacman.Pacman;
import com.mygdx.model.tree.CustomFileWriter;
import com.mygdx.model.tree.Node;
import com.mygdx.model.tree.tests.WorldTester;
import com.mygdx.view.textures.TextureFactory;

public class World implements Iterable<GameElement> {

	private Pacman currentPacman;
	
	private Maze maze;
	
	private ArrayList<PacGum> PG;
	private SuperPacGum SP_BottomLeft; 
	private SuperPacGum SP_TopLeft;
	private SuperPacGum SP_BottomRight;
	private SuperPacGum SP_TopRight;
	
	private ArrayList<Ghost> ghosts;
	private Blinky blinky;
	private Inky inky;
	private Pinky pinky;
	private Clyde clyde;
	
	private final int[] GHOSTVALUE = new int[]{200,400,800,1600}; //Valeur des fantomes au fur et à mesure qu'ils sont mangés
	private final int SUPERPACGUMDURATION = 10; //Durée d'utilité de la SP
	private final int DURATIONBEFOREBLINKING = 7; //Durée avant que les fantomes ne se mettent à flasher
	private final double DURATIONBLINK = 0.250; //Durée avant de changer la texture des fantomes quand ils flashent
	
	private boolean superPacgumEatenRecently;
	private float deltaSinceSuperPacGumEaten;
	private int ghostsEatenSinceLastSP;
	private double deltaBlink;
	
	// Var relatives à l'algorithme génétique
	private int currentGenerationNumber = 0;
	private int currentAgentNumber = 0;
	private ArrayList<Pacman> population;
	private int nbAgentPerGeneration = 100;
	private int maxDepthFirstGeneration = 2;
	private double baseMutationRate = .1;
	public double mutationRate = baseMutationRate;
	private int numberOfParentsToChooseFrom = 8;
	private int printEveryXGeneration = 200;
	private Random worldRand = new Random();
	private int scoreMaxPreviousGen = -1;
	double modifMutationRate = 0.05;
	boolean isPacmanBlocked = false;
	
	public World() {
		WorldTester.world = this;
		
		this.maze = new Maze(this);
		
		/** Fantomes **/
		this.ghosts = new ArrayList<Ghost>();
		this.blinky = new Blinky(this, new Vect2D(12,16), DIRECTION.DOWN);
		this.pinky = new Pinky(this, new Vect2D(13,16), DIRECTION.DOWN);
		this.inky = new Inky(this, new Vect2D(14,16), DIRECTION.DOWN);
		this.clyde = new Clyde(this, new Vect2D(15,16), DIRECTION.DOWN);
		
		ghosts.add(blinky);
		ghosts.add(pinky);
		ghosts.add(inky);
		ghosts.add(clyde);
		
		// Generation of the first population
		population = new ArrayList<Pacman>();
		createFirstGeneration();
		currentPacman = population.get(currentAgentNumber);
		
		CustomFileWriter.getInstance().printToFile("*** Configurations ***");
		CustomFileWriter.getInstance().printToFile("Taille population : " + nbAgentPerGeneration);
		CustomFileWriter.getInstance().printToFile("Profondeur max 1ère génération : " + maxDepthFirstGeneration);
		CustomFileWriter.getInstance().printToFile("Taux de mutation de base : " + (baseMutationRate*100) + "%");
		CustomFileWriter.getInstance().printToFile("Modif du taux de mutation en stagnation : " + (modifMutationRate*100) + "%");
		CustomFileWriter.getInstance().printToFile("Nombre de parents pour le tournoi : " + numberOfParentsToChooseFrom);
		
		init();
	}
	
	
	public void init() {
		// Place element
		for(Ghost ghost : ghosts) {
			ghost.setPositionToSpawn();
		}
		currentPacman.setPositionToSpawn();
		
		// Reset random for ghost
		Ghost.resetRandomGenerator();
		
		/** Pacgums **/
		PG = new ArrayList<PacGum>();
		
		this.SP_BottomLeft = new SuperPacGum(this,new Vect2D(1,7));
		this.SP_TopLeft = new SuperPacGum(this,new Vect2D(26,7));
		this.SP_BottomRight = new SuperPacGum(this,new Vect2D(1,27));
		this.SP_TopRight = new SuperPacGum(this,new Vect2D(26,27));
				
		PG.add(SP_BottomLeft);
		PG.add(SP_TopLeft);
		PG.add(SP_BottomRight);
		PG.add(SP_TopRight);
		
		for(GameElement element : this) {
			if(!overlapsSuperPacGum(element) && element instanceof Dark ) { 
				PG.add(new PacGum(this, element.position, 1, 1));
			}
		}

		superPacgumEatenRecently = false;
		deltaSinceSuperPacGumEaten = 0;
		deltaBlink = 0;
		ghostsEatenSinceLastSP = 0;
		
		isPacmanBlocked = false;
		
		TextureFactory.reset();		
		TextureFactory.setWorld(this);
	}
	
	public Maze getMaze() {
		return this.maze;
	}
	
	public int getHeight() {
		return this.maze.getHeight();
	}
	public int getWidth() {
		return this.maze.getWidth();
	}
	
	public Pacman getPacman() {
		return this.currentPacman;
	}
	
	public ArrayList<PacGum> getPacGumList() {
		return PG;
	}
	
	public ArrayList<Ghost> getGhostsList() {
		return ghosts;
	}
	
	public Blinky getBlinky() {
		return blinky;
	}
	
	public Pinky getPinky() {
		return pinky;
	}
	
	public Inky getInky() {
		return inky;
	}
	
	public Clyde getClyde() {
		return clyde;
	}
	
	public SuperPacGum getSP_BottomLeft() {
		return SP_BottomLeft;
	}
	
	public SuperPacGum getSP_BottomRight() {
		return SP_BottomRight;
	}
	
	public SuperPacGum getSP_TopLeft() {
		return SP_TopLeft;
	}
	
	public SuperPacGum getSP_TopRight() {
		return SP_TopRight;
	}
	
	public boolean hasSuperPacgumBeEatenRecently() {
		return superPacgumEatenRecently;
	}
	
	public void setSuperPacgumEatenRecently(boolean eaten) {
		this.superPacgumEatenRecently = eaten;
	}
	
	public float getDeltaSinceSuperPacGumEaten() {
		return deltaSinceSuperPacGumEaten;
	}
	
	public void setDeltaSinceSuperPacGumEaten(float delta) {
		deltaSinceSuperPacGumEaten = delta;
	}
	
	@Override
	public Iterator<GameElement> iterator() {
		return new WorldIterator(this);
	}
	
	public void processPacgumEaten(PacGum pacgum) {
		PG.remove(pacgum);
//		AudioFactory.getInstance().playMunch();
		updatePacmanScore(pacgum.value);
		
		if(pacgum.isSuper) {
			this.ghostsEatenSinceLastSP = 0;
			this.superPacgumEatenRecently = true;
			setDeltaSinceSuperPacGumEaten(0);
			deltaBlink = 0;
			for(Ghost ghost : this.ghosts) {
				if(!ghost.isInGhostHouse() && !ghost.isDead()) { 
					ghost.setStateToEscaping();
				}		
			}
		}
	}

	
	public void movePacmanAndGhosts() {
		Vect2D p = currentPacman.position;
		
		this.currentPacman.deplacer();
		
		isPacmanBlocked = currentPacman.position.isEqualTo(p);
		
		for(Ghost g : ghosts) {
			g.deplacer();
		}
	}
	
	private boolean overlapsSuperPacGum(GameElement element) {
		if( (element.isOverlaping(SP_BottomLeft) ||
				element.isOverlaping(SP_BottomRight) ||
				element.isOverlaping(SP_TopLeft) ||
				element.isOverlaping(SP_TopRight)) ) {
			return true;
		} else {
			return false;
		}
	}
	
	public void processCollisionPacmanGhost(Ghost ghost) {
		if(currentPacman.isOverlaping(ghost)) {
			if(ghost.canBeEaten()) {
				ghost.setStateDead();
				this.updatePacmanScore(GHOSTVALUE[getNbGhostEatenSinceSuperPacGumEaten()]);
				this.incrementNbGhostEatenSinceSuperPacGumEaten();
			} else if(!ghost.isDead()) {
				currentPacman.setDead(true);
			}
		}
	}
	
	public void updatePacmanScore(int value) {
		currentPacman.score += value;
	}
	
	public int getNbGhostEatenSinceSuperPacGumEaten() {
		return ghostsEatenSinceLastSP;
	}
	
	public void incrementNbGhostEatenSinceSuperPacGumEaten() {
		if(ghostsEatenSinceLastSP < 3) {
			ghostsEatenSinceLastSP++;
		}
	}
	

	/**
	 * Met à jour l'état des fantomes.
	 * Si avant la durée du blink : rien
	 * Sinon blink toutes les 250 ms
	 * reset à vivant s'ils ne sont pas mangé avant la fin de la durée de la SP
	 * @param delta durée depuis dernier rafraichissement
	 */
	public void updateGhostsStates(float delta) {
		setDeltaSinceSuperPacGumEaten(getDeltaSinceSuperPacGumEaten() + delta);
		
		if(getDeltaSinceSuperPacGumEaten() >= DURATIONBEFOREBLINKING && getDeltaSinceSuperPacGumEaten() < SUPERPACGUMDURATION) { 
			deltaBlink += delta;
			
			for(Ghost ghost : getGhostsList()) {
				if((ghost.getState() == GhostState.ESCAPING || ghost.getState() == GhostState.BLINKING) && deltaBlink > DURATIONBLINK) {
					ghost.switchEscapingBlinking();
				}
			}
			
			if(deltaBlink > DURATIONBLINK) {
				deltaBlink = 0;
			}
			
		} else if(getDeltaSinceSuperPacGumEaten() > SUPERPACGUMDURATION) { //après 10 secondes, les fantomes repassent à l'état normal
			for(Ghost ghost : getGhostsList()) {
				if(ghost.getState() == GhostState.ESCAPING || ghost.getState() == GhostState.BLINKING) {
					ghost.setStateToAlive();
				}
			}

			setSuperPacgumEatenRecently(false);
		}
		
	}
	
	public void play(float delta) {		
		movePacmanAndGhosts();
		
		for(Ghost ghost : getGhostsList()) {
			processCollisionPacmanGhost(ghost);
		}
		
		if(hasSuperPacgumBeEatenRecently()) {
			updateGhostsStates(delta);
		}
		
		for(PacGum p : getPacGumList()) {
			if(getPacman().hasReachCenter(p)) {
				processPacgumEaten(p);
				break;
			}
		}		
	}
	
	public void initNextPacman() {
			currentAgentNumber++;
			if(currentAgentNumber >= nbAgentPerGeneration) {
				// Next Gen
				currentGenerationNumber++;
				currentAgentNumber = 0;
				createNewGeneration();
			}
			
			currentPacman = population.get(currentAgentNumber);
			init();
	}
	
	private void createFirstGeneration() {
		System.out.println("printing generation tree to file");
		
		for(int i = 0; i < nbAgentPerGeneration; i++) {
			Pacman agent = new Pacman(this);
			agent.setBrain(Node.generateRandomTree(maxDepthFirstGeneration));
			population.add(agent);
		}
		
//		printGeneration();
	}
	
	private void printGeneration() {
		CustomFileWriter.getInstance().printToFile("-----------------------------------------------------------------------");
		CustomFileWriter.getInstance().printToFile("Generation " + (currentGenerationNumber+1) + "\n");
		
		for(int i = 0; i < nbAgentPerGeneration; i++) {
			CustomFileWriter.getInstance().printToFile("Pacman N°" + (i+1));
			population.get(i).getBrain().saveToFile();
			CustomFileWriter.getInstance().printToFile("************************************************************************");
		}
	}
	
	/**
	 * Crée la nouvelle génération de pacman en appliquant un algorithme génétique
	 */
	private void createNewGeneration() {
		ArrayList<Pacman> newPop = new ArrayList<Pacman>();
		
		
		if(currentGenerationNumber > 1) {
			int scoreMaxGen = -1;
			for(Pacman p : population) {
				if(p.score > scoreMaxGen) {
					scoreMaxGen = p.score;
				}
			}
		
			if(scoreMaxGen == scoreMaxPreviousGen) {
				this.mutationRate = Math.min(mutationRate + modifMutationRate, 0.9);
			} else {
				scoreMaxPreviousGen = scoreMaxGen;
				this.mutationRate = this.baseMutationRate;
			}
		} else {
			for(Pacman p : population) {
				if(p.score > scoreMaxPreviousGen) {
					scoreMaxPreviousGen = p.score;
				}
			}
		}
				
		printPreviousGenStats(); //print to console
		CustomFileWriter.getInstance().printGenerationStats(this, population); //print to file
		
		/**
		 * Tant que le taux de mutation est <= à 50% on créé une nouvelle génération normale
		 */
		if(mutationRate <= 0.5) { 
			for(int i = 0; i < nbAgentPerGeneration; i+=2) {
				
				/** PREMIERE ETAPE : SELECTION **/
				
				Pacman parent1, parent2;
				
				parent1= selectionParent(numberOfParentsToChooseFrom);
				do {
					parent2 = selectionParent(numberOfParentsToChooseFrom);
				} while(parent1 == parent2);
				
				/** 2EME ETAPE : CROISEMENT **/
				
				Pacman[] children = new Pacman[2];
				children = croisementPacman(parent1, parent2);
				
				Pacman child1 = children[0];
				Pacman child2 = children[1];
				
				/** 3EME ETAPE : MUTATATION **/
				
				if(worldRand.nextDouble() < mutationRate) {
					child1.setBrain(Node.appplyMutation(child1.getBrain()));
				}
				
				if(worldRand.nextDouble() < mutationRate) {
					child2.setBrain(Node.appplyMutation(child2.getBrain()));
				}			
				
				newPop.add(child1);
				newPop.add(child2);
			}
			
			population = newPop;
			
		} else { //Sinon => APOCALYPSE
			
			mutationRate = baseMutationRate;
			
			CustomFileWriter.getInstance().printToFile("----------");
			CustomFileWriter.getInstance().printToFile("APOCALYPSE");
			CustomFileWriter.getInstance().printToFile("----------");
			
			System.out.println("----------");
			System.out.println("APOCALYPSE");
			System.out.println("----------");
			
			ArrayList<Pacman> bestPacmans = new ArrayList<Pacman>();
			
			for(Pacman p : population) {
				if(bestPacmans.size() < 10) {
					bestPacmans.add(p);
				} else {
					for(Pacman bestP : bestPacmans) {
						if(p.score > bestP.score) {
							bestP = p;
							break;
						}
						
					}
				}
			}
			
			for(int i = bestPacmans.size(); i < nbAgentPerGeneration; i++) {
				Pacman p = new Pacman(this);
				p.setBrain(Node.generateRandomTree(maxDepthFirstGeneration));
				
				bestPacmans.add(p);
			}
			
			population = bestPacmans;
		}
		
		if(((currentGenerationNumber+1) % printEveryXGeneration) == 0) {
		//	printGeneration();
		}

	}
	
	private void printPreviousGenStats() {
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
		
		moyenne /= nbAgentPerGeneration;
		
		System.out.println("************************");
		System.out.println("Génération " + currentGenerationNumber);
		System.out.println("Score max : " + scoreMax);
		System.out.println("Score min : " + scoreMin);
		System.out.println("Score moy   : " + moyenne);
		System.out.println("Taux de mutation : " + this.mutationRate);
		
	}

	private Pacman selectionParent(int nbConcurrents) {
		ArrayList<Pacman> participants = new ArrayList<Pacman>();
		
		for(int i = 0; i < nbConcurrents; i++) {
			
			Pacman newChallenger = population.get(worldRand.nextInt(population.size()));
			
			if(!participants.contains(newChallenger)) {
				participants.add(newChallenger);
			} else {
				i--;
			}
		}
		
		Pacman bestPacman = participants.get(0);
		for(int i = 1; i < nbConcurrents; i++) {
			if(bestPacman.score < participants.get(i).score) {
				bestPacman = participants.get(i);
			}
		}
	
		return bestPacman;
	}
	
	private Pacman[] croisementPacman(Pacman parent1, Pacman parent2) {
		Pacman fils1 = parent1.clone();
		Pacman fils2 = parent2.clone();
		
		Node [] newBrains = Node.croisement(fils1.getBrain(), fils2.getBrain());
		
		fils1.setBrain(newBrains[0]);
		fils2.setBrain(newBrains[1]);
		
		Pacman[] children = {fils1, fils2};
		
		return children;
	}

	public int getCurrentGenerationNumber() {
		return currentGenerationNumber;
	}

	public int getCurrentAgentNumber() {
		return currentAgentNumber;
	}

	public int getNbAgentPerGeneration() {
		return nbAgentPerGeneration;
	}

	public boolean isPacmanBlocked() {
		return isPacmanBlocked;
	}
	
}
