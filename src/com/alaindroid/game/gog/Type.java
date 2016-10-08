package com.alaindroid.game.gog;

public enum Type {
	INVI("Hidden", Integer.MIN_VALUE), FLAG("Flag", -1), SPY("Spy", 0), PRVT("Private", 1), SGT("Sergeant",
			2), LT2("2nd Lieutenant", 3), LT1("1st Lieutenant", 4), CPT("Captain", 5), MAJ("Major", 6), LTCOL(
					"Lieutenant Colonel", 7), COL("Colonel", 8), GEN1("Brigadier General", 9), GEN2("Major General",
							10), GEN3("Lieutenant General", 11), GEN4("General", 12), GEN5("General of the Army", 13);
	String rank;
	int order;

	private Type(String rank, int order) {
		this.rank = rank;
		this.order = order;
	}

	public String getRank() {
		return rank;
	}

	public int getOrder() {
		return order;
	}
}