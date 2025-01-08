package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.repository.match.MatchRepository;

import java.util.List;

public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match getById(int id) {
        return matchRepository.findById(id);
    }

    public List<Match> getAll() {
        return matchRepository.findAll();
    }
}
