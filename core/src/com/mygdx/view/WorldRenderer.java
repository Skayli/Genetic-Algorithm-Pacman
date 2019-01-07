package com.mygdx.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.mygdx.model.AudioFactory;
import com.mygdx.model.Blinky;
import com.mygdx.model.Block;
import com.mygdx.model.Clyde;
import com.mygdx.model.Dark;
import com.mygdx.model.GameElement;
import com.mygdx.model.Ghost;
import com.mygdx.model.Inky;
import com.mygdx.model.Intersection;
import com.mygdx.model.PacGum;
import com.mygdx.model.Pacman;
import com.mygdx.model.Pinky;
import com.mygdx.model.PointsGhostEat;
import com.mygdx.model.Settings;
import com.mygdx.model.SuperPacGum;
import com.mygdx.model.World;

public class WorldRenderer {

	private World world;
	private SpriteBatch spriteBatch;
	private float ppuX, ppuY;
	private ShapeRenderer shape;
	private float deltaDebug;
	private float deltaBlink;
	private ArrayList<PointsGhostEat> displayPointAboveGhost;
	private float deltaRender;
	private float deltaTempsTotal;
	
	public WorldRenderer(World world) {
		this.world = world;
		this.spriteBatch = new SpriteBatch();
		shape = new ShapeRenderer();
		deltaDebug = 0;
		deltaBlink = 0;
		displayPointAboveGhost = new ArrayList<PointsGhostEat>();
		AudioFactory.getInstance().setLooping("siren", true);
		deltaRender = 0;
		deltaTempsTotal = 0;
	}
	
	public void render(float delta) {
		
		if(deltaTempsTotal == 0)
			AudioFactory.getInstance().playMusic("intro");
		
		deltaTempsTotal += delta;
		
		deltaRender = delta;
		if(AudioFactory.getInstance().isPlaying("intro") || world.getPacman().isDead() || world.getVies() == 0) {
			deltaRender = 0;
			TextureFactory.getInstance().getTexturable(Pacman.class).resetDelta();
			AudioFactory.getInstance().stopMusic("siren");
			for(Ghost ghost : world.getGhostsList())
				TextureFactory.getInstance().getTexturable(ghost.getClass()).resetDelta();
		} else {
			AudioFactory.getInstance().playMusic("siren");
		}
		
		TexturePacman texturePacman = (TexturePacman) TextureFactory.getInstance().getTexturable(Pacman.class);
		texturePacman.render(deltaRender);
		
		TextureBlinky textureBlinky = (TextureBlinky) TextureFactory.getInstance().getTexturable(Blinky.class);
		textureBlinky.render(deltaRender);
		
		TexturePinky texturePinky = (TexturePinky) TextureFactory.getInstance().getTexturable(Pinky.class);
		texturePinky.render(deltaRender);

		TextureInky textureInky = (TextureInky) TextureFactory.getInstance().getTexturable(Inky.class);
		textureInky.render(deltaRender);

		TextureClyde textureClyde = (TextureClyde) TextureFactory.getInstance().getTexturable(Clyde.class);
		textureClyde.render(deltaRender);

		TextureSuperPacGum textureSPG = (TextureSuperPacGum) TextureFactory.getInstance().getTexturable(SuperPacGum.class);
		textureSPG.render(deltaRender);

		
		spriteBatch.enableBlending();
			
		for(GameElement element : this.world) {	
			
			if(element.color != null)
				spriteBatch.setColor(element.color);
			else
				spriteBatch.setColor(Color.WHITE);
			
			this.spriteBatch.begin();
			this.spriteBatch.draw(
					TextureFactory.getInstance().getTexture(element.getClass()),
					element.getPosition().x * ppuX,
					element.getPosition().y * ppuY,
					element.getWidth() * ppuX,
					element.getHeight() * ppuY
			);
		
			this.spriteBatch.end();
			
			/**Si le debug est actif : recolore les body (utilis�s pour les collisions) des �l�ments
			 * @BlockCollision Gold
			 * @Pacman Rouge
			 * @Intersection Bleu
			 */
			if(Settings.DEBUGCOLLISION) {
				if(element.getClass() == Block.class) {
					shape.begin(ShapeType.Filled);
					shape.setColor(Color.GOLD);
					shape.rect(element.getBody().x *ppuX, element.getBody().y * ppuY, element.getBody().getWidth() * ppuX, element.getBody().getHeight()* ppuY);
					shape.end();
				}
				
				if(element.getClass() == PacGum.class) {
					shape.begin(ShapeType.Filled);
					shape.setColor(Color.BLUE);
					shape.rect(element.getBody().x *ppuX, element.getBody().y * ppuY, element.getBody().getWidth() * ppuX, element.getBody().getHeight()* ppuY);
					shape.end();
				}
				
				if(element.getPosition().epsilonEquals(new Vector2(17,18))) {
					shape.begin(ShapeType.Filled);
					shape.setColor(Color.GREEN);
					shape.rect(element.getBody().x *ppuX, element.getBody().y * ppuY, element.getBody().getWidth() * ppuX, element.getBody().getHeight()* ppuY);
					shape.end();
				}
				
				shape.begin(ShapeType.Filled);
				shape.setColor(Color.RED);
				shape.rect(world.getPacman().getBody().x * ppuX, world.getPacman().getBody().y *  ppuY, world.getPacman().getBody().width * ppuX, world.getPacman().getBody().height *ppuY);
				shape.end();
			}
			
			/** FIN COLORATION DES HITBOX EN DEBUG **/
		}
		
		if(Settings.DEBUGTEXT) {
			deltaDebug += delta;
			if(deltaDebug > Settings.seuilActualisationDebug) {
				System.out.println("===================================================================");
				System.out.println("Position de Pacman : " + this.world.getPacman().getPosition());
				System.out.println("Position de Blinky : " + this.world.getBlinky().getPosition());
				System.out.println("Position de Pinky : " + this.world.getPinky().getPosition());
				System.out.println("Position de Inky : " + this.world.getInky().getPosition());
				System.out.println("Position de Clyde : " + this.world.getClyde().getPosition());
				deltaDebug = 0;
			}
			
			/** FIN DU DEBUG TEXT **/
		}
		
		//Score quand un fantome est mang�
		for(PointsGhostEat pt : displayPointAboveGhost) {
			pt.displayPointsEarned(spriteBatch, ppuX, ppuY, delta);		
		}
		for(PointsGhostEat pt : displayPointAboveGhost) {
			if(pt.shouldBeRemoved())
				pt = null;		
		}
				
		//Score
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.BLACK);
		shape.rect(ppuX,ppuY*12, ppuX*4, ppuY*3);
		shape.rect(ppuX*23, ppuY*12, ppuX*4, ppuY*3);
		shape.end();
		
