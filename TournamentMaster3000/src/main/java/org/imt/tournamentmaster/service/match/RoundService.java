package org.imt.tournamentmaster.service.match;

import jakarta.persistence.EntityNotFoundException;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.match.MatchRepository;
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
    private final MatchRepository matchRepository;


    @Autowired
    public RoundService(RoundRepository roundRepository, MatchRepository matchRepository) {
        this.roundRepository = roundRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public Round save(Round round) {
        return roundRepository.save(round);
    }

    @Transactional
    public Round updateRound(Long id, Round updatedRound) {
        Round existingRound = roundRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Round not found"));

        existingRound.setScoreA(updatedRound.getScoreA());
        existingRound.setScoreB(updatedRound.getScoreB());
        existingRound.setRoundNumber(updatedRound.getRoundNumber());
        existingRound.setEquipeA(updatedRound.getEquipeA());
        existingRound.setEquipeB(updatedRound.getEquipeB());

        return roundRepository.save(existingRound);
    }


    @Transactional
    public void deleteById(long id) {
        Round round = roundRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Round not found with id: " + id));

        List<Match> matchesWithRound = matchRepository.findByRoundsContaining(round);
        for (Match match : matchesWithRound) {
            match.getRounds().remove(round);
            matchRepository.save(match);
        }

        roundRepository.deleteById(id);
    }
}
