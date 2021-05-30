package com.rmed.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmed.game.service.GameService;
import com.rmed.game.vo.GameRequest;
import com.rmed.game.vo.GameRoundResponse;
import com.rmed.game.vo.PlayerResponse;
import com.rmed.game.vo.PlayerStatResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Game Api, compete with the computer ")
@RequestMapping(path="/game/v1/")
@Validated
public class GameController {

	private GameService gameService;
	
	@Autowired
	GameController(GameService gameService) {
		this.gameService = gameService;
	}

    /**
     * This method is called to play with Game Application.
     * Player name and player symbol is passed as part of the request,
     * which are then validated, system symbol is generated and winner is determined
     * The results are persisted into the db table.
     * @param gameRequest
     * @return
     */
    @PostMapping(path= "/play")
    @ApiOperation(value = "Return play status", response = GameRoundResponse.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully played a game with Game App"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 404, message = "Player is Not Found")
        })
    public GameRoundResponse play(@RequestBody GameRequest gameRequest) {    	   		
    	return gameService.play(gameRequest);
    }

    /**
     * This method fetches all the players who are registered with the application
     * @return
     */
    @GetMapping("/players")
    @ApiOperation(value = "Return all players registered with applicaion", response = List.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 404, message = "Requested Resource Not Found")
        })
    public  List<PlayerResponse> getPlayers() {
    	return gameService.getAllPlayers();
    }

    /**
     * This method fetches all the statistics of all the players
     * This include number of games played,number of wins, loses, ties/draws etc
     * This als shows the % of each status
     * @return
     */
    @GetMapping("/players/stats")
    @ApiOperation(value = "Return game stats for all players", response = List.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 404, message = "Requested Resource Not Found")
        })
    public  List<PlayerStatResponse> getPlayerStats() {
    	return gameService.getAllPlayersGameStats();
    }

    /**
     * This method fetches all the statistics of all the specific player passed in the input
     * This include number of games played,number of wins, loses, ties/draws etc
     * This als shows the % of each status
     * @param player
     * @return
     */
    @GetMapping("/players/{player}/stats")
    @ApiOperation(value = "Return game stats for a player", response = PlayerStatResponse.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 404, message = "Requested Resource Not Found")
        })
    public  PlayerStatResponse getPlayerDetails(@PathVariable("player") String player) {
    	return gameService.getPlayerGameStats(player);
    }

}
