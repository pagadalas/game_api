package com.rmed.game.vo;
/**
 * This class reporesents the player input to play with the game application
 * @author Spagadala
 *
 */
public class GameRequest {

	private String playerName;
	private String playerSymbol;
	public String getPlayerName() {
		return playerName;
	}
	public String getPlayerSymbol() {
		return playerSymbol;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public void setPlayerSymbol(String playerSymbol) {
		this.playerSymbol = playerSymbol;
	}

}
