package com.mygdx.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.model.World;
import com.mygdx.model.elements.GameElement;
import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.SuperPacGum;
import com.mygdx.model.elements.moving.ghosts.Blinky;
import com.mygdx.model.elements.moving.ghosts.Clyde;
import com.mygdx.model.elements.moving.ghosts.Ghost;
import com.mygdx.model.elements.moving.ghosts.Inky;
import com.mygdx.model.elements.moving.ghosts.Pinky;
import com.mygdx.model.elements.moving.pacman.Pacman;
import com.mygdx.view.textures.TextureBlinky;
import com.mygdx.view.textures.TextureClyde;
import com.mygdx.view.textures.TextureFactory;
import com.mygdx.view.textures.TextureInky;
import com.mygdx.view.textures.TexturePacman;
import com.mygdx.view.textures.TexturePinky;
import com.mygdx.view.textures.TextureSuperPacGum;

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

//			//Test couleur autour pacman
//			if(element.getClass() == Pacman.class) {
//				shape.setColor(Color.BLUE);
//			    shape.begin(ShapeType.Filled);
//			    shape.circle((float)world.getPacman().position.x*ppuX, (float)world.getPacman().position.y*ppuY, 64);
//			    shape.end();
//			}
			    
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
