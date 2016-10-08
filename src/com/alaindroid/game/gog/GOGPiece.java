package com.alaindroid.game.gog;

public class GOGPiece {

	public GOGPiece(String playerId, Type type) {
		this.type = type;
		this.playerid = playerId;
	}

	int id;
	String playerid;
	Type type;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return type.name();
	}

	public Type getTypeSpecial() {
		return type;
	}

	public Type getType(String playerId) {
		if (this.playerid.equalsIgnoreCase(playerId)) {
			return type;
		} else {
			return Type.INVI;
		}
	}

	public String getPlayerId() {
		return playerid;
	}

}