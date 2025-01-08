package org.imt.tournamentmaster.repository.match;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.MapRepository;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;

import java.util.LinkedList;
import java.util.Map;

public class MatchRepositoryImpl extends MapRepository<Match> implements MatchRepository {

    private final EquipeRepository equipeRepository;

    private final RoundRepository roundRepository;

    public MatchRepositoryImpl(EquipeRepository equipeRepository, RoundRepository roundRepository) {
        this.equipeRepository = equipeRepository;
        this.roundRepository = roundRepository;
    }

    @Override
    public Map<Long, Match> initData() {
        Equipe equipe1 = equipeRepository.findById(1L);
        Equipe equipe2 = equipeRepository.findById(2L);

        Round round1 = roundRepository.findById(1L);
        Round round2 = roundRepository.findById(2L);
        Round round3 = roundRepository.findById(3L);

        LinkedList<Round> rounds = new LinkedList<>();
        rounds.add(round1);
        rounds.add(round2);
        rounds.add(round3);

        return Map.of(
                1L, new Match(1L, equipe1, equipe2, rounds, Match.Status.TERMINE)
        );
    }
}
