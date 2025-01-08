package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.match.RoundRepository;

import java.util.List;

public class RoundService {

    private final RoundRepository roundRepository;

    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    public Round getById(long id) {
        return roundRepository.findById(id);
    }

    public List<Round> getAll() {
        return roundRepository.findAll();
    }
}
