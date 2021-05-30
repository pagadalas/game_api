package com.rmed.game.service;

import java.util.List;

import com.rmed.game.vo.GameRequest;
import com.rmed.game.vo.GameRoundResponse;
import com.rmed.game.vo.PlayerResponse;
import com.rmed.game.vo.PlayerStatResponse;

/**
 * This interface provides the main operations for the Game Application
 */
public interface GameService {

	GameRoundResponse play(GameRequest game);

	List<PlayerResponse> getAllPlayers();

	List<PlayerStatResponse> getAllPlayersGameStats();

	PlayerStatResponse getPlayerGameStats(String playerName);
	
}
