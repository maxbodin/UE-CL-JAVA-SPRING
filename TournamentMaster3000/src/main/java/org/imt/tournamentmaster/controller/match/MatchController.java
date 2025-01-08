package org.imt.tournamentmaster.controller.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.imt.tournamentmaster.service.match.MatchService;

import java.util.List;

public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    public Match getById(int id) {
        return matchService.getById(id);
    }

    public List<Match> getAll() {
        return matchService.getAll();
    }
}
