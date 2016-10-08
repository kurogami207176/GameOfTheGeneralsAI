package com.alaindroid.game.gog.impl;

import java.util.Arrays;
import java.util.List;

import com.alaindroid.game.gog.MoveRule;
import com.alaindroid.game.gog.constants.Directions;
import com.alaindroid.game.gog.model.Vector;

public class HexDirectionMoveRule extends MoveRule {
	private static HexDirectionMoveRule instance = new HexDirectionMoveRule();

	public static HexDirectionMoveRule getInstance() {
		return instance;
	}

	private static final List<Vector> MOVES0 = Arrays.asList(Directions.N.getVector(), Directions.E.getVector(),
			Directions.W.getVector(), Directions.S.getVector(), Directions.NW.getVector(), Directions.SW.getVector());

	private static final List<Vector> MOVES1 = Arrays.asList(Directions.N.getVector(), Directions.E.getVector(),
			Directions.W.getVector(), Directions.S.getVector(), Directions.NE.getVector(), Directions.SE.getVector());

	@Override
	public List<Vector> getMoveVectors(int x, int y) {
		if (y % 2 == 0) {
			return MOVES0;
		}
		return MOVES1;
	}

}
