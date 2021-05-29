package com.rmed.game.vo;

import com.rmed.game.entity.GameRound;

public class GameRoundResponse {

	private String name;
	private String playerShape;
	private String applicationShape;
	private String gameStatus;
	
	public GameRoundResponse() {}
	
	public GameRoundResponse(GameRound round ) {
		this.name = round.getPlayer().getName();
		this.playerShape = round.getPlayerShape();
		this.applicationShape = round.getAppShape();
		this.gameStatus = round.getStatus();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayerShape() {
		return playerShape;
	}

	public void setPlayerShape(String playerShape) {
		this.playerShape = playerShape;
	}

	public String getApplicationShape() {
		return applicationShape;
	}

	public void setApplicationShape(String applicationShape) {
		this.applicationShape = applicationShape;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	
}
