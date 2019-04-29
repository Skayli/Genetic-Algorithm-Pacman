package com.mygdx.model.elements;

import com.mygdx.model.elements.moving.Vect2D;

public class HitBox {
		
	private double width;
	private double height;
	
	public HitBox(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	/////////////////////////////////////////////
	
}
