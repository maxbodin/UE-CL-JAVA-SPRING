package org.imt.tournamentmaster.controller.round;

import org.imt.tournamentmaster.controller.match.RoundController;
import org.imt.tournamentmaster.model.match.Round;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@SpringBootTest
public class RoundControllerTest {

    @Autowired
    private RoundController roundController;

    @Test
    public void testGetRoundById() {
        Round round1 = roundController.getById(1L).getBody();

        // assert
        Assertions.assertNotNull(round1);
        Assertions.assertEquals(1L, round1.getId());
        Assertions.assertEquals(1L, round1.getEquipeA().getId());
        Assertions.assertEquals(2L, round1.getEquipeB().getId());
        Assertions.assertEquals(21, round1.getScoreA());
        Assertions.assertEquals(14, round1.getScoreB());
        Assertions.assertEquals(1, round1.getRoundNumber());

        Round round2 = roundController.getById(2L).getBody();

        // assert
        Assertions.assertNotNull(round2);
        Assertions.assertEquals(2L, round2.getId());
        Assertions.assertEquals(1L, round2.getEquipeA().getId());
        Assertions.assertEquals(2L, round2.getEquipeB().getId());
        Assertions.assertEquals(19, round2.getScoreA());
        Assertions.assertEquals(21, round2.getScoreB());
        Assertions.assertEquals(2, round2.getRoundNumber());

        Round round3 = roundController.getById(3L).getBody();

        // assert
        Assertions.assertNotNull(round3);
        Assertions.assertEquals(3L, round3.getId());
        Assertions.assertEquals(1L, round3.getEquipeA().getId());
        Assertions.assertEquals(2L, round3.getEquipeB().getId());
        Assertions.assertEquals(21, round3.getScoreA());
        Assertions.assertEquals(17, round3.getScoreB());
        Assertions.assertEquals(3, round3.getRoundNumber());
    }

    @Test
    public void testGetNonExistingRoundById_shouldBeNull() {
        HttpStatusCode status = roundController.getById(42L).getStatusCode();

        // assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND, status);
    }

    @Test
    public void testGetAll() {
        List<Round> rounds = roundController.getAll();

        // assert
        Assertions.assertNotNull(rounds);
        Assertions.assertEquals(6, rounds.size());
    }
}
