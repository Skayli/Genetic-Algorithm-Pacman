package com.mygdx.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.view.WorldRenderer;

public class PointsGhostEat {

	private BitmapFont font;
	
	private int points;
	private Vector2 position;
	
	private float deltaDisplay;
	
	public PointsGhostEat(int points, Vector2 position) {
		this.points = points;
		this.position = position;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}
	
	public void displayPointsEarned(SpriteBatch batch, float ppuX, float ppuY, float delta) {
		deltaDisplay += delta;
		if(deltaDisplay < Settings.DISPLAYPOINTSABOVEGHOST) {
			batch.begin();
			font.draw(batch, String.valueOf(points), position.x * ppuX, position.y * ppuY + ppuY);
			batch.end();
		} 
	}
	
	public boolean shouldBeRemoved() {
		return deltaDisplay > Settings.DISPLAYPOINTSABOVEGHOST;
	}
}

