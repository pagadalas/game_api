package com.rmed.game.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.rmed.game.cons.GameShapesEnum;
import com.rmed.game.cons.GameStatusEnum;
import com.rmed.game.entity.Player;
import com.rmed.game.helper.GameHelper;
import com.rmed.game.repo.PlayerRepository;
import com.rmed.game.vo.GameRequest;
import com.rmed.game.vo.GameRoundResponse;
import com.rmed.game.vo.PlayerResponse;
import com.rmed.game.vo.PlayerStatResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author SPagadala
 * @since May 29, 2021
 */
@SpringBootTest
@Transactional
public class GameServiceImplTest {

    @Mock
    private GameHelper helperMock;
    @Mock
    private PlayerRepository playerRepoMock;

    @InjectMocks
    private GameServiceImpl target;

    @Test
    public void when_player_shape_Rock_application_shape_scissors_player_wins_play_test() {
        MockitoAnnotations.initMocks(this);
        GameRequest request = new GameRequest();
        request.setPlayerName("Tim");
        request.setPlayerSymbol("rock");
        Player player = new Player();
        player.setName("Tim");
        player.setGameRounds(new HashSet<>());
        when(helperMock.getRandomShape()).thenReturn(GameShapesEnum.SCISSORS);
        when(helperMock.determineWinner(GameShapesEnum.ROCK,GameShapesEnum.SCISSORS)).thenReturn(GameStatusEnum.WIN);
        when(helperMock.getGameShapeForString(Mockito.anyString())).thenReturn(Optional.of(GameShapesEnum.ROCK));
        when(playerRepoMock.findByName(Mockito.anyString())).thenReturn(player);
        GameRoundResponse response = target.play(request);
        assertNotNull(response);
        assertEquals(response.getName(), "Tim");
        assertEquals(response.getGameStatus(), "WIN");
    }

    @Test
    public void when_player_name_doesnt_exist_then_returns_exception_test() {
        when(playerRepoMock.findByName(Mockito.anyString())).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> {
            target.getPlayerGameStats("Rocky");
        });
        assertThrows(ResponseStatusException.class, () -> {
            target.getPlayerGameStats(null);
        });
    }

    @Test
    public void getAllPlayers_test() {
        List<Player> players = new ArrayList<>();
        Player p1 = new Player();
        p1.setName("Tim");
        p1.setGameRounds(new HashSet<>());
        Player p2 = new Player();
        p2.setGameRounds(new HashSet<>());
        players.add(p1);
        players.add(p2);
        when(playerRepoMock.findAll()).thenReturn(players);
        List<PlayerResponse> responses = target.getAllPlayers();
        assertNotNull(responses);
        assertEquals(responses.get(0).getPlayerName(),"Tim");
    }

    @Test
    public void getAllPlayersGameStats_test() {
        List<Player> players = new ArrayList<>();
        Player p1 = new Player();
        p1.setName("Tim");
        p1.setGameRounds(new HashSet<>());
        Player p2 = new Player();
        p2.setGameRounds(new HashSet<>());
        players.add(p1);
        players.add(p2);
        when(playerRepoMock.findAll()).thenReturn(players);
        List<PlayerStatResponse> responses = target.getAllPlayersGameStats();
        assertNotNull(responses);
        assertEquals(responses.get(0).getPlayerName(),"Tim");
        assertEquals(responses.get(0).getTotalGames(),0);
    }

}
