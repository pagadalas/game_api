package com.rmed.game.helper;

import com.rmed.game.cons.GameShapesEnum;
import com.rmed.game.cons.GameStatusEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author SPagadala
 * @since May 29, 2021
 */
public class GameHelperTest {

    private GameHelper target = new GameHelper();

    @Test
    public void getRandomShape_test() {
        GameShapesEnum shape = target.getRandomShape();
        assertNotNull(shape);
    }

    @Test
    public void getAllowedGameShapes_test() {
        assertNotNull(target.getAllowedGameShapes());
        assertEquals(target.getAllowedGameShapes().size(), 3);
    }

    @Test
    public void determineWinner() {
      assertEquals(target.determineWinner(GameShapesEnum.PAPER, GameShapesEnum.PAPER), GameStatusEnum.TIE);
      assertEquals(target.determineWinner(GameShapesEnum.ROCK, GameShapesEnum.SCISSORS), GameStatusEnum.WIN);
      assertEquals(target.determineWinner(GameShapesEnum.PAPER, GameShapesEnum.SCISSORS), GameStatusEnum.LOST);
      assertEquals(target.determineWinner(GameShapesEnum.SCISSORS, GameShapesEnum.PAPER), GameStatusEnum.WIN);
    }

    @Test
    public void getGameShapeForString_test() {
        assertEquals(target.getGameShapeForString("rocket").isPresent(), false);
        assertEquals(target.getGameShapeForString("rock").isPresent(), true);
    }
}
