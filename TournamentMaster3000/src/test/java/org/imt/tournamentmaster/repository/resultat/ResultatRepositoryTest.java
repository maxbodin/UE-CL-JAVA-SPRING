package org.imt.tournamentmaster.repository.resultat;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@SpringBootTest
public class ResultatRepositoryTest {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(ResultatRepositoryTest.class);

    @Autowired
    private ResultatRepository resultatRepository;

    @Test
    @Transactional
    public void testFindById() {
        // find a resultat

        Resultat resultat = resultatRepository.findById(1L).get();

        // assert
        Assertions.assertNotNull(resultat);
        Assertions.assertEquals(1L, resultat.getId());

        Assertions.assertNotNull(resultat.getMatch());
        Assertions.assertEquals(1L, resultat.getMatch().getId());

        Assertions.assertNotNull(resultat.getMatch().getEquipeA());
        Assertions.assertEquals(1L, resultat.getMatch().getEquipeA().getId());

        Assertions.assertNotNull(resultat.getMatch().getEquipeB());
        Assertions.assertEquals(2L, resultat.getMatch().getEquipeB().getId());

        Assertions.assertNotNull(resultat.getMatch().getRounds());
        Assertions.assertEquals(3, resultat.getMatch().getRounds().size());

        Assertions.assertEquals(Match.Status.TERMINE, resultat.getMatch().getStatus());

        Assertions.assertEquals(resultat.getMatch().getEquipeA(), resultat.determineWinner());
    }

    @Test
    public void testFindAll() {
        // find all resultats
        List<Resultat> resultats = StreamSupport.stream(resultatRepository.findAll().spliterator(), false).toList();

        // assert
        Assertions.assertNotNull(resultats);
        Assertions.assertEquals(2, resultats.size());
    }

}
