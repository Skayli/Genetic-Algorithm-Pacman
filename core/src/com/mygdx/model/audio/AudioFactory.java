package com.mygdx.model.audio;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioFactory {
	static private AudioFactory instance = null;
	private HashMap<String,Music> music;
	private boolean deltaMunch;
	
	private AudioFactory () {
		deltaMunch = false;
		music = new HashMap<String, Music>();
		music.put("intro", Gdx.audio.newMusic(Gdx.files.internal("music/intro.wav")));
		music.put("munch_a", Gdx.audio.newMusic(Gdx.files.internal("music/munch_a.wav")));
		music.put("munch_b", Gdx.audio.newMusic(Gdx.files.internal("music/munch_b.wav")));
		music.put("large_pellet", Gdx.audio.newMusic(Gdx.files.internal("music/large_pellet.wav")));
		music.put("death",  Gdx.audio.newMusic(Gdx.files.internal("music/death.wav")));
		music.put("eatGhost", Gdx.audio.newMusic(Gdx.files.internal("music/ghost_eat.wav")));
		music.put("siren", Gdx.audio.newMusic(Gdx.files.internal("music/siren.wav")));
	}
	
	public static AudioFactory getInstance() {
		if(instance == null)
			instance = new AudioFactory();
		
		return instance;
	}
	
	public void setVolume(String name, float volume) {
		music.get(name).setVolume(volume);
	}
	public void playMusic(String name) {
		music.get(name).play();
	}
	
	public boolean isPlaying(String name) {
		return music.get(name).isPlaying();
	}
	
	public void setLooping(String name, boolean loop) {
		music.get(name).setLooping(loop);
	}
	
	public void stopMusic(String name){
		music.get(name).stop();
	}
	
	public void playMunch() {
		if(deltaMunch) {
			music.get("munch_a").play();
			deltaMunch = false;
		} else {
			music.get("munch_b").play();
			deltaMunch = true;
		}
	}
}
