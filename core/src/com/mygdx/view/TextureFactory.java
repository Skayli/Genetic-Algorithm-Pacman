package com.mygdx.view;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.Settings;
import com.mygdx.model.World;
import com.mygdx.model.elements.blocks.Barriere;
import com.mygdx.model.elements.blocks.Block;
import com.mygdx.model.elements.blocks.Dark;
import com.mygdx.model.elements.blocks.GhostHouse;
import com.mygdx.model.elements.blocks.GhostIntersection;
import com.mygdx.model.elements.blocks.Intersection;
import com.mygdx.model.elements.blocks.PacGum;
import com.mygdx.model.elements.blocks.SuperPacGum;
import com.mygdx.model.elements.moving.Pacman;
import com.mygdx.model.elements.moving.ghosts.Blinky;
import com.mygdx.model.elements.moving.ghosts.Clyde;
import com.mygdx.model.elements.moving.ghosts.Inky;
import com.mygdx.model.elements.moving.ghosts.Pinky;

public class TextureFactory {
	static private TextureFactory instance = null;
	private HashMap<Class<?>, iTexturable> textures;
	private static World world;
	
	private TextureFactory() {
		try {
			textures = new HashMap<Class<?>, iTexturable>();
			textures.put(Pacman.class, new TexturePacman(world.getPacman(), Settings.SEUILPACMAN));
			
			textures.put(Blinky.class, new TextureBlinky(world.getBlinky(), Settings.SEUILGHOSTS));
			textures.put(Inky.class, new TextureInky(world.getInky(), Settings.SEUILGHOSTS));
			textures.put(Pinky.class, new TexturePinky(world.getPinky(), Settings.SEUILGHOSTS));
			textures.put(Clyde.class, new TextureClyde(world.getClyde(), Settings.SEUILGHOSTS));
			
			textures.put(Block.class, new TextureUnique(new Texture(Gdx.files.internal("images/bloc.png"))));
			textures.put(Barriere.class, new TextureUnique(new Texture(Gdx.files.internal("images/barriere.png"))));
			textures.put(PacGum.class, new TextureUnique(new Texture(Gdx.files.internal("images/pellet.png"))));
			if(Settings.DEBUGALGOPCC) {
				textures.put(Dark.class, new TextureUnique(new Texture(Gdx.files.internal("images/debug.png"))));
				textures.put(Intersection.class, new TextureUnique(new Texture(Gdx.files.internal("images/debug.png"))));
				textures.put(GhostHouse.class, new TextureUnique(new Texture(Gdx.files.internal("images/debug.png"))));
				textures.put(GhostIntersection.class, new TextureUnique(new Texture(Gdx.files.internal("images/debug.png"))));
				
			} else {
				textures.put(Dark.class, new TextureUnique(new Texture(Gdx.files.internal("images/dark.png"))));
				textures.put(Intersection.class, new TextureUnique(new Texture(Gdx.files.internal("images/dark.png"))));
				textures.put(GhostHouse.class, new TextureUnique(new Texture(Gdx.files.internal("images/dark.png"))));
				textures.put(GhostIntersection.class, new TextureUnique(new Texture(Gdx.files.internal("images/dark.png"))));
			}
			
			textures.put(SuperPacGum.class, new TextureSuperPacGum(world.getSP_BottomLeft(), Settings.SEUILSUPERPACGUM));
			textures.put(SuperPacGum.class, new TextureSuperPacGum(world.getSP_BottomRight(), Settings.SEUILSUPERPACGUM));
			textures.put(SuperPacGum.class, new TextureSuperPacGum(world.getSP_TopLeft(), Settings.SEUILSUPERPACGUM));
			textures.put(SuperPacGum.class, new TextureSuperPacGum(world.getSP_TopRight(), Settings.SEUILSUPERPACGUM));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public static void reset() {
		instance = null;
	}
	
	public static TextureFactory getInstance() {
		if(instance == null)
			instance = new TextureFactory();
		
		return instance;
	}
	
	public static void setWorld(World world) {
		TextureFactory.world = world;
	}
	
	public Texture getTexture(Class<?> c) {
		return textures.get(c).getTexture();
	}
	
	public iTexturable getTexturable(Class<?> c) {
		return textures.get(c);
	}
}
