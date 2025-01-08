package org.imt.tournamentmaster.repository.resultat;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.repository.MapRepository;
import org.imt.tournamentmaster.repository.match.MatchRepository;

import java.util.Map;

public class ResultatRepositoryImpl extends MapRepository<Resultat> implements ResultatRepository {

    private final MatchRepository matchRepository;

    public ResultatRepositoryImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Map<Long, Resultat> initData() {
        Match match = matchRepository.findById(1L);

        return Map.of(
                1L, new Resultat(1L, match)
        );
    }
}
