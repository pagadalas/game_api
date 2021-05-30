package com.rmed.game.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.rmed.game.cons.GameShapesEnum;
import com.rmed.game.cons.GameStatusEnum;

/**
 * This class provides the prime functionality that is needed for game application
 * Get Random System symbol from the avialable options
 * Determine the winner
 * convert to symbol from user input
 */
@Component
public class GameHelper {

	private Random ran = new Random();

	public GameShapesEnum getRandomShape() {	
		return getAllowedGameShapes().get(ran.nextInt(getAllowedGameShapes().size()));
	}

	/**
	 * This method returns list of valid GameShapes for the game application
	 * @return
	 */
	public List<GameShapesEnum> getAllowedGameShapes() {
		return Arrays.asList(GameShapesEnum.values());
	}

	/**
	 * This method determines the winner between the two options passed as parameter
	 * If the options are same it is a TIE
	 * if the playerShape is ROCK and system symbol is SCISSORS ==> player wins
	 * if the playerShape is PAPER and system symbol is ROCK ==> player wins
	 * if the playerShape is SCISSORS and system symbol is PAPER ==> player wins
	 * if not above System/Game application Wins
	 * @param playerShape
	 * @param appShape
	 * @return
	 */
	public GameStatusEnum determineWinner(GameShapesEnum playerShape, GameShapesEnum appShape) {		
		if (playerShape == appShape)
			return GameStatusEnum.TIE;
		else if(playerShape == GameShapesEnum.ROCK && appShape == GameShapesEnum.SCISSORS ||
				playerShape == GameShapesEnum.PAPER && appShape == GameShapesEnum.ROCK ||
				playerShape == GameShapesEnum.SCISSORS && appShape == GameShapesEnum.PAPER)
		{
			return GameStatusEnum.WIN;
		}
		return GameStatusEnum.LOST;
	}

	/**
	 * This will return an Enum for the string passed
	 * @param name
	 * @return
	 */
	public Optional<GameShapesEnum> getGameShapeForString(String name) {
		return getAllowedGameShapes().stream().filter(s -> s.name().equalsIgnoreCase(name)).findAny();
	}
}
