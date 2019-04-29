package com.mygdx.model.elements.moving;

public class Vect2D {
	
	public double x;
	public double y;
	
	public Vect2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vect2D(Vect2D vector) {
		this.x = vector.x;
		this.y = vector.y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
	public String toString() {
		return "["+this.x+", "+this.y+"]";
	}

	public boolean isEqualTo(Vect2D position) {
		return this.x == position.x && this.y == position.y;
	}
}