		spriteBatch.begin();
		
		BitmapFont font  = new BitmapFont();
		font.setColor(Color.WHITE);
		font.draw(spriteBatch, "Score : "+world.getScore(), ppuX, ppuY*13.9f);
		int beginLifesDraw = 23;
		font.draw(spriteBatch, "Vies", ppuX*(beginLifesDraw+1), ppuY*14.5f);
		
		for(int lifes = 0; lifes < world.getVies(); lifes++)
			spriteBatch.draw(new Texture(Gdx.files.internal("images/heart.png")), ppuX*(beginLifesDraw+lifes), (float) (ppuY*12.5));
		
		spriteBatch.end();
		
		//Mort de pacman & bouger les �l�ments
		if(AudioFactory.getInstance().isPlaying("intro") || world.getPacman().isDead()) {
			if(world.getPacman().isDead())
				world.getPacman().incrementDeltaDead(delta);
			
			if(world.getPacman().getDeltaDead() > Settings.PACMANDEATHDURATION) {
				world.getPacman().setDead(false);
				world.getPacman().resetDealtaDead();
			}
			
		} else {
			moveGameElements(delta);
		}
		
	}
	
	public void moveGameElements(float delta) {
		if(world.hasPacmanEatenPacGumRecently()) {
			updateGhosts(delta);
		}
		
		this.world.movePacmanAndGhosts();
		
		for(Ghost ghost : this.world.getGhostsList()) {
			if(this.world.getPacman().eatGhost(ghost)) {
				ghost.setStateDead();
				this.displayPointAboveGhost.add(new PointsGhostEat(Settings.GHOSTVALUE[world.getNbGhostEatenSinceSuperPacGumEaten()], new Vector2(ghost.getPosition())));
				world.updateScore(Settings.GHOSTVALUE[world.getNbGhostEatenSinceSuperPacGumEaten()]);
				world.incrementNbGhostEatenSinceSuperPacGumEaten();
				AudioFactory.getInstance().playMusic("eatGhost");
			}
			
			if(ghost.justRespawned()) {
				ghost.incrementDeltaDeath(delta);
				if(ghost.getDeltaDeath() > Settings.SEUILDEATHGHOST) {
					ghost.setJustRespawned(false);
				}
			}
			
			if(ghost.eatPacman()) {
				world.replaceElement();
				world.getPacman().setDead(true);
				world.decrementeVies();
				AudioFactory.getInstance().playMusic("death");
			}
		}
	}
	
	public void setPpuX(float ppuX) {
		this.ppuX = ppuX;
	}
	
	public void setPpuY(float ppuY) {
		this.ppuY = ppuY;
	}
	
	/**
	 * Utilis�e par la classe GameScreen pour le viewport
	 * @return
	 */
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
	
	public ShapeRenderer getShape() {
		return shape;
	}
	
	private void updateGhosts(float delta) {
		world.setDeltaSinceSuperPacGumEaten(world.getDeltaSinceSuperPacGumEaten() + delta);
		if(world.getDeltaSinceSuperPacGumEaten() >= Settings.DURATIONBEFOREBLINKING && world.getDeltaSinceSuperPacGumEaten() < Settings.DURATIONSUPERPACGUM) {
			deltaBlink += delta;
			for(Ghost ghost : world.getGhostsList()) {
				if((ghost.getState() == Settings.ESCAPING || ghost.getState() == Settings.BLINKING) && deltaBlink > Settings.DURATIONBLINK) {
					ghost.switchEscapingBlinking();
				}
			}
			
			if(deltaBlink > Settings.DURATIONBLINK)
				deltaBlink = 0;
			
		} else if(world.getDeltaSinceSuperPacGumEaten() > Settings.DURATIONSUPERPACGUM) { //apr�s 10 secondes, les fantomes repassent � l'�tat normal
			for(Ghost ghost : world.getGhostsList()) {
				if(ghost.getState() == Settings.ESCAPING || ghost.getState() == Settings.BLINKING) {
					ghost.setStateToNormal();
				}
			}
			world.setDeltaSinceSuperPacGumEaten(0);
			deltaBlink = 0;
			world.setPacmanhasEatenSuperPacGumRecently(false);
		}
	}
}
