package com.rmed.game.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.rmed.game.service.GameService;
import com.rmed.game.vo.GameRequest;
import com.rmed.game.vo.PlayerStatResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author SPagadala
 * @since May 29, 2021
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService mockService;

    @BeforeEach
    public void init() {
        PlayerStatResponse stat = new PlayerStatResponse();
        stat.setPlayerName("TimBrown");
        stat.setDraws(1);
        stat.setLosts(0);
        stat.setTotalGames(3);
        stat.setWins(2);
        when(mockService.getPlayerGameStats("TimBrown")).thenReturn(stat);
    }

    @WithMockUser("ADMIN")
    @Test
    public void when_successful_login_getPlayerStats_test() throws Exception {

        mockMvc.perform(get("/game/v1/players/TimBrown/stats"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerName", is("TimBrown")))
                .andExpect(jsonPath("$.wins", is(2)))
                .andExpect(jsonPath("$.winPercentage", is("66%")));
    }

    @Test
    public void when_nologin_then_401_test() throws Exception {
        mockMvc.perform(get("/game/v1/players/"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser("ADMIN")
    @Test
    public void when_successful_login_with_invalid_inputs_then_throws_BadRequest_test() throws Exception {
        GameRequest req = new GameRequest();
        when(mockService.play(req)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input"));
        mockMvc.perform(post("/game/v1/play"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
