package com.rmed.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class GameController {

	private GameService gameService;
	
	@Autowired
	GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
    @PostMapping(path= "/play")
    @ApiOperation(value = "Return play status", response = GameRoundResponse.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully played a game with Game App"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                @ApiResponse(code = 404, message = "Player is Not Found")
        })
    public GameRoundResponse play(@RequestBody GameRequest gameRequest) {    	   		
    	gameService.validateGameRequest(gameRequest);    	
    	return gameService.play(gameRequest);
    }
    
    @GetMapping("/players")
    @ApiOperation(value = "Return all players registered with applicaion", response = List.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                @ApiResponse(code = 404, message = "Requested Resource Not Found")
        })
    public  List<PlayerResponse> getPlayers() {
    	return gameService.getPlayers();
    }
    
    @GetMapping("/players/stats")
    @ApiOperation(value = "Return game stats for all players", response = List.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                @ApiResponse(code = 404, message = "Requested Resource Not Found")
        })
    public  List<PlayerStatResponse> getPlayerStats() {
    	return gameService.getPlayesStats();
    }

    @GetMapping("/players/{player}/stats")
    @ApiOperation(value = "Return game stats for a player", response = PlayerStatResponse.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                @ApiResponse(code = 404, message = "Requested Resource Not Found")
        })
    public  PlayerStatResponse getPlayerDetails(@PathVariable("player") String player) {
    	return gameService.getPlayesGameDetails(player);
    }

}
