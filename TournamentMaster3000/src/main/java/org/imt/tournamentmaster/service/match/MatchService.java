package org.imt.tournamentmaster.service.match;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final EntityManager entityManager;

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(EntityManager entityManager, MatchRepository matchRepository) {
        this.entityManager = entityManager;
        this.matchRepository = matchRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Match> getById(long id) {
        return matchRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Match> getAll() {
        return matchRepository.findAll().stream()
                .toList();
    }

    @Transactional
    public Match save(Match match) {
        return matchRepository.save(match);
    }

    @Transactional
    public void deleteById(long id) {
        matchRepository.deleteById(id);
    }

    public List<Match> searchMatches(Long equipeAId, Long equipeBId, Match.Status status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Match> criteriaQuery = criteriaBuilder.createQuery(Match.class);
        Root<Match> matchRoot = criteriaQuery.from(Match.class);

        List<Predicate> predicates = new ArrayList<>();

        if (equipeAId != null) {
            predicates.add(criteriaBuilder.equal(matchRoot.get("equipeA").get("id"), equipeAId));
        }
        if (equipeBId != null) {
            predicates.add(criteriaBuilder.equal(matchRoot.get("equipeB").get("id"), equipeBId));
        }
        if (status != null) {
            predicates.add(criteriaBuilder.equal(matchRoot.get("status"), status));
        }

        criteriaQuery.select(matchRoot).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}