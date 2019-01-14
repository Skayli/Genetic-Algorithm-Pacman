package com.mygdx.view;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.World;
import com.mygdx.model.elements.PacGum;
import com.mygdx.model.elements.SuperPacGum;
import com.mygdx.model.elements.blocks.Barriere;
import com.mygdx.model.elements.blocks.Block;
import com.mygdx.model.elements.blocks.Dark;
import com.mygdx.model.elements.blocks.GhostHouse;
import com.mygdx.model.elements.moving.ghosts.Blinky;
import com.mygdx.model.elements.moving.ghosts.Clyde;
import com.mygdx.model.elements.moving.ghosts.Inky;
import com.mygdx.model.elements.moving.ghosts.Pinky;
import com.mygdx.model.elements.moving.pacman.Pacman;

public class TextureFactory {
	static private TextureFactory instance = null;
	private HashMap<Class<?>, iTexturable> textures;
	private static World world;
	
	private final int SEUIL_ACTU_SUPER_PACGUM = 500;
	private final int SEUIL_ACTU_GHOSTS = 250;
	private final int SEUIL_ACTU_PACMAN = 500;
	
	private TextureFactory() {
		try {
			textures = new HashMap<Class<?>, iTexturable>();
			textures.put(Pacman.class, new TexturePacman(world.getPacman(), SEUIL_ACTU_PACMAN));
			
			textures.put(Blinky.class, new TextureBlinky(world.getBlinky(), SEUIL_ACTU_GHOSTS));
			textures.put(Inky.class, new TextureInky(world.getInky(), SEUIL_ACTU_GHOSTS));
			textures.put(Pinky.class, new TexturePinky(world.getPinky(), SEUIL_ACTU_GHOSTS));
			textures.put(Clyde.class, new TextureClyde(world.getClyde(), SEUIL_ACTU_GHOSTS));
			
			textures.put(Dark.class, new TextureUnique(new Texture(Gdx.files.internal("images/dark.png"))));
			textures.put(GhostHouse.class, new TextureUnique(new Texture(Gdx.files.internal("images/dark.png"))));
			textures.put(Block.class, new TextureUnique(new Texture(Gdx.files.internal("images/bloc.png"))));
			textures.put(Barriere.class, new TextureUnique(new Texture(Gdx.files.internal("images/barriere.png"))));
			textures.put(PacGum.class, new TextureUnique(new Texture(Gdx.files.internal("images/pellet.png"))));
						
			textures.put(SuperPacGum.class, new TextureSuperPacGum(world.getSP_BottomLeft(), SEUIL_ACTU_SUPER_PACGUM));
			textures.put(SuperPacGum.class, new TextureSuperPacGum(world.getSP_BottomRight(), SEUIL_ACTU_SUPER_PACGUM));
			textures.put(SuperPacGum.class, new TextureSuperPacGum(world.getSP_TopLeft(), SEUIL_ACTU_SUPER_PACGUM));
			textures.put(SuperPacGum.class, new TextureSuperPacGum(world.getSP_TopRight(), SEUIL_ACTU_SUPER_PACGUM));
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
