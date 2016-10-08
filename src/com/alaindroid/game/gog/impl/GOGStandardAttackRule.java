package com.alaindroid.game.gog.impl;

import static com.alaindroid.game.gog.Type.SPY;

import com.alaindroid.game.gog.AttackRule;
import com.alaindroid.game.gog.GOGPiece;
import com.alaindroid.game.gog.Type;

public class GOGStandardAttackRule implements AttackRule {
	private static GOGStandardAttackRule instance = new GOGStandardAttackRule();

	public static GOGStandardAttackRule getInstance() {
		return instance;
	}

	@Override
	public GOGPiece getWinner(GOGPiece attacker, GOGPiece defender) {
		if (attacker.getType() == defender.getType()) {
			// both are eliminated if same unit
			if (attacker.getType() != Type.FLAG) {
				return null;
			} else { // flag will win if flag is defender
				return attacker;
			}
		}
		// flag always loses
		if (defender.getType() == Type.FLAG) {
			return attacker;
		}
		if (attacker.getType() == SPY) {
			if (defender.getType().getOrder() >= Type.SGT.getOrder()) {
				return attacker;
			} else {
				return defender;
			}
		}
		if (defender.getType() == SPY) {
			if (attacker.getType().getOrder() >= Type.SGT.getOrder()) {
				return defender;
			} else {
				return attacker;
			}
		}
		if (attacker.getType().getOrder() > defender.getType().getOrder()) {
			return attacker;
		} else {
			return defender;
		}
	}

}
