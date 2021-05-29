package com.rmed.game.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.rmed.game.cons.GameShapesEnum;
import com.rmed.game.cons.GameStatusEnum;

@Component
public class GameHelper {

	private Random ran = new Random();

	public GameShapesEnum getRandomShape() {	
		return getAllowedGameShapes().get(ran.nextInt(getAllowedGameShapes().size()));
	}

	public List<GameShapesEnum> getAllowedGameShapes() {
		return Arrays.asList(GameShapesEnum.values());
	}

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
	
	public Optional<GameShapesEnum> getGameShapeForString(String name) {
		return getAllowedGameShapes().stream().filter(s -> s.name().equalsIgnoreCase(name)).findAny();
	}
}
