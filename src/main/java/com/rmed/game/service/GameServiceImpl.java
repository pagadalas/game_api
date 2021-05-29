package com.rmed.game.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rmed.game.cons.GameShapesEnum;
import com.rmed.game.cons.GameStatusEnum;
import com.rmed.game.entity.GameRound;
import com.rmed.game.entity.Player;
import com.rmed.game.helper.GameHelper;
import com.rmed.game.repo.PlayerRepository;
import com.rmed.game.vo.GameRequest;
import com.rmed.game.vo.GameRoundResponse;
import com.rmed.game.vo.PlayerResponse;
import com.rmed.game.vo.PlayerStatResponse;

@Service
public class GameServiceImpl implements GameService {

	@Autowired 
	private PlayerRepository playerRepo;

	@Autowired
	private GameHelper helper;


	@Override
	public GameRoundResponse play(GameRequest gameRequest) {
		validateGameRequest(gameRequest);
		Player player = playerRepo.findByName(gameRequest.getPlayerName().toUpperCase());
		GameRoundResponse response = null;
		if(player == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid input, player not found");			
		}
		else {
			GameShapesEnum applicationShape =  helper.getRandomShape();
			GameStatusEnum status = helper.determineWinner(helper.getGameShapeForString(gameRequest.getPlayerSymbol()).get(), applicationShape);
			player.getGameRounds().add(buildGameRound(gameRequest.getPlayerSymbol(), applicationShape.name(), status, player));
			playerRepo.save(player);
			response = buildGameResponse(gameRequest.getPlayerSymbol(), applicationShape.name(), status.name(), gameRequest.getPlayerName() );
		}
		return response; 
	}

	private Boolean validateGameRequest(GameRequest req) {
		if(req == null || req.getPlayerName() == null || req.getPlayerSymbol() == null ||
				!helper.getGameShapeForString(req.getPlayerSymbol()).isPresent()) {			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input");					
		} 		
		return true;
	}

	@Override
	public List<PlayerResponse> getAllPlayers() {
		List<Player> players = playerRepo.findAll();
		return players.stream().map(s -> new PlayerResponse(s.getName(), s.getGameRounds().size())).collect(Collectors.toList());
	}

	@Override
	public List<PlayerStatResponse> getAllPlayersGameStats() {
		List<Player> players = playerRepo.findAll();
		return players.stream().map(s -> convertPlayerStats(s)).collect(Collectors.toList());
	}

	
	@Override
	public PlayerStatResponse getPlayerGameStats(String player) {
		if(player == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input");			
		}
		Player playerDetail = playerRepo.findByName(player.toUpperCase());
		if(playerDetail == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid input, player not found");
		} else {
			return convertPlayerStats(playerDetail);
		}
	}

	
	private PlayerStatResponse convertPlayerStats(Player s) {
		Map<String, Long> stats = s.getGameRounds().stream().collect(Collectors.groupingBy(GameRound::getStatus, Collectors.counting()));
		return new PlayerStatResponse(s.getName(), s.getGameRounds().size(), stats.getOrDefault(GameStatusEnum.WIN.name(), 0L)
				, stats.getOrDefault(GameStatusEnum.LOST.name(), 0L)
				, stats.getOrDefault(GameStatusEnum.TIE.name(), 0L));
	}

	private GameRound buildGameRound(String playerShape, String applicationShape, GameStatusEnum status, Player player ) {
		GameRound round  = new GameRound();
		round.setAppShape(applicationShape);
		round.setPlayDate(new Date());
		round.setPlayer(player);
		round.setPlayerShape(playerShape.toUpperCase());
		round.setStatus(status.name());
		return round;
	}


	private GameRoundResponse buildGameResponse(String playerShape, String appShape, String status, String playerName) {
		GameRoundResponse resp = new GameRoundResponse();
		resp.setName(playerName);
		resp.setGameStatus(status);
		resp.setApplicationShape(appShape);
		resp.setPlayerShape(playerShape);
		return resp;
	}

}
