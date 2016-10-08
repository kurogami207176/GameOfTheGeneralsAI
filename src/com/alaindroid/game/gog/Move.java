package com.alaindroid.game.gog;

import com.alaindroid.game.gog.model.Vector;

public class Move {
	public Move(int xFrom, int yFrom, int xTo, int yTo) {
		this.from = new Vector(xFrom, yFrom);
		this.to = new Vector(xTo, yTo);
	}

	Vector from;
	Vector to;

	public int getXFrom() {
		return from.getX();
	}

	public int getYFrom() {
		return from.getY();
	}

	public int getXTo() {
		return to.getX();
	}

	public int getYTo() {
		return to.getY();
	}

	public Vector getFrom() {
		return from;
	}

	public Vector getTo() {
		return to;
	}

}
