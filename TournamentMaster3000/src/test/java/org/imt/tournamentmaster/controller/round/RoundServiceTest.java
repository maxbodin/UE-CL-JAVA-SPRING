package org.imt.tournamentmaster.controller.round;

import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.service.match.RoundService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RoundServiceTest {

    // TODO-01a : Instancier correctement roundService pour faire compiler et passer les tests
    // TODO-01b : Comprendre la problématique de devoir instancier plusieurs objets pour n'en tester qu'un seul
    private final RoundService roundService = new RoundService();

    @Test
    public void testGetRoundById() {
        Round round1 = roundService.getById(1L);

        // assert
        Assertions.assertNotNull(round1);
        Assertions.assertEquals(1L, round1.getId());
        Assertions.assertEquals(1L, round1.getEquipeA().getId());
        Assertions.assertEquals(2L, round1.getEquipeB().getId());
        Assertions.assertEquals(21, round1.getScoreA());
        Assertions.assertEquals(14, round1.getScoreB());
        Assertions.assertEquals(1, round1.getRoundNumber());

        Round round2 = roundService.getById(2L);

        // assert
        Assertions.assertNotNull(round2);
        Assertions.assertEquals(2L, round2.getId());
        Assertions.assertEquals(1L, round2.getEquipeA().getId());
        Assertions.assertEquals(2L, round2.getEquipeB().getId());
        Assertions.assertEquals(19, round2.getScoreA());
        Assertions.assertEquals(21, round2.getScoreB());
        Assertions.assertEquals(2, round2.getRoundNumber());

        Round round3 = roundService.getById(3L);

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
        Round round = roundService.getById(42L);

        // assert
        Assertions.assertNull(round);
    }

    @Test
    public void testGetAll() {
        List<Round> rounds = roundService.getAll();

        // assert
        Assertions.assertNotNull(rounds);
        Assertions.assertEquals(3, rounds.size());
    }
}
