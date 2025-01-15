package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.match.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RoundService {

    private final RoundRepository roundRepository;

    @Autowired
    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    public Optional<Round> getById(long id) {
        return roundRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Round> getAll() {
        return StreamSupport.stream(roundRepository.findAll().spliterator(), false)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Round> getByScoreAGreaterThanEqual(int scoreA) {
        return roundRepository.findByScoreAGreaterThanEqual(scoreA);
    }
}
