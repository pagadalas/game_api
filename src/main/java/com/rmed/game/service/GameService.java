package com.rmed.game.service;

import java.util.List;

import com.rmed.game.vo.GameRequest;
import com.rmed.game.vo.GameRoundResponse;
import com.rmed.game.vo.PlayerResponse;
import com.rmed.game.vo.PlayerStatResponse;

public interface GameService {

	GameRoundResponse play(GameRequest game);
	
	Boolean validateGameRequest(GameRequest game);

	List<PlayerResponse> getPlayers();

	List<PlayerStatResponse> getPlayesStats();

	PlayerStatResponse getPlayesGameDetails(String player);
	
}