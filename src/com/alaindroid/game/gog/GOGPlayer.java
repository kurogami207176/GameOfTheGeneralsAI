package com.alaindroid.game.gog;

public abstract class GOGPlayer {
	public GOGPlayer(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	private String id;

	public abstract Move getMove(GOGBoard board);
}
