package com.rmed.game.vo;

public class PlayerStatResponse {
	private String playerName;
	private Integer totalGames;
	private Integer wins;
	private Integer losts;
	private Integer draws;
	private String winPercentage;
	private String lostPercentage;
	private String drawPercentage;
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Integer getTotalGames() {
		return totalGames;
	}
	public void setTotalGames(Integer totalGames) {
		this.totalGames = totalGames;
	}
	public Integer getWins() {
		return wins;
	}
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	public Integer getLosts() {
		return losts;
	}
	public void setLosts(Integer losts) {
		this.losts = losts;
	}
	public Integer getDraws() {
		return draws;
	}
	public void setDraws(Integer draws) {
		this.draws = draws;
	}
	
	public PlayerStatResponse(String name, int size, Long wins, Long losts, Long ties) {
		this.playerName = name;
		this.totalGames = size;
		this.draws = ties.intValue();
		this.losts = losts.intValue();
		this.wins = wins.intValue();
	}
	public String getWinPercentage() {
		if(this.totalGames > 0) {
			return this.wins*100/this.totalGames+"%";
		}
		return "0%";
	}
	public String getLostPercentage() {
		if(this.totalGames > 0) {
			return this.losts*100/this.totalGames+"%";
		}
		return "0%";
	}
	public String getDrawPercentage() {
		if(this.totalGames > 0) {
			return this.draws*100/this.totalGames+"%";
		}
		return "0%";

	}
	public void setWinPercentage(String winPercentage) {
		this.winPercentage = winPercentage;
	}
	public void setLostPercentage(String lostPercentage) {
		this.lostPercentage = lostPercentage;
	}
	public void setDrawPercentage(String drawPercentage) {
		this.drawPercentage = drawPercentage;
	}

}
