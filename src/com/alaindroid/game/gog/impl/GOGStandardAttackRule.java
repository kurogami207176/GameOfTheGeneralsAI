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
		if (attacker.getTypeSpecial() == defender.getTypeSpecial()) {
			// both are eliminated if same unit
			if (attacker.getTypeSpecial() != Type.FLAG) {
				return null;
			} else { // flag will win if flag is defender
				return attacker;
			}
		}
		// flag always loses
		if (defender.getTypeSpecial() == Type.FLAG) {
			return attacker;
		}
		if (attacker.getTypeSpecial() == SPY) {
			if (defender.getTypeSpecial().getOrder() >= Type.SGT.getOrder()) {
				return attacker;
			} else {
				return defender;
			}
		}
		if (defender.getTypeSpecial() == SPY) {
			if (attacker.getTypeSpecial().getOrder() >= Type.SGT.getOrder()) {
				return defender;
			} else {
				return attacker;
			}
		}
		if (attacker.getTypeSpecial().getOrder() > defender.getTypeSpecial().getOrder()) {
			return attacker;
		} else {
			return defender;
		}
	}

}
