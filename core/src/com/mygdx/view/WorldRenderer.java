package com.mygdx.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.SuperPacGum;
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
	private float deltaRender;
	
	
	public WorldRenderer(World world) {
		this.world = world;
		
		this.spriteBatch = new SpriteBatch();
		shape = new ShapeRenderer();
		
		deltaRender = 0;
	}
	
	public void render(float delta) {
		
		deltaRender = delta;
		
		if(world.getPacman().isDead()) {
			deltaRender = 0;
			
			world.getPacman().setDead(false);
			TextureFactory.getInstance().getTexturable(Pacman.class).resetDelta();
			
			for(Ghost ghost : world.getGhostsList()) {
				TextureFactory.getInstance().getTexturable(ghost.getClass()).resetDelta();
			}
			
			world.init();
		}
		
		if(true) {
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
						(float) element.position.x * ppuX,
						(float) element.position.y * ppuY,
						1 * ppuX,
						1 * ppuY
				);
			
			
				this.spriteBatch.end();
			}
			
		}
		
		
		for(int i = 0 ; i < 1; i++) {
			
			world.movePacmanAndGhosts();
			
			for(Ghost ghost : world.getGhostsList()) {
				world.processCollisionPacmanGhost(ghost);
			}
			
			if(world.hasSuperPacgumBeEatenRecently()) {
				world.updateGhostsStates(delta);
			}
			
			for(PacGum p : world.getPacGumList()) {
				if(world.getPacman().hasReachCenter(p)) {
					world.processPacgumEaten(p);
					break;
				}
			}
			
			if(world.getPacman().isDead())
				break;
			
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
	
}
