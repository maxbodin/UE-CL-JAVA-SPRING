package org.imt.tournamentmaster.controller.match;

import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.service.match.RoundService;

import java.util.List;

public class RoundController {

    private final RoundService roundService;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    public Round getById(long id) {
        return roundService.getById(id);
    }

    public List<Round> getAll() {
        return roundService.getAll();
    }
}
