package com.alaindroid.game.gog.constants;

import com.alaindroid.game.gog.model.Vector;

public enum Directions {
	N(0, 1), E(1, 0), W(-1, 0), S(0, -1), NE(1, 1), NW(-1, 1), SE(1, -1), SW(-1, -1);
	Vector vector;

	private Directions(int x, int y) {
		this.vector = new Vector(x, y);
	}

	public Vector getVector() {
		return vector;
	}
}