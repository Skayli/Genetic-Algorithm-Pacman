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
import com.mygdx.model.PointsGhostEat;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.audio.AudioFactory;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.blocks.Block;
import com.mygdx.model.elements.blocks.Dark;
import com.mygdx.model.elements.blocks.Intersection;
import com.mygdx.model.elements.blocks.PacGum;
import com.mygdx.model.elements.blocks.SuperPacGum;
import com.mygdx.model.elements.moving.ghosts.Blinky;
import com.mygdx.model.elements.moving.ghosts.Clyde;
import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.elements.moving.ghosts.GhostState;
import com.mygdx.model.elements.moving.ghosts.Inky;
import com.mygdx.model.elements.moving.ghosts.Pinky;
import com.mygdx.model.elements.moving.pacman.Pacman;

public class WorldRenderer {

	private World world;
	private SpriteBatch spriteBatch;
	private float ppuX, ppuY;
	private ShapeRenderer shape;
	private float deltaBlink;
	private float deltaRender;
	
	public WorldRenderer(World world) {
		this.world = world;
		
		this.spriteBatch = new SpriteBatch();
		shape = new ShapeRenderer();
		
		deltaRender = 0;
		deltaBlink = 0;
	}
	
	public void render(float delta) {
		
		deltaRender = delta;
		
		if(world.getPacman().isDead()) {
			deltaRender = 0;
			TextureFactory.getInstance().getTexturable(Pacman.class).resetDelta();
			for(Ghost ghost : world.getGhostsList())
				TextureFactory.getInstance().getTexturable(ghost.getClass()).resetDelta();
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
			this.spriteBatch.begin();
			
			this.spriteBatch.draw(
					TextureFactory.getInstance().getTexture(element.getClass()),
					element.getPosition().x * ppuX,
					element.getPosition().y * ppuY,
					element.getWidth() * ppuX,
					element.getHeight() * ppuY
			);
		
			this.spriteBatch.end();
		}
						
		//Mort de pacman & bouger les éléments
		if(world.getPacman().isDead()) {
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
	 * Utilisée par la classe GameScreen pour le viewport
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
				if((ghost.getState() == GhostState.ESCAPING || ghost.getState() == GhostState.BLINKING) && deltaBlink > Settings.DURATIONBLINK) {
					ghost.switchEscapingBlinking();
				}
			}
			
			if(deltaBlink > Settings.DURATIONBLINK)
				deltaBlink = 0;
			
		} else if(world.getDeltaSinceSuperPacGumEaten() > Settings.DURATIONSUPERPACGUM) { //après 10 secondes, les fantomes repassent à l'état normal
			for(Ghost ghost : world.getGhostsList()) {
				if(ghost.getState() == GhostState.ESCAPING || ghost.getState() == GhostState.BLINKING) {
					ghost.setStateToAlive();
				}
			}
			world.setDeltaSinceSuperPacGumEaten(0);
			deltaBlink = 0;
			world.setPacmanhasEatenSuperPacGumRecently(false);
		}
	}
}
