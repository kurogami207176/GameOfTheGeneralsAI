package com.alaindroid.game.gog;

public interface AttackRule {
	/**
	 * 
	 * @return Return winning piece, null means draw
	 */
	public GOGPiece getWinner(GOGPiece attacker, GOGPiece defender);
}
