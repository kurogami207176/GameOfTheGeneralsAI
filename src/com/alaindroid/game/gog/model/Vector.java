package com.alaindroid.game.gog.model;

public class Vector {
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	int x;
	int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Vector) {
			Vector v = (Vector) o;
			return v.x == x && v.y == y;
		}
		return false;
	}
}
