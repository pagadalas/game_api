package com.rmed.game.vo;

public class PlayerResponse {
	
	private String playerName;
	
	private int totalPlays;
	public PlayerResponse(String name, int games) {
		this.playerName = name;
		this.totalPlays = games;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getTotalPlays() {
		return totalPlays;
	}
	public void setTotalPlays(int totalPlays) {
		this.totalPlays = totalPlays;
	}

}
