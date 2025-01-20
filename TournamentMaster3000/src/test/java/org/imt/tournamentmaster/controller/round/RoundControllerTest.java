/*
package org.imt.tournamentmaster.controller.round;

import org.imt.tournamentmaster.controller.match.RoundController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class RoundControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoundController roundController;

    @Test
    public void testGetRoundById() throws Exception {
        mockMvc.perform(get("/api/round/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.equipeA.id").value(1))
                .andExpect(jsonPath("$.equipeB.id").value(2))
                .andExpect(jsonPath("$.scoreA").value(21))
                .andExpect(jsonPath("$.scoreB").value(14))
                .andExpect(jsonPath("$.roundNumber").value(1));
    }

    @Test
    public void testGetNonExistingRoundById_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/round/42"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllRounds() throws Exception {
        mockMvc.perform(get("/api/round"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(6)); // Total 6 rounds in the fixture
    }

    @Test
    public void testCreateRound() throws Exception {
        // Clear the Hibernate session before performing the test
        sessionFactory.getCurrentSession().clear();

        String newRoundJson = """
        {
            "equipeA": { "id": 1 },
            "equipeB": { "id": 2 },
            "scoreA": 15,
            "scoreB": 12,
            "roundNumber": 7
        }
    """;

        mockMvc.perform(post("/api/round")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRoundJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.roundNumber").value(7));
    }




    @Test
    public void testUpdateRound() throws Exception {
        String updatedRoundJson = """
            {
                "id": 1,
                "equipeA": {"id": 1},
                "equipeB": {"id": 2},
                "scoreA": 18,
                "scoreB": 21,
                "roundNumber": 1
            }
            """;

        mockMvc.perform(put("/api/round/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedRoundJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scoreA").value(18))
                .andExpect(jsonPath("$.scoreB").value(21));
    }



    @Test
    public void testDeleteRound() throws Exception {
        mockMvc.perform(delete("/api/round/6"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/round/6"))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2, 21, 14, 1",
            "2, 1, 2, 19, 21, 2",
            "3, 1, 2, 21, 17, 3"
    })
    public void testGetRoundByIdParameterized(long id, long equipeAId, long equipeBId, int scoreA, int scoreB, int roundNumber) throws Exception {
        mockMvc.perform(get("/api/round/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.equipeA.id").value(equipeAId))
                .andExpect(jsonPath("$.equipeB.id").value(equipeBId))
                .andExpect(jsonPath("$.scoreA").value(scoreA))
                .andExpect(jsonPath("$.scoreB").value(scoreB))
                .andExpect(jsonPath("$.roundNumber").value(roundNumber));
    }

    @Test
    public void testCreateRoundInvalidInput_shouldReturnBadRequest() throws Exception {
        String invalidRoundJson = """
                {
                    "equipeA": {},
                    "equipeB": {},
                    "scoreA": -5,
                    "scoreB": 12,
                    "roundNumber": 0
                }
                """;

        mockMvc.perform(post("/api/round")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRoundJson))
                .andExpect(status().isBadRequest());
    }
}*/
