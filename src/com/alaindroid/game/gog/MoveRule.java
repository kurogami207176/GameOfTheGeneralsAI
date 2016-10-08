package com.alaindroid.game.gog;

import java.util.ArrayList;
import java.util.List;

import com.alaindroid.game.gog.model.Vector;

public abstract class MoveRule {
	public boolean valid(Move move, Vector min, Vector max) {
		List<Vector> vectors = getPossibleMoves(move.getXFrom(), move.getYFrom(), min.getX(), min.getY(), max.getX(),
				max.getY());
		for (Vector vector : vectors) {
			if (vector.getX() == move.getXTo() && vector.getY() == move.getYTo()) {
				return true;
			}
		}
		return false;
	}

	public List<Vector> getPossibleMoves(int x, int y, int xMin, int yMin, int xMax, int yMax) {
		List<Vector> moveList = new ArrayList<Vector>();
		List<Vector> vectorList = getMoveVectors(x, y);
		System.out.println("getPossibleMoves");
		for (Vector vector : vectorList) {
			int xPoss = x + vector.getX();
			int yPoss = y + vector.getY();
			if (xPoss >= xMin && xPoss <= xMax && yPoss >= yMin && yPoss <= yMax) {
				moveList.add(new Vector(xPoss, yPoss));
				System.out.println(xPoss + "," + yPoss);
			}
		}
		return moveList;
	}

	public abstract List<Vector> getMoveVectors(int x, int y);

}
