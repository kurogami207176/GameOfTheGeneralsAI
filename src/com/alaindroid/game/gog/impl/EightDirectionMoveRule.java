package com.alaindroid.game.gog.impl;

import java.util.Arrays;
import java.util.List;

import com.alaindroid.game.gog.MoveRule;
import com.alaindroid.game.gog.constants.Directions;
import com.alaindroid.game.gog.model.Vector;

public class EightDirectionMoveRule extends MoveRule {
	private static EightDirectionMoveRule instance = new EightDirectionMoveRule();

	public static EightDirectionMoveRule getInstance() {
		return instance;
	}

	private static final List<Vector> MOVES = Arrays.asList(Directions.N.getVector(), Directions.E.getVector(),
			Directions.W.getVector(), Directions.S.getVector());

	@Override
	public List<Vector> getMoveVectors(int x, int y) {
		return MOVES;
	}

}
